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

public class MemberController implements Initializable{
    Queries queries = new Queries();
    private final ObservableList<Member> memberList = FXCollections.observableArrayList();
  
    @FXML
    private VBox addNewMemberVB;
    
    @FXML
    private VBox viewMemberVB;

    @FXML
    private TextField addPhoneTF;

    @FXML
    private TextField addUsernameTF;

    @FXML
    private TextField addEmailTF;

    @FXML
    private TableView<Member> memberTable;

    @FXML
    private TableColumn<Member, String> emailCol;

    @FXML
    private TableColumn<Member, Integer> memberIdCol;

    @FXML
    private TableColumn<Member, String> memberNameCol;

    @FXML
    private TableColumn<Member, String> phoneCol;

    @FXML
    private TextField viewEmailTF;


    @FXML
    private TextField viewNameTF;

    @FXML
    private TextField viewPhoneTF;

    @FXML
    void addClicked(ActionEvent event) {
        String fullName = addUsernameTF.getText();
        String email = addEmailTF.getText();
        String phone = addPhoneTF.getText(); 
        if(!fullName.isBlank() && !email.isBlank() && !phone.isBlank()){
            int result = queries.createMember(new Member(0, fullName, phone, email));
            if (result > 0) {
                displayAlert(Alert.AlertType.INFORMATION, "Member Created", "Member Created Succesfully");
                memberList.setAll(queries.getAllMember());
            } else {
                displayAlert(Alert.AlertType.ERROR, "Member Not Created", "Unabale to create Member");
            }
        }
    }

    @FXML
    void addMemberClicked(ActionEvent event) {
        // String fullName = addUsernameTF.getText();
        // String email = addEmailTF.getText();
        // String phone = addPhoneTF.getText();
        // if(!fullName.isEmpty() && !email.isEmpty() && !phone.isEmpty()){
            memberTable.getSelectionModel().clearSelection();
            viewMemberVB.visibleProperty().set(false);
            addNewMemberVB.visibleProperty().set(true);
        //}
    }

    @FXML
    void addResetClick(ActionEvent event) {
        addUsernameTF.clear();
        addEmailTF.clear();
        addPhoneTF.clear();
    }

    @FXML
    void viewEditClicked(ActionEvent event) {
        String fullName = viewNameTF.getText();
        String email = viewEmailTF.getText();
        String phone = viewPhoneTF.getText();
        if(!fullName.isEmpty() && !email.isEmpty() && !phone.isEmpty()){
            Member editMember = memberTable.selectionModelProperty().get().getSelectedItem();
            editMember.setFullName(fullName);
            editMember.setEmail(email);
            editMember.setPhoneNo(phone);
            int result = queries.editMember(editMember);
            if(result > 0){
                displayAlert(Alert.AlertType.INFORMATION, "Member Edited", "Member edited successfully");
                memberList.setAll(queries.getAllMember());
            } else {
                displayAlert(Alert.AlertType.ERROR, "Member Not Edited", "Unabale to edit Member");
            }
        }else{
            displayAlert(Alert.AlertType.INFORMATION, "Enter Data", "Please Enter Appropirate Data");
        }

    }
    public void displayMember(Member member) {
        if (member != null) {
            viewNameTF.setText(member.getFullName());
            viewPhoneTF.setText(member.getPhoneNo());
            viewEmailTF.setText(member.getEmail());
        } else {
            viewNameTF.clear();
            viewPhoneTF.clear();
            viewEmailTF.clear();
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
        memberList.setAll(queries.getAllMember());
        memberIdCol.setCellValueFactory(new PropertyValueFactory<Member, Integer>("memId"));
        memberNameCol.setCellValueFactory(new PropertyValueFactory<Member, String>("fullName"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Member, String>("phoneNo"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Member, String>("email"));
        memberTable.setItems(memberList);
        memberTable.getSelectionModel().selectedItemProperty().addListener(
                (ob, oldValue, newValue) -> {
                displayMember(newValue);
                addNewMemberVB.visibleProperty().set(false);
                viewMemberVB.visibleProperty().set(true);
            });
    }

}
