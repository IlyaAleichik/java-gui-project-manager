package views.authorization;

import java.net.URL;
import java.util.ResourceBundle;
import database.Factory;
import javafx.fxml.FXML;
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
        Factory connection = new Factory();

        buttonSignUp.setOnAction(event -> {
            User user = new User(textFieldName.getText(),textFieldSurname.getText(),textFieldPatronymic.getText(),textFieldPassword.getText(),textFieldPhone.getText(),textFieldEmail.getText(),textFieldUsername.getText());
            connection.getUsers().insertUser(user);
            Stage stage = (Stage) buttonSignUp.getScene().getWindow();
            stage.close();
        });
    }
}
