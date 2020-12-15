package models;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ProjectManagerClient extends Application {
    Parent root;
    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            root = FXMLLoader.load(getClass().getResource("/views/authorization/LoginWindow.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Hello World");
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.setScene(scene);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.show();
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
