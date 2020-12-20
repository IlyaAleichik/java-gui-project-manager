package views.dashboard;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.Factory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.*;
import javafx.scene.control.Label;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import poi.WordWorker;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.control.Button;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import views.AddWindowProjectController;
import javafx.scene.control.TextField;

public class DashboardWindowController implements Initializable {

    private int t_user_id;
   //private int t_user_role;
    private String t_user_name;

    private ObservableList<Project> projectsList = FXCollections.observableArrayList();
    private ObservableList<Task> tasksList = FXCollections.observableArrayList();
    private ObservableList<Report> reportList = FXCollections.observableArrayList();
    private ObservableList<Risk> risks = FXCollections.observableArrayList();

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
    private VBox pageTasks;

    @FXML
    private VBox pageFinance;

    @FXML
    private ScrollPane scrollProjectPane;

    @FXML
    private GridPane gridProjectPane;




    //Project Table View
    @FXML
    private TableView<Project> projectTable;

    @FXML
    private TableColumn<Project, String> colProjectName;

    @FXML
    private TableColumn<Project,String> colProjectCreateData;

    @FXML
    private TableColumn<Project, String> colProjectCreateTime;

    @FXML
    private TableColumn<Project, String> colProjectTermDeliv;

    @FXML
    private TableColumn<Project, String> colProjectCostDeliv;

    @FXML
    private TableColumn<Project, String> colProjectDescription;

    @FXML
    private TextField searchTextFieldProject;

    //Task Table View
    @FXML
    private TableView<Task> taskTable;

    @FXML
    private TableColumn<Task, String> colNameTask;

    @FXML
    private TableColumn<Task, String> colDeskTask;

    @FXML
    private TableColumn<Task, String> colStateTask;

    @FXML
    private TableColumn<Task, String> colPryorityTask;



    //Risk Table View

    @FXML
    private TableView<Risk> riskTable;

    @FXML
    private TableColumn<Risk, String> colNameRisk;

    @FXML
    private TableColumn<Risk, String> colRiskLossIncome;

    @FXML
    private TableColumn<Risk, String> colRiskLossInday;

    @FXML
    private TableColumn<Risk, String> colRiskProject;

    /////////////

    @FXML
    private TableColumn<Task, String> colProjTask;

    @FXML
    private HBox hBoxReportPane;

    @FXML
    private ScrollPane scrollReportPane;

    @FXML
    private GridPane gridReportPane1;

    @FXML
    private Button buttonAddProject;

