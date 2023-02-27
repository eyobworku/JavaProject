package proj1;

import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class bookView  implements Initializable {
    Queries queries = new Queries();

    private final ObservableList<Book> bookList = FXCollections.observableArrayList();

    @FXML
    private TableView<Book> bookTable;
    
    @FXML
    private TableColumn<Book,Integer> quantityCol;

    @FXML
    private TableColumn<Book,String> authorNameCol;

    @FXML
    private TableColumn<Book,Integer> bookIdCol;

    @FXML
    private TableColumn<Book,String> bookNameCol;

    @FXML
    private TableColumn<Book,String> generCol;

    @FXML
    private TextField bookIdTF;

    @FXML
    private TextField memberIdTF;

    @FXML
    private ImageView myBookIV;
    
    @FXML
    private TextField searchTF;

    @FXML
    void borrowClicked(ActionEvent event) {

    }

    public void displayPicture(Book book) {
        if (book != null) {
           //Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
           try {
            BufferedImage bufferedImage = ImageIO.read(new File(book.getImageBook()));
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            myBookIV.setImage(image);
            bookIdTF.setText(String.valueOf(book.getBookId()));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        }else {
            myBookIV.setImage(null);
        }
    }

    @Override
    public void initialize(URL param1, ResourceBundle param2) {
        bookList.setAll(queries.getAllBook());
        bookIdCol.setCellValueFactory(new PropertyValueFactory<Book,Integer>("bookId"));
        bookNameCol.setCellValueFactory(new PropertyValueFactory<Book,String>("bookName"));
        authorNameCol.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
        generCol.setCellValueFactory(new PropertyValueFactory<Book,String>("gener"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<Book,Integer>("quantity"));
        bookTable.setItems(bookList);
        bookTable.getSelectionModel().selectedItemProperty().addListener(
                (ob, oldValue, newValue) -> {
                    displayPicture(newValue);
            });

        FilteredList<Book> filteredData = new FilteredList<>(bookList, b -> true);

        searchTF.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(book -> {
                if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();

                if(book.getBookName().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                } else if(book.getAuthor().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                } else if(book.getGener().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }else if(Integer.toString(book.getQuantity()).toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                } else
                    return false;
            });
        });

        SortedList<Book> sortedData = new SortedList <>(filteredData);

        sortedData.comparatorProperty().bind(bookTable.comparatorProperty());

        bookTable.setItems(sortedData);
    }
}
