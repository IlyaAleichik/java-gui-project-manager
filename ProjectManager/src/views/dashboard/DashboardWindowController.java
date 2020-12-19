package views.dashboard;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import models.Project;

public class DashboardWindowController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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

    private List<Project> projects = new ArrayList<>();

    private List<Project> getData() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();

        List<Project> projects = new ArrayList<>();
        Project project;

        for (int i = 0; i < 10; i++){
            project = new Project();
            project.setProject_name("Darktable");
            project.setImgSrc("../img/darktable_icon.png");
            project.setProject_date_creation(dtf.format(localDate));
            project.setProject_description("Project fot ");
            projects.add(project);
        }
        return projects;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dashboardButton.setOnMouseClicked(event -> {
            pageDash.toFront();
        });
        projectButton.setOnMouseClicked(event -> {
            pageProjects.toFront();

        });
        reportButton.setOnMouseClicked(event -> {
            pageReports.toFront();
        });

        projects.addAll(getData());
        int column = 0;
        int row = 1;

        try {
            for (int i = 0; i < projects.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/dashboard/ItemProject.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemProjectController itemProjectController = fxmlLoader.getController();
                itemProjectController.setData(projects.get(i));

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
