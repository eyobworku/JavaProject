package proj1;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class BookController {

    @FXML
    private VBox booxLowerVbox;
    
    @FXML
    void addBtClick(ActionEvent event) throws IOException {
        Parent bookAddVb = FXMLLoader.load(getClass().getResource("bookAdd.fxml"));
        booxLowerVbox.getChildren().removeAll();
        booxLowerVbox.getChildren().setAll(bookAddVb);
    }

    @FXML
    void searchBtClick(ActionEvent event) throws IOException {
        Parent bookSearchVb = FXMLLoader.load(getClass().getResource("bookSearch.fxml"));
        booxLowerVbox.getChildren().removeAll();
        booxLowerVbox.getChildren().setAll(bookSearchVb);
    }

    @FXML
    void viewBtClick(ActionEvent event) throws IOException {
        Parent booViewVb = FXMLLoader.load(getClass().getResource("bookView.fxml"));
        booxLowerVbox.getChildren().removeAll();
        booxLowerVbox.getChildren().setAll(booViewVb);
    }

    public void setAddVbox() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("bookAdd.fxml"));
        Parent bookAddVb = loader.load();
        booxLowerVbox.getChildren().removeAll();
        booxLowerVbox.getChildren().setAll(bookAddVb);
    }

}