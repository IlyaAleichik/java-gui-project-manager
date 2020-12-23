package views;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.Factory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.*;

public class AddWindowTaskController {


    private  boolean update;
    int task_id;
    int project_id;
    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    ObservableList<StateTask>  stateTasksList = FXCollections.observableArrayList();
    ObservableList<PriorityTask>  priorityTasksList = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textFieldNameTask;

    @FXML
    private TextArea textFieldTaskNote;

    @FXML
    private ComboBox<StateTask> coboBoxStateTask;

    @FXML
    private ComboBox<PriorityTask> coboBoxPriorityTask;

    @FXML
    private Button buttonCleanTask;

    @FXML
    private Button buttonAboutTask;

    @FXML
    private Button buttonAceptTask;

    @FXML
    void aboutAddTask(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void acceptAddTask(MouseEvent event) {
        Factory factory = new Factory();
        if (update == false) {

            String taskName =  textFieldNameTask.getText();
            String tasnNote = textFieldTaskNote.getText();
            int comBoxTaskStateId = coboBoxStateTask.getSelectionModel().getSelectedItem().getState_id();
            int comBoxTaskPriorityId = coboBoxPriorityTask.getSelectionModel().getSelectedItem().getPriority_id();
            Task task = new Task(project_id,taskName,tasnNote,comBoxTaskStateId,comBoxTaskPriorityId);

            if (taskName.equals("")|| tasnNote.equals("")|| comBoxTaskStateId == 0 || comBoxTaskPriorityId == 0){
                dialogAlert("Input error", "Пожалуйста введите все данные");
            }else {
                factory.getTask().insertTask(task);
                dialogAlert("", "Данные успешно добавлены");
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }


        }else{
            String taskName =  textFieldNameTask.getText();
            String tasnNote = textFieldTaskNote.getText();
            int comBoxTaskStateId = coboBoxStateTask.getSelectionModel().getSelectedItem().getState_id();
            int comBoxTaskPriorityId = coboBoxPriorityTask.getSelectionModel().getSelectedItem().getPriority_id();
            Task task = new Task(taskName,tasnNote,comBoxTaskStateId,comBoxTaskPriorityId,task_id);

            if (taskName.equals("")|| tasnNote.equals("")|| comBoxTaskStateId == 0 || comBoxTaskPriorityId == 0){
                dialogAlert("Input error", "Пожалуйста введите все данные");
            }else {
                factory.getTask().updateTask(task);
                dialogAlert("", "Данные успешно добавлены");
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }
        }
    }

    public void setTextField(String taskName, String tasnNote, int taskId) {
        textFieldNameTask.setText(taskName);
        textFieldTaskNote.setText(tasnNote);
        this.task_id = taskId;
    }
    @FXML
    void cleanAddTask(MouseEvent event) {

    }

    @FXML
    void initialize() throws SQLException {
        loadComboBox();
    }
    private void loadComboBox() throws SQLException {

        Factory factory = new Factory();
        ObservableList<StateTask>  stateTasksList = FXCollections.observableArrayList();
        ObservableList<PriorityTask>  priorityTaskList = FXCollections.observableArrayList();

        ResultSet resultSet = factory.getTask().selectStateTask();
        while (resultSet.next()){
            stateTasksList.add(new StateTask(
                    resultSet.getInt("state_id"),
                    resultSet.getString("state_name")
            ));
        };
        coboBoxStateTask.setItems(stateTasksList);


        resultSet = factory.getTask().selectPriorityTask();
        while (resultSet.next()){
            priorityTaskList.add(new PriorityTask(
                    resultSet.getInt("priority_id"),
                    resultSet.getString("prioroty_name")
            ));
        };
        coboBoxPriorityTask.setItems(priorityTaskList);
    }

    public void setUpdate(boolean b) {
        this.update = b;
    }
    private void dialogAlert(String type ,String str){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(type);
        alert.setHeaderText(null);
        alert.setContentText(str);
        alert.showAndWait();
    }

}
