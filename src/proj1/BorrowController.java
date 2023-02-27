package proj1;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class BorrowController implements Initializable{
    Queries queries = new Queries();

    private final ObservableList<Borrow> borrowList = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Borrow,Integer> bookIdcol;

    @FXML
    private TextField borrBookId;

    @FXML
    private VBox borrowBookVB;

    @FXML
    private TableColumn<Borrow,Integer> borrowIdCol;

    @FXML
    private TextField borrowMemberId;

    @FXML
    private TableView<Borrow> borrowedTable;

    @FXML
    private TableColumn<Borrow,Integer> memberIdCol;

    @FXML
    private TextField returnBookIdTF;

    @FXML
    private TextField returnMemberIdTF;

    @FXML
    private VBox viewMemberVB;

    @FXML
    void borrowClicked(ActionEvent event) {
        int bookId = Integer.parseInt(borrBookId.getText());
        int membId = Integer.parseInt(borrowMemberId.getText());
        queries.borrowBook(bookId, membId);
        displayAlert(Alert.AlertType.INFORMATION, "Succes", "Borrowed");
        borrowList.setAll(queries.getAllBorrow());
    }

    @FXML
    void returnBookClicked(ActionEvent event) {
        Borrow selecedBorrow = borrowedTable.selectionModelProperty().get().getSelectedItem();
        int result = queries.returnBook(selecedBorrow.getBorrowId());
        //if (result > 0) {
            displayAlert(Alert.AlertType.INFORMATION, "Succes", "Returned");
            borrowList.setAll(queries.getAllBorrow());
    }

    @FXML
    void borrowBookViewClick(ActionEvent event) {
        borrowedTable.getSelectionModel().clearSelection();
        if (viewMemberVB.visibleProperty().get()) {
            viewMemberVB.visibleProperty().set(false);
            borrowBookVB.visibleProperty().set(true);
        }

    }

    public void displayBorrow(Borrow borrow) {
        if (borrow != null) {
            returnBookIdTF.setText(Integer.toString(borrow.getBookId()) );
            returnMemberIdTF.setText(Integer.toString(borrow.getMembId()));
        } else {
            returnBookIdTF.clear();
            returnMemberIdTF.clear();
        }
    }

    private void displayAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL param1, ResourceBundle param2) {
        borrowList.setAll(queries.getAllBorrow());
        borrowIdCol.setCellValueFactory(new PropertyValueFactory<Borrow, Integer>("borrowId"));
        bookIdcol.setCellValueFactory(new PropertyValueFactory<Borrow, Integer>("membId"));
        memberIdCol.setCellValueFactory(new PropertyValueFactory<Borrow, Integer>("bookId"));
        borrowedTable.setItems(borrowList);
        borrowedTable.getSelectionModel().selectedItemProperty().addListener(
                (ob, oldValue, newValue) -> {
                displayBorrow(newValue);
                borrowBookVB.visibleProperty().set(false);
                viewMemberVB.visibleProperty().set(true);
            });
    }

}
