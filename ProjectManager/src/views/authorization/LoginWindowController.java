package views.authorization;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import database.Factory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.User;
import views.dashboard.DashboardWindowController;

public class LoginWindowController {
    ObservableList<User> userList = FXCollections.observableArrayList();
    private FXMLLoader loader;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField textField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button signInButton;



    @FXML
    void initialize() {

        signInButton.setOnAction(event -> {
            String loginText = textField.getText().trim();
            String loginPassword = passwordField.getText().trim();
            if (!loginText.equals("") && !loginPassword.equals((""))){
                try {
                    loginUser(loginText,loginPassword);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                dialogAlert("Input error","Login and password is empty");
            }
        });

        signUpButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/views/authorization/RegistrationWindow.fxml"));
                Scene scene = new Scene(root);
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Регистрация");
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
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

private void showDasboardOld(){
    try {
        Parent root = FXMLLoader.load(getClass().getResource("/views/dashboard/DashboardWindow.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    private  void showDashboard(int userId,int userRoleId, String userText) throws IOException {

       User userData = new User();
       userData.setUserId(userId);
       userData.setUserRole(userRoleId);
       userData.setUserNickname(userText);



        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/dashboard/DashboardWindow.fxml"));
        loader.load();

        Parent root =  loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        DashboardWindowController dashboardWindowController = (DashboardWindowController) loader.getController();
        dashboardWindowController.initDataUser(userData);

        stage.show();
        signInButton.getScene().getWindow().hide();




    }

    private void loginUser(String loginText,String loginPassword) throws SQLException, IOException {
        Factory factory = new Factory();
        User user = new User();
        user.setUserNickname(loginText);
        user.setUserEmail(loginText);
        user.setUserPassword(loginPassword);
        ResultSet result = factory.getUsers().selectUser(user);

        int counter = 0;

        try {
            while (result.next()){
                counter++;
                userList.add(new User(
                        result.getInt("user_id"),
                        result.getString("user_name"),
                        result.getString("user_surname"),
                        result.getString("user_patronymic"),
                        result.getString("user_password"),
                        result.getString("user_phone"),
                        result.getString("user_email"),
                        result.getString("user_nickname"),
                        result.getInt("user_role_id")
                ));

            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        if (counter >=1){
            for (User item: userList) {
                showDashboard(item.getUserId(),item.getUserRole(),item.getUserNickname());
            }

        }else {
            dialogAlert("Input error","User don't is exist");
        }
    }
}
