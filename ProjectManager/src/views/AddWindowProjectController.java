package views;

import java.net.URL;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;

import database.Factory;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import models.Project;

public class AddWindowProjectController {

    private int user_id;
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textFieldNameProject;

    @FXML
    private DatePicker dataPickerProject;

    @FXML
    private TextField textFieldCostProject;

    @FXML
    private TextArea textAreaDeskProject;

    @FXML
    private Button buttonCleanProject;

    @FXML
    private Button buttonAboutProject;

    @FXML
    private Button buttonAceptProject;

    @FXML
    void aboutAddProject(MouseEvent event) {

    }

    @FXML
    void acceptAddProject(MouseEvent event) {
        Factory factory = new Factory();

        String dateTermDeliveryProject = String.valueOf(dataPickerProject.getValue());
        String costDeliveryProject =  textFieldCostProject.getText();
        String nameProject = textFieldNameProject.getText();
        String descriptionProject = textAreaDeskProject.getText();

        Project project = new Project(dateTermDeliveryProject, Double.valueOf(costDeliveryProject),nameProject,descriptionProject,user_id);

        if (nameProject.isEmpty() || dateTermDeliveryProject.isEmpty()|| String.valueOf(costDeliveryProject).isEmpty()|| descriptionProject.isEmpty() ){
            dialogAlert("Input error", "Пожалуйста введите все данные");
        }else {
            factory.getProject().insertProject(project);
            dialogAlert("", "Данные успешно добавлены");
        }
    }

    @FXML
    void cleanAddProject(MouseEvent event) {

    }

    @FXML
    void initialize() {

    }
    private void dialogAlert(String type ,String str){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(type);
        alert.setHeaderText(null);
        alert.setContentText(str);
        alert.showAndWait();
    }
}
