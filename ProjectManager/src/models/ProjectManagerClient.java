package models;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
;

public class ProjectManagerClient extends Application {
    Parent root;
    private double xOffset = 0;
    private double yOffset = 0;
    @Override
    public void start(Stage primaryStage) throws Exception{
        try {

            root = FXMLLoader.load(getClass().getResource("/views/authorization/LoginWindow.fxml"));

            root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
            });
            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
             }
            });

            Scene scene = new Scene(root);
            primaryStage.setTitle("Hello World");
            primaryStage.initStyle(StageStyle.UNDECORATED);
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
