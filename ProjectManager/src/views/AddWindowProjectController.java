package views;

import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import database.Factory;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Project;

public class AddWindowProjectController {

    private int user_id;
    private int project_id;
    private boolean update;
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
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void acceptAddProject(MouseEvent event) {

        if (update == false) {

            Factory factory = new Factory();

            String dateTermDeliveryProject = String.valueOf(dataPickerProject.getValue());
            String costDeliveryProject =  textFieldCostProject.getText();
            String nameProject = textFieldNameProject.getText();
            String descriptionProject = textAreaDeskProject.getText();

            Project project = new Project(dateTermDeliveryProject, costDeliveryProject,nameProject,descriptionProject,user_id);

            if (nameProject.isEmpty() || dateTermDeliveryProject.isEmpty()|| String.valueOf(costDeliveryProject).isEmpty()|| descriptionProject.isEmpty() ){
                dialogAlert("Input error", "Пожалуйста введите все данные");
            }else {
                factory.getProject().insertProject(project);
                dialogAlert("", "Данные успешно добавлены");
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }


        }else{
            Factory factory = new Factory();

            String dateTermDeliveryProject = String.valueOf(dataPickerProject.getValue());
            String costDeliveryProject =  textFieldCostProject.getText();
            String nameProject = textFieldNameProject.getText();
            String descriptionProject = textAreaDeskProject.getText();

            Project project = new Project(project_id,dateTermDeliveryProject, costDeliveryProject,nameProject,descriptionProject,user_id);

            if (nameProject.equals("") && dateTermDeliveryProject.equals("") || String.valueOf(costDeliveryProject).equals("") || descriptionProject.equals("") ){
                dialogAlert("Input error", "Пожалуйста введите все данные");
            }else {
                factory.getProject().updateProject(project);
                System.out.println(project_id);
                dialogAlert("", "Данные успешно обнавлены");
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }
        }

    }

    @FXML
    void cleanAddProject(MouseEvent event) {

    }

    @FXML
    void initialize() {

    }

    public void setUpdate(boolean b) {
        this.update = b;

    }
    public void setTextField(int id, String name, String cost, String desk) {

        project_id = id;
        textFieldNameProject.setText(name);
        textFieldCostProject.setText(String.valueOf(cost));
        textAreaDeskProject.setText(desk);

    }
    private void dialogAlert(String type ,String str){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(type);
        alert.setHeaderText(null);
        alert.setContentText(str);
        alert.showAndWait();
    }
}
