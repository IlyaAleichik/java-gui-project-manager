package views.dashboard;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import database.Factory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import models.Project;
import models.User;
import javafx.scene.control.Label;

import javax.xml.transform.Result;

public class DashboardWindowController {
    private int t_user_id;
    private String t_user_name;
    private int t_user_role;

    private ObservableList<Project> projectsList = FXCollections.observableArrayList();
   // private ObservableList<Task> tasks = FXCollections.observableArrayList();
   // private ObservableList<Risk> risks = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Label usernameLabel;

    @FXML
    private HBox dashboardButton;

    @FXML
    private HBox projectButton;

    @FXML
    private HBox taskButton;

    @FXML
    private HBox financeButton;

    @FXML
    private HBox reportButton;

    @FXML
    private HBox hBoxProjectPane;

    @FXML
    private VBox pageReports;

    @FXML
    private VBox pageDash;

    @FXML
    private VBox pageProjects;

    @FXML
    private ScrollPane scrollProjectPane;

    @FXML
    private GridPane gridProjectPane;

    @FXML
    public void initialize() {


        dashboardButton.setOnMouseClicked(event -> {
            pageDash.toFront();
        });
        projectButton.setOnMouseClicked(event -> {
            pageProjects.toFront();

        });
        reportButton.setOnMouseClicked(event -> {
            pageReports.toFront();
        });

    }

    public void initDataUser(User obj){
        this.t_user_id = obj.getUserId();
        this.t_user_name = obj.getUserNickname();
        this.t_user_role = obj.getUserRole();
        usernameLabel.setText(t_user_name);
        initData();
    }

    private void initData(){
        try {
            int column = 0;
            int row = 1;

            projectsList.addAll(getData());
            for (int i = 0; i < projectsList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/dashboard/ItemProject.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemProjectController itemProjectController = fxmlLoader.getController();
                itemProjectController.setData(projectsList.get(i));

                if (column == 4) {
                    column = 0;
                    row++;
                }

                gridProjectPane.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                gridProjectPane.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridProjectPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridProjectPane.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridProjectPane.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridProjectPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridProjectPane.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
    private ObservableList<Project> getData() throws SQLException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();

        Factory factory = new Factory();
        ObservableList<Project> projectsList = FXCollections.observableArrayList();
        Project project = new Project();
        ResultSet resultSet = factory.getProject().selectProject(project,t_user_id);

        while (resultSet.next()){
            projectsList.add(new Project(
                    resultSet.getInt("project_id"),
                    resultSet.getDate("project_date_creation"),
                    resultSet.getTime("project_time_creation"),
                    resultSet.getDate("project_term_delivery"),
                    resultSet.getDouble("project_cost_delivery"),
                    resultSet.getString("project_name"),
                    resultSet.getString("project_description"),
                    resultSet.getInt("project_user_id")
            ));
        };
           // project.setProject_date_creation(dtf.format(localDate));

        return projectsList;
    }

}
