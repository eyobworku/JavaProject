package proj1;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class AccountController implements Initializable {
    Queries queries = new Queries();

    private final ObservableList<Account> userList = FXCollections.observableArrayList();

    @FXML
    private RadioButton addAdminCB;

    @FXML
    private RadioButton addLibrCB;

    @FXML
    private VBox addNewUserVB;

    @FXML
    private TextField addPasswordTF;

    @FXML
    private TextField addPhoneTF;

    @FXML
    private ToggleGroup addTG;

    @FXML
    private TextField addUsernameTF;

    @FXML
    private TableView<Account> userTable;

    @FXML
    private TableColumn<Account, String> passwordCol;

    @FXML
    private TableColumn<Account, String> phoneCol;

    @FXML
    private TableColumn<Account, String> rollCol;

    @FXML
    private TableColumn<Account, String> usernameCol;

    @FXML
    private RadioButton viewAdminCB;

    @FXML
    private RadioButton viewLIbCB;

    @FXML
    private TextField viewPasswordTF;

    @FXML
    private TextField viewPhoneTF;

    @FXML
    private ToggleGroup viewTG;

    @FXML
    private VBox viewUserVB;

    @FXML
    private TextField viewUsernameTF;

    // used to change to add user form
    @FXML
    void addUserClicked(ActionEvent event) {
        userTable.getSelectionModel().clearSelection();
        if (viewUserVB.visibleProperty().get()) {
            viewUserVB.visibleProperty().set(false);
            addNewUserVB.visibleProperty().set(true);
        } 
        // else {
        //     addNewUserVB.visibleProperty().set(false);
        //     viewUserVB.visibleProperty().set(true);
        // }
    }

    @FXML
    void addCreateClicked(ActionEvent event) {
        String username = addUsernameTF.getText();
        String password = addPasswordTF.getText();
        String phone = addPhoneTF.getText();
        if (addTG != null && (username != "" && password != "" && phone != "")) {
            String roll;
            if(addTG.getSelectedToggle().equals(addAdminCB)){
                roll = "admin";
            }else{
                roll = "librerian";
            }
            int result = queries.createUserAccount(username, password, roll, phone);
            if (result > 0) {
                displayAlert(Alert.AlertType.INFORMATION, "Account Created", "Account Created Succesfully");
                userList.setAll(queries.getAllUser());
            } else {
                displayAlert(Alert.AlertType.ERROR, "Account Not Created", "Unabale to create Account");
            }
        } else {
            displayAlert(Alert.AlertType.INFORMATION, "Enter Data", "Please Enter Appropirate Data");
        }

    }

    @FXML
    void addResetClicked(ActionEvent event) {
        addUsernameTF.clear();
        addPasswordTF.clear();
        addPhoneTF.clear();
        addAdminCB.selectedProperty().set(false);
        addLibrCB.selectedProperty().set(false);
    }

    @FXML
    void viewDeleteClicked(ActionEvent event) {
        Account delAccount = userTable.getSelectionModel().getSelectedItem();
        if (delAccount != null) {
            int result = queries.deleteAccount(delAccount.getId());
            if (result > 0) {
                displayAlert(Alert.AlertType.INFORMATION, "Account Deleteed", "The account deleted successfuly");
                // userTable.getSelectionModel().clearSelection();
                userList.setAll(queries.getAllUser());
            } else {
                displayAlert(Alert.AlertType.ERROR, "Account Not Deleted", "Unabale to delete Account");
            }
        }
    }

    @FXML
    void viewEditClicked(ActionEvent event) {
        String username = viewUsernameTF.getText();
        String password = viewPasswordTF.getText();
        String phone = viewPhoneTF.getText();
        if(userTable != null && (username != "" && password != "" && phone != "") && viewTG != null){
            Account ediAccount = userTable.selectionModelProperty().get().getSelectedItem();
            String roll;
            if(viewTG.getSelectedToggle().equals(viewAdminCB)){
                roll = "admin";
            }else{
                roll = "librerian";
            }
            //System.out.println("roll:"+roll+"zz");
            ediAccount.setUserName(username);
            ediAccount.setPassword(password);
            ediAccount.setPhoneNo(phone);
            ediAccount.setRoll(roll);
            int result = queries.ediAccount(ediAccount);
            if(result > 0){
                displayAlert(Alert.AlertType.INFORMATION, "Account Edited", "Account edited successfully");
                userList.setAll(queries.getAllUser());
            } else {
                displayAlert(Alert.AlertType.ERROR, "Account Not Edited", "Unabale to edit Account");
            }
        }else{
            displayAlert(Alert.AlertType.INFORMATION, "Enter Data", "Please Enter Appropirate Data");
        }
    }

    public void displayContact(Account account) {
        if (account != null) {
            viewUsernameTF.setText(account.getUserName());
            viewPasswordTF.setText(account.getPassword());
            viewPhoneTF.setText(account.getPhoneNo());
            if (account.getRoll().equals("admin")) {
                viewAdminCB.selectedProperty().set(true);
                viewLIbCB.selectedProperty().set(false);
            } else {
                viewAdminCB.selectedProperty().set(false);
                viewLIbCB.selectedProperty().set(true);
            }
        } else {
            viewUsernameTF.clear();
            viewPasswordTF.clear();
            viewPhoneTF.clear();
            viewAdminCB.selectedProperty().set(false);
            viewLIbCB.selectedProperty().set(false);
        }
        System.out.println("account: "+account);
    }

    private void displayAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL param1, ResourceBundle param2) {
        userList.setAll(queries.getAllUser());
        usernameCol.setCellValueFactory(new PropertyValueFactory<Account, String>("userName"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<Account, String>("password"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Account, String>("phoneNo"));
        rollCol.setCellValueFactory(new PropertyValueFactory<Account, String>("roll"));
        userTable.setItems(userList);
        userTable.getSelectionModel().selectedItemProperty().addListener(
                (ob, oldValue, newValue) -> {
                displayContact(newValue);
                addNewUserVB.visibleProperty().set(false);
                viewUserVB.visibleProperty().set(true);
            });
    }
}