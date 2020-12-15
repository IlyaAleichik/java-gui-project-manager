package views.authorization;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.client.Client;

public class LoginWindowController {
    Parent root;
    Client client;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button signInButton;

    @FXML
    void initialize() {

        client = new Client("127.0.0.1", "3030");

        signInButton.setOnAction(event -> {
            client.sendMessage("ok");
            try {
                System.out.println(client.readMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        signUpButton.setOnAction(event -> {
            try {
                root = FXMLLoader.load(getClass().getResource("/views/authorization/RegistrationWindow.fxml"));
                Scene scene = new Scene(root);
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Регистрация");
                primaryStage.setScene(scene);
                primaryStage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
