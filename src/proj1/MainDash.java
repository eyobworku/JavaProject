package proj1;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainDash {

    @FXML
    private Label accountLable;

    @FXML
    private Button userBtId;

    @FXML
    private BorderPane borderPane;

    @FXML
    private VBox centerVB;
    
    @FXML
    void bookBtClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Book.fxml"));
        Parent bookVb = loader.load();
        centerVB.getChildren().removeAll();
        centerVB.getChildren().setAll(bookVb);
        BookController book = loader.getController();
        book.setAddVbox();
    }

    @FXML
    void userBtClick(ActionEvent event) throws IOException {
        Parent userVb = FXMLLoader.load(getClass().getResource("account.fxml"));
        centerVB.getChildren().removeAll();
        centerVB.getChildren().setAll(userVb);
    }

    @FXML
    void memberClicked(ActionEvent event) throws IOException {
        Parent memberVb = FXMLLoader.load(getClass().getResource("Member.fxml"));
        centerVB.getChildren().removeAll();
        centerVB.getChildren().setAll(memberVb);
    }
    @FXML
    void logoutClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
    
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void homeBtClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainDash.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root,905,534);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();


    }

    public void setAccountInfo(Account user){
        accountLable.setText(user.getUserName());
        if(user.getRoll().equals("librerian")){
            userBtId.setVisible(false);
            userBtId.setManaged(false);
        }
    }
}