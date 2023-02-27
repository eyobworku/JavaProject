package proj1;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LoginController {
    Queries query = new Queries();

    @FXML
    private TextField nameTF;

    @FXML
    private PasswordField passwordTF;

    @FXML
    private HBox thirdHbox;

    @FXML
    private Label msgLable;

    @FXML
    void loginButtonClicked(ActionEvent event) throws IOException {
        //Account logedUser = query.loginUser(nameTF.getText(), passwordTF.getText());
        Account logedUser = new Account(1, "test", "null","0909123", "admin");
        //String msg;
        if(logedUser != null){
            // if(logedUser.getRoll().equals("admin")){
            //     //msg = "You are Admin.";
            // }
            // else {
            //     //msg = "You are librerian.";
            // }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainDash.fxml"));
            Parent root = loader.load();
    
            MainDash mainDash = loader.getController();
            mainDash.setAccountInfo(logedUser);
            //mainDash.userBtId.setVisible(false);
    
            Scene scene = new Scene(root,1000,700);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    
            stage.setScene(scene);
            stage.show();
        }
        //else {
        //     msg = "Login error.";
        //     msgLable.setText(msg);
        // }
    }
}
