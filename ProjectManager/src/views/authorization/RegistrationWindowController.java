package views.authorization;

import java.net.URL;
import java.util.ResourceBundle;
import database.Factory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.User;


public class RegistrationWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textFieldUsername;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldSurname;

    @FXML
    private TextField textFieldPatronymic;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldPhone;

    @FXML
    private Button buttonSignUp;

    @FXML
    private CheckBox checkBoxAcceptPrivacy;

    @FXML
    void initialize() {
        String typeError = "Input error";
        Factory connection = new Factory();

        checkBoxAcceptPrivacy.setOnAction(event -> chekSelected());

        buttonSignUp.setOnAction(event -> {
            if(textFieldUsername.getText().equals("")){
                dialogAlert(typeError, "Not input Username");
                if(textFieldName.getText().equals("")){
                    dialogAlert(typeError, "Not input Name");
                    if(textFieldSurname.getText().equals("")){
                        dialogAlert(typeError, "Not input Surname");
                        if(textFieldPatronymic.getText().equals("")){
                            dialogAlert(typeError, "Not input Patronymic");
                            if(textFieldPassword.getText().equals("")){
                                dialogAlert(typeError, "Not input Paswword");
                                if(textFieldEmail.getText().equals("")){
                                    dialogAlert(typeError, "Not input Email");
                                    if(textFieldPhone.getText().equals("")){
                                        dialogAlert(typeError, "Not input Phone");
                                    }
                                    else {
                                        User user = new User(textFieldName.getText(),textFieldSurname.getText(),textFieldPatronymic.getText(),textFieldPassword.getText(),textFieldPhone.getText(),textFieldEmail.getText(),textFieldUsername.getText());
                                        connection.getUsers().insertUser(user);
                                        Stage stage = (Stage) buttonSignUp.getScene().getWindow();
                                        stage.close();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    private void dialogAlert(String type ,String str){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(type);
        alert.setHeaderText(null);
        alert.setContentText(str);
        alert.showAndWait();
    }

    private void chekSelected(){
        if(checkBoxAcceptPrivacy.isSelected()) buttonSignUp.setDisable(false);
    }

}
