package views.authorization;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.client.Client;

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
        buttonSignUp.setOnAction(event -> {
            Stage stage = (Stage) buttonSignUp.getScene().getWindow();
            stage.close();
        });
    }
}
