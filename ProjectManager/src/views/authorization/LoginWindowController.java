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
import javafx.stage.Stage;

import javax.print.DocFlavor;

public class LoginWindowController {
    Parent root;
    //Client client;
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
        signInButton.setOnAction(event -> {
            String loginText = emailField.getText().trim();
            String loginPassword = passwordField.getText().trim();
            
            if (!loginText.equals("") && !loginPassword.equals((""))){
                try {
                    signInButton.getScene().getWindow().hide();
                    root = FXMLLoader.load(getClass().getResource("/views/dashboard/DashboardWindow.fxml"));
                    Scene scene = new Scene(root);
                    Stage primaryStage = new Stage();
                    primaryStage.setTitle("AICGTrade Projects Manager");
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("Login and password is empty");
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

     /*   signInButton.setOnAction(event -> {
            try {
                signInButton.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/views/dashboard/DashboardWindow.fxml"));
                Scene scene = new Scene(root);
                Stage primaryStage = new Stage();
                primaryStage.setTitle("AICGTrade Projects Manager");
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });*/


    }
}