    @FXML
    void getAddViewProject(MouseEvent event) {
        try{
            /*
            Parent parent = FXMLLoader.load(getClass().getResource("/views/AddWindowProject.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();*/
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/AddWindowProject.fxml"));
            loader.load();

            Parent root =  loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UTILITY);

            AddWindowProjectController addWindowProjectController = (AddWindowProjectController) loader.getController();
            addWindowProjectController.setUser_id(t_user_id);

            stage.show();
        }catch (IOException e){
            Logger.getLogger(DashboardWindowController.class.getName()).log(Level.SEVERE, null,e);
        }
    }

    @FXML
    void newWordReport(ActionEvent event) {
        int count = 0;
        WordWorker word = new WordWorker("./doc/");
        word.createWordReport("newReport.docx");
    }

    @FXML
    void refreshProjectTable(ActionEvent event) throws SQLException {
        refreshDataProject();
    }

    @FXML
    void exportToExcelProject(ActionEvent event) throws IOException, SQLException {

        projectsList.clear();
        Factory factory = new Factory();
        Project project = new Project();
        ResultSet resultSet = factory.getProject().selectProject(project,t_user_id);
        while (resultSet.next()){
            projectsList.add(new Project(
                    resultSet.getInt("project_id"),
                    resultSet.getDate("project_date_creation"),
                    resultSet.getTime("project_time_creation"),
                    resultSet.getString("project_term_delivery"),
                    resultSet.getDouble("project_cost_delivery"),
                    resultSet.getString("project_name"),
                    resultSet.getString("project_description"),
                    resultSet.getInt("project_user_id")
            ));
            projectTable.setItems(projectsList);
        };

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Projects");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setColor(IndexedColors.RED.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Create a Row
            Row headerRow = sheet.createRow(0);

            for (int i = 0; i < projectTable.getColumns().size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(String.valueOf(projectTable.getColumns().get(i).getText()));
                cell.setCellStyle(headerCellStyle);
            }

            // Create Other rows and cells with contacts data
            int rowNum = 1;

            for (Project pj : projectsList) {
                System.out.println(pj.getProject_name());
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(pj.getProject_name());
                row.createCell(1).setCellValue(pj.getProject_term_delivery());
                row.createCell(2).setCellValue(pj.getProject_description());
            }

            // Resize all columns to fit the content size
            for (int i = 0; i < projectTable.getColumns().size(); i++) {
                sheet.autoSizeColumn(i);
            }

            // Write the output to a file
            FileOutputStream fileOut = new FileOutputStream("./doc/projects.xlsx");
            workbook.write(fileOut);
            fileOut.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dashboardButton.setOnMouseClicked(event -> {
            pageDash.toFront();
        });
        projectButton.setOnMouseClicked(event -> {
            pageProjects.toFront();
        });
        taskButton.setOnMouseClicked(event -> {
            pageTasks.toFront();
        });
        financeButton.setOnMouseClicked(event -> {
            pageFinance.toFront();
        });
        reportButton.setOnMouseClicked(event -> {
            pageReports.toFront();
        });

        searchTextFieldProject.setOnAction(event -> {

            // Wrap the ObservableList in a FilteredList (initially display all data).
            FilteredList<Project> filteredData = new FilteredList<>(projectsList, b -> true);

            // 2. Set the filter Predicate whenever the filter changes.
            searchTextFieldProject.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(proj -> {
                    // If filter text is empty, display all persons.

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (proj.getProject_name().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true; // Filter matches first name.
                    } else if (String.valueOf(proj.getProject_date_creation()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    } else if (String.valueOf(proj.getProject_time_creation()).indexOf(lowerCaseFilter)!=-1){
                        return true; // Filter matches last name.
                    }else if (String.valueOf(proj.getProject_term_delivery()).indexOf(lowerCaseFilter)!=-1){
                        return true; // Filter matches last name.
                    }else if (String.valueOf(proj.getProject_cost_delivery()).indexOf(lowerCaseFilter)!=-1){
                        return true; // Filter matches last name.
                    }else if (String.valueOf(proj.getProject_description()).indexOf(lowerCaseFilter)!=-1)
                        return true;
                    else
                        return false; // Does not match.
                });
            });

            // 3. Wrap the FilteredList in a SortedList.
            SortedList<Project> sortedData = new SortedList<>(filteredData);

            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(projectTable.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            projectTable.setItems(sortedData);
        });
    }



    public void loadData(User obj) throws SQLException {
        this.t_user_id = obj.getUserId();
        this.t_user_name = obj.getUserNickname();
       // this.t_user_role = obj.getUserRole();

        usernameLabel.setText(t_user_name);


        loadDataProject();
        loadDataTask();
        loadDataRisk();
        loadDataReport();
    }


    //Project



    private void refreshDataProject() throws SQLException {

        projectsList.clear();
        Factory factory = new Factory();
        Project project = new Project();
        ResultSet resultSet = factory.getProject().selectProject(project,t_user_id);
        while (resultSet.next()){
            projectsList.add(new Project(
                    resultSet.getInt("project_id"),
                    resultSet.getDate("project_date_creation"),
                    resultSet.getTime("project_time_creation"),
                    resultSet.getString("project_term_delivery"),
                    resultSet.getDouble("project_cost_delivery"),
                    resultSet.getString("project_name"),
                    resultSet.getString("project_description"),
                    resultSet.getInt("project_user_id")
            ));
            projectTable.setItems(projectsList);
        };
    }

    private void loadDataProject() throws SQLException {
        colProjectName.setCellValueFactory(new PropertyValueFactory<>("project_name"));
        colProjectCreateData.setCellValueFactory(new PropertyValueFactory<>("project_date_creation"));
        colProjectCreateTime.setCellValueFactory(new PropertyValueFactory<>("project_time_creation"));
        colProjectTermDeliv.setCellValueFactory(new PropertyValueFactory<>("project_term_delivery"));
        colProjectCostDeliv.setCellValueFactory(new PropertyValueFactory<>("project_cost_delivery"));
        colProjectDescription.setCellValueFactory(new PropertyValueFactory<>("project_description"));
        projectTable.setItems(getDataProject());
    }
    private ObservableList<Project> getDataProject() throws SQLException {
        Factory factory = new Factory();
        ObservableList<Project> projectsList = FXCollections.observableArrayList();
        Project project = new Project();
        ResultSet resultSet = factory.getProject().selectProject(project,t_user_id);

        while (resultSet.next()){
            projectsList.add(new Project(
                    resultSet.getInt("project_id"),
                    resultSet.getDate("project_date_creation"),
                    resultSet.getTime("project_time_creation"),
                    resultSet.getString("project_term_delivery"),
                    resultSet.getDouble("project_cost_delivery"),
                    resultSet.getString("project_name"),
                    resultSet.getString("project_description"),
                    resultSet.getInt("project_user_id")
            ));
        };
        return projectsList;
    }

    //Task
    private void loadDataTask() throws SQLException {
        colNameTask.setCellValueFactory(new PropertyValueFactory<>("task_name"));
        colDeskTask.setCellValueFactory(new PropertyValueFactory<>("task_note"));
        colStateTask.setCellValueFactory(new PropertyValueFactory<>("state_name"));
        colPryorityTask.setCellValueFactory(new PropertyValueFactory<>("priority_name"));
        colProjTask.setCellValueFactory(new PropertyValueFactory<>("project_name"));
        taskTable.setItems(getDataTask());
    }
    private ObservableList<Task> getDataTask() throws SQLException {
        ObservableList<Task> tasksList = FXCollections.observableArrayList();
        Task task = new Task();

        Factory factory = new Factory();
        ResultSet resultSet = factory.getTask().selectTask(task);

        while (resultSet.next()){
            tasksList.add(new Task(
                    resultSet.getInt("task_id"),
                    resultSet.getString("task_name"),
                    resultSet.getString("task_note"),
                    resultSet.getString("state_name"),
                    resultSet.getString("prioroty_name"),
                    resultSet.getString("project_name"),
                    resultSet.getInt("project_user_id")
            ));
        };
        return tasksList;
    }

    //Risk
    private void loadDataRisk() throws SQLException {
        colNameRisk.setCellValueFactory(new PropertyValueFactory<>("risk_name"));
        colRiskLossInday.setCellValueFactory(new PropertyValueFactory<>("risk_loss_time_inday"));
        colRiskLossIncome.setCellValueFactory(new PropertyValueFactory<>("risk_loss_income"));
        colRiskProject.setCellValueFactory(new PropertyValueFactory<>("project_name"));
        riskTable.setItems(getDataRisk());
    }
    private ObservableList<Risk> getDataRisk() throws SQLException {
        ObservableList<Risk> risksList = FXCollections.observableArrayList();
        Risk risk = new Risk();

        Factory factory = new Factory();
        ResultSet resultSet = factory.getRisk().selectRisk(risk);

        while (resultSet.next()){
            risksList.add(new Risk(
                    resultSet.getInt("risk_id"),
                    resultSet.getString("risk_name"),
                    resultSet.getDouble("risk_loss_income"),
                    resultSet.getInt("risk_loss_time_inday"),
                    resultSet.getString("project_name"),
                    resultSet.getInt("project_id"),
                    resultSet.getInt("project_user_id")
            ));
        };
        return risksList;
    }

    //Report
    private void loadDataReport(){
        try {
            int column = 0;
            int row = 1;
            final File folder = new File("./doc/");

            reportList.addAll(listFilesForFolder(folder));
            for (int i = 0; i < reportList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/dashboard/ItemReport.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemReportController itemReportController = fxmlLoader.getController();
                itemReportController.setDataReport(reportList.get(i));

                if (column == 4) {
                    column = 0;
                    row++;
                }
                gridReportPane1.getChildren().removeAll();
                gridReportPane1.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                gridReportPane1.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridReportPane1.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridReportPane1.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridReportPane1.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridReportPane1.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridReportPane1.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Report> listFilesForFolder(final File folder) throws IOException {
        ObservableList<Report> reportList = FXCollections.observableArrayList();
        BasicFileAttributes attr;
        String fileTime;
        reportList.removeAll();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
              //  listFilesForFolder(fileEntry);
            } else {
                 attr = Files.readAttributes(fileEntry.toPath().getParent(), BasicFileAttributes.class);
                 fileTime = attr.creationTime().toString();
                String fileName = fileEntry.getName();
                System.out.println(fileName);
                reportList.add(new Report(fileName,fileTime) );
            }
        }
        return  reportList;
    }






}
