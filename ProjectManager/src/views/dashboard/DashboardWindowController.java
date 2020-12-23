package views.dashboard;

import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;

import java.net.URL;
import java.net.UnknownHostException;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import poi.WordWriter;
import views.AddWindowProjectController;
import views.AddWindowRiskController;
import views.AddWindowTaskController;
import database.Factory;
import models.*;


public class DashboardWindowController implements Initializable {

    //region Variables
    //private int t_user_role;
    private int t_user_id;
    private String t_user_name;
    private ObservableList<Project> projectsList = FXCollections.observableArrayList();
    private ObservableList<Task> tasksList = FXCollections.observableArrayList();
    private ObservableList<Report> reportList = FXCollections.observableArrayList();
    private ObservableList<Risk> risksList = FXCollections.observableArrayList();
    private ObservableList<ActivityLog> activityLogsList = FXCollections.observableArrayList();

    private Desktop desktop = Desktop.getDesktop();
    final FileChooser fileChooser = new FileChooser();
    //endregion

    //region Dashboard
    @FXML
    private Label usernameLabel;

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
    //endregion

    //region Project
    @FXML
    private TableView<Project> projectTable;
    @FXML
    private TableColumn<Project, String> colIdProject;
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
    @FXML
    private Button buttonAddProject;
    @FXML
    void getAddViewProject(MouseEvent event) {
        try{
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
    }  //Insert
    @FXML
    void buttonClickUpdateProject(MouseEvent event) throws IOException, SQLException {

        Project project = projectTable.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/AddWindowProject.fxml"));
        loader.load();

        Parent root =  loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UTILITY);

        AddWindowProjectController addWindowProjectController = (AddWindowProjectController) loader.getController();
        addWindowProjectController.setUpdate(true);
        addWindowProjectController.setTextField(project.getProject_id(),project.getProject_name(),project.getProject_cost_delivery(),project.getProject_description());

        stage.show();
        refreshDataProject();
    } //Update
    @FXML
    void buttonClickDeleteProject(MouseEvent event) {
        Factory factory = new Factory();
        Project project = new Project(projectTable.getSelectionModel().getSelectedItem().getProject_id());
        factory.getProject().deleteProject(project);
    } //Delete
    @FXML
    void refreshProjectTable(ActionEvent event) throws SQLException {
        refreshDataProject();
    }

    //ProjectMethods
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
                    resultSet.getString("project_cost_delivery"),
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
                    resultSet.getString("project_cost_delivery"),
                    resultSet.getString("project_name"),
                    resultSet.getString("project_description"),
                    resultSet.getInt("project_user_id")
            ));
        };
        return projectsList;
    }
    //endregion

    //region Task
    @FXML
    private TableView<Task> taskTable;
    @FXML
    private TableColumn<Task, String> colIDTask;
    @FXML
    private TableColumn<Task, String> colNameTask;
    @FXML
    private TableColumn<Task, String> colDeskTask;
    @FXML
    private TableColumn<Task, String> colStateTask;
    @FXML
    private TableColumn<Task, String> colPryorityTask;
    @FXML
    private TableColumn<Task, String> colNameProjectTask;
    @FXML
    private ComboBox<Project> comboSelectProjectTask;
    @FXML
    void getAddViewTask(MouseEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/AddWindowTask.fxml"));
            loader.load();

            Parent root =  loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UTILITY);

            AddWindowTaskController addWindowTaskController = (AddWindowTaskController) loader.getController();
            addWindowTaskController.setProject_id(comboSelectProjectTask.getSelectionModel().getSelectedItem().getProject_id());

            stage.show();
        }catch (IOException e){
            Logger.getLogger(DashboardWindowController.class.getName()).log(Level.SEVERE, null,e);
        }
    } //Add
    @FXML
    void buttonClickUpdateTask(MouseEvent event) throws IOException, SQLException {
        Task task = taskTable.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/AddWindowTask.fxml"));
        loader.load();

        Parent root =  loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UTILITY);

        AddWindowTaskController addWindowTaskController = (AddWindowTaskController) loader.getController();
        addWindowTaskController.setUpdate(true);
        addWindowTaskController.setTextField(task.getTask_name(),task.getTask_note(),task.getTask_id());

        stage.show();
    } //Update
    @FXML
    void buttonClickDeleteTask(MouseEvent event) {
        Factory factory = new Factory();
        Task task = new Task(taskTable.getSelectionModel().getSelectedItem().getTask_id());
        factory.getTask().deleteTask(task);
    }  //Delete
    @FXML
    void refreshTaskTable(ActionEvent event) throws SQLException {
        refreshDataTask(comboSelectProjectTask.getSelectionModel().getSelectedItem().getProject_id());
    }

    //TaskMethods
    private void loadComboBoxTask() throws SQLException {

        Factory factory = new Factory();
        ObservableList<Project> projectsList = FXCollections.observableArrayList();
        Project project = new Project();
        ResultSet resultSet = factory.getProject().selectProject(project,t_user_id);

        while (resultSet.next()){
            projectsList.add(new Project(
                    resultSet.getInt("project_id"),
                    resultSet.getString("project_name")
            ));
        };
        comboSelectProjectTask.setItems(projectsList);
    }
    private void refreshDataTask(int id) throws SQLException {
        tasksList.clear();
        Factory factory = new Factory();
        Task task = new Task(id,t_user_id);
        ResultSet resultSet = factory.getTask().selectTask(task);
        while (resultSet.next()){
            tasksList.add(new Task(
                    resultSet.getInt("task_id"),
                    resultSet.getString("task_name"),
                    resultSet.getString("task_note"),
                    resultSet.getString("state_name"),
                    resultSet.getString("prioroty_name"),
                    resultSet.getString("project_name"),
                    resultSet.getInt("state_id"),
                    resultSet.getInt("priority_id"),
                    resultSet.getInt("project_user_id"),
                    resultSet.getInt("project_id")
            ));
            taskTable.setItems(tasksList);
        };
    }
    private void refreshDataTask() throws SQLException {
        tasksList.clear();
        Factory factory = new Factory();
        Task task = new Task();
        ResultSet resultSet = factory.getTask().selectTask(task);
        while (resultSet.next()){
            tasksList.add(new Task(
                    resultSet.getInt("task_id"),
                    resultSet.getString("task_name"),
                    resultSet.getString("task_note"),
                    resultSet.getString("state_name"),
                    resultSet.getString("prioroty_name"),
                    resultSet.getString("project_name"),
                    resultSet.getInt("state_id"),
                    resultSet.getInt("priority_id"),
                    resultSet.getInt("project_user_id"),
                    resultSet.getInt("project_id")
            ));
            taskTable.setItems(tasksList);
        };
    }
    private void loadDataTask() throws SQLException {
        colIDTask.setCellValueFactory(new PropertyValueFactory<>("task_id"));
        colNameTask.setCellValueFactory(new PropertyValueFactory<>("task_name"));
        colDeskTask.setCellValueFactory(new PropertyValueFactory<>("task_note"));
        colStateTask.setCellValueFactory(new PropertyValueFactory<>("state_name"));
        colPryorityTask.setCellValueFactory(new PropertyValueFactory<>("priority_name"));
        colNameProjectTask.setCellValueFactory(new PropertyValueFactory<>("project_name"));
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
                    resultSet.getInt("state_id"),
                    resultSet.getInt("priority_id"),
                    resultSet.getInt("project_user_id"),
                    resultSet.getInt("project_id")
            ));
        };
        return tasksList;
    }
    //endregion

    //region Risk
    @FXML
    private Label calc_day_delivery;
    @FXML
    private Label calc_cost_delivery;
    @FXML
    private Label cost_delivery;
    @FXML
    private Label day_delivery;
    @FXML
    private TableView<Risk> riskTable;
    @FXML
    private TableColumn<Risk, String> colIDRisk;
    @FXML
    private TableColumn<Risk, String> colNameRisk;
    @FXML
    private TableColumn<Risk, String> colRiskLossIncome;
    @FXML
    private TableColumn<Risk, String> colRiskLossInday;
    @FXML
    private TableColumn<Risk, String> colRiskProject;
    @FXML
    private TableColumn<Risk, String> colAvaibilityRisk;
    @FXML
    private ComboBox<Project> comboSelectProjectRisk2;
    @FXML
    void getAddViewRisk(MouseEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/AddWindowRisk.fxml"));
            loader.load();

            Parent root =  loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UTILITY);

            AddWindowRiskController addWindowRiskController = (AddWindowRiskController) loader.getController();
            addWindowRiskController.setProject_id(comboSelectProjectRisk2.getSelectionModel().getSelectedItem().getProject_id());

            stage.show();
        }catch (IOException e){
            Logger.getLogger(DashboardWindowController.class.getName()).log(Level.SEVERE, null,e);
        }
    } //Add
    @FXML
    void buttonClickUpdateRisk(MouseEvent event) throws IOException, SQLException {
        Risk risk = riskTable.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/AddWindowRisk.fxml"));
        loader.load();

        Parent root =  loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UTILITY);

        AddWindowRiskController addWindowRiskController = (AddWindowRiskController) loader.getController();
        addWindowRiskController.setUpdate(true);
        addWindowRiskController.setTextField(risk.getRisk_id(),risk.getRisk_loss_income(),risk.getRisk_loss_time_inday(),risk.getRisk_name());

        stage.show();
        refreshDataRisk();
    } //Update
    @FXML
    void buttonClickDeleteRisk(MouseEvent event) {
        Factory factory = new Factory();
        Risk risk = new Risk(riskTable.getSelectionModel().getSelectedItem().getRisk_id());
        factory.getRisk().deleteRisk(risk);
    } //Delete

    //Methods Risk
    private void refreshDataRisk(int id) throws SQLException {
        risksList.clear();
        Factory factory = new Factory();
        Risk risk = new Risk(id,t_user_id);
        ResultSet resultSet = factory.getRisk().selectRisk(risk);

        while (resultSet.next()){
            risksList.add(new Risk(
                    resultSet.getInt("risk_id"),
                    resultSet.getString("risk_name"),
                    resultSet.getString("risk_loss_income"),
                    resultSet.getString("risk_loss_time_inday"),
                    resultSet.getString("project_name"),
                    resultSet.getString("availability_name"),
                    resultSet.getInt("project_id"),
                    resultSet.getInt("project_user_id")
            ));
        };
        riskTable.setItems(risksList);
    };
    private void refreshDataRisk() throws SQLException {

        risksList.clear();
        Factory factory = new Factory();
        Risk risk = new Risk(comboSelectProjectRisk2.getSelectionModel().getSelectedItem().getProject_id(),t_user_id);
        ResultSet resultSet = factory.getRisk().selectRisk(risk);

        while (resultSet.next()){
            risksList.add(new Risk(
                    resultSet.getInt("risk_id"),
                    resultSet.getString("risk_name"),
                    resultSet.getString("risk_loss_income"),
                    resultSet.getString("risk_loss_time_inday"),
                    resultSet.getString("project_name"),
                    resultSet.getString("availability_name"),
                    resultSet.getInt("project_id"),
                    resultSet.getInt("project_user_id")
            ));
        };
        riskTable.setItems(risksList);
    };
    private void loadComboBoxRisk() throws SQLException {
       /* Factory factory = new Factory();
        ObservableList<Project> projectsList = FXCollections.observableArrayList();
        Project project = new Project();
        ResultSet resultSet = factory.getProject().selectProject(project,t_user_id);
        while (resultSet.next()){
            projectsList.add(new Project(
                    resultSet.getInt("project_id"),
                    resultSet.getString("project_name")
            ));
        };*/
        comboSelectProjectRisk2.setItems(getDataProject());
    }
    private void loadDataRisk() throws SQLException {
        colIDRisk.setCellValueFactory(new PropertyValueFactory<>("risk_id"));
        colNameRisk.setCellValueFactory(new PropertyValueFactory<>("risk_name"));
        colRiskLossInday.setCellValueFactory(new PropertyValueFactory<>("risk_loss_time_inday"));
        colRiskLossIncome.setCellValueFactory(new PropertyValueFactory<>("risk_loss_income"));
        colRiskProject.setCellValueFactory(new PropertyValueFactory<>("project_name"));
        colAvaibilityRisk.setCellValueFactory(new PropertyValueFactory<>("availability_name"));
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
                    resultSet.getString("risk_loss_income"),
                    resultSet.getString("risk_loss_time_inday"),
                    resultSet.getString("project_name"),
                    resultSet.getString("availability_name"),
                    resultSet.getInt("project_id"),
                    resultSet.getInt("project_user_id")
            ));
        };
        return risksList;
    }
    //endregion

    //region Report
    @FXML
    private Button exportWordReport;
    @FXML
    private HBox hBoxReportPane;
    @FXML
    private ScrollPane scrollReportPane;
    @FXML
    private GridPane gridReportPane1;
    @FXML
    void newWordReport(ActionEvent event) throws IOException, SQLException {
            WordWriter word = new WordWriter(getDataProject(),getDataTask(),getDataRisk());
            word.createWordReport();
    }

    //Methods Report
    private void loadDataReport(){
        reportList.clear();
        gridReportPane1.getChildren().removeAll();
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
    
    @FXML
    void exportToExcelActivity(ActionEvent event) throws IOException, SQLException {
        activityLogsList.clear();
        activityTable.setItems(getDataActivity());
        activityLogsList = getDataActivity();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Activity");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.BLACK.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < activityTable.getColumns().size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(String.valueOf(activityTable.getColumns().get(i).getText()));
            cell.setCellStyle(headerCellStyle);
        }

        // Create Other rows and cells with contacts data
        int rowNum = 1;

        for (ActivityLog pj : activityLogsList) {
            System.out.println(pj.getRole_name());
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(pj.getLog_time());
            row.createCell(1).setCellValue(pj.getLog_date());
            row.createCell(2).setCellValue(pj.getUser_email());
            row.createCell(3).setCellValue(pj.getRole_name());
            row.createCell(4).setCellValue(pj.getRole_name());
            row.createCell(5).setCellValue(pj.getLog_user_ip());
        }

        // Resize all columns to fit the content size
        for (int i = 0; i < projectTable.getColumns().size(); i++) {
            sheet.autoSizeColumn(i);
        }
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Save Document");//Заголовок диалога
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel (*.xlsx)", "*.xlsx");//Расширение
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(stage);//Указываем текущую сцену CodeNote.mainStage

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream(file.getAbsoluteFile());
        workbook.write(fileOut);
        fileOut.close();
        dialogAlert("Успешно записан в файл");

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
                    resultSet.getString("project_cost_delivery"),
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
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.BLACK.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        for (int i = 1; i < projectTable.getColumns().size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(String.valueOf(projectTable.getColumns().get(i).getText()));
            cell.setCellStyle(headerCellStyle);
        }

        // Create Other rows and cells with contacts data
        int rowNum = 1;

        for (Project pj : projectsList) {
            System.out.println(pj.getProject_name());
            Row row = sheet.createRow(rowNum++);
            row.createCell(1).setCellValue(pj.getProject_name());
            row.createCell(2).setCellValue(pj.getProject_date_creation());
            row.createCell(3).setCellValue(pj.getProject_time_creation());
            row.createCell(4).setCellValue(pj.getProject_term_delivery());
            row.createCell(5).setCellValue(pj.getProject_cost_delivery());
            row.createCell(6).setCellValue(pj.getProject_description());
        }

        // Resize all columns to fit the content size
        for (int i = 0; i < projectTable.getColumns().size(); i++) {
            sheet.autoSizeColumn(i);
        }

        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Save Document");//Заголовок диалога
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel (*.xlsx)", "*.xlsx");//Расширение
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(stage);//Указываем текущую сцену CodeNote.mainStage

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream(file.getAbsoluteFile());
        workbook.write(fileOut);
        fileOut.close();
        dialogAlert("Успешно записан в файл");
    }
    @FXML
    void exportToExcelRisk(ActionEvent event) throws  IOException, SQLException {

        riskTable.setItems(getDataRisk());


        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Risks");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.BLACK.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        for (int i = 1; i < riskTable.getColumns().size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(String.valueOf(riskTable.getColumns().get(i).getText()));
            cell.setCellStyle(headerCellStyle);
        }

        // Create Other rows and cells with contacts data
        int rowNum = 1;

        for (Risk pj : risksList) {
            System.out.println(pj.getRisk_id());
            Row row = sheet.createRow(rowNum++);
            row.createCell(1).setCellValue(pj.getRisk_name());
            row.createCell(2).setCellValue(pj.getRisk_loss_income());
            row.createCell(3).setCellValue(pj.getRisk_loss_time_inday());
            row.createCell(4).setCellValue(pj.getProject_name());
            row.createCell(5).setCellValue(pj.getAvailability_name());
        }

        // Resize all columns to fit the content size
        for (int i = 0; i < riskTable.getColumns().size(); i++) {
            sheet.autoSizeColumn(i);
        }

        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Save Document");//Заголовок диалога
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel (*.xlsx)", "*.xlsx");//Расширение
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(stage);//Указываем текущую сцену CodeNote.mainStage

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream(file.getAbsoluteFile());
        workbook.write(fileOut);
        fileOut.close();
        dialogAlert("Успешно записан в файл");
    }
    @FXML
    void exportToExcelTask(ActionEvent event) throws IOException, SQLException{
        tasksList.clear();
        taskTable.setItems(getDataTask());

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Risks");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.BLACK.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        for (int i = 1; i < riskTable.getColumns().size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(String.valueOf(riskTable.getColumns().get(i).getText()));
            cell.setCellStyle(headerCellStyle);
        }

        // Create Other rows and cells with contacts data
        int rowNum = 1;

        for (Risk pj : risksList) {
            System.out.println(pj.getRisk_id());
            Row row = sheet.createRow(rowNum++);
            row.createCell(1).setCellValue(pj.getRisk_name());
            row.createCell(2).setCellValue(pj.getRisk_loss_income());
            row.createCell(3).setCellValue(pj.getRisk_loss_time_inday());
            row.createCell(4).setCellValue(pj.getProject_name());
            row.createCell(5).setCellValue(pj.getAvailability_name());
        }

        // Resize all columns to fit the content size
        for (int i = 0; i < riskTable.getColumns().size(); i++) {
            sheet.autoSizeColumn(i);
        }

        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Save Document");//Заголовок диалога
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel (*.xlsx)", "*.xlsx");//Расширение
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(stage);//Указываем текущую сцену CodeNote.mainStage

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream(file.getAbsoluteFile());
        workbook.write(fileOut);
        fileOut.close();
        dialogAlert("Успешно записан в файл");
    }

    public  void createWordReport2() throws IOException {
        //Blank Document
        // создаем модель docx документа,
        // к которой будем прикручивать наполнение (колонтитулы, текст)
        XWPFDocument docxModel = new XWPFDocument();
        CTSectPr ctSectPr = docxModel.getDocument().getBody().addNewSectPr();
        // получаем экземпляр XWPFHeaderFooterPolicy для работы с колонтитулами
        XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(docxModel, ctSectPr);

        // создаем верхний колонтитул Word файла
        CTP ctpHeaderModel = createHeaderModel("ProjectManager          Создан:" + LocalDate.now().toString());
        // устанавливаем сформированный верхний
        // колонтитул в модель документа Word
        XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeaderModel, docxModel);
        headerFooterPolicy.createHeader(
                XWPFHeaderFooterPolicy.DEFAULT,
                new XWPFParagraph[]{headerParagraph}
        );

        // создаем нижний колонтитул docx файла
        CTP ctpFooterModel = createFooterModel("Project Manager - ProjectReport AICGTrade 2020 ");
        // устанавливаем сформированый нижний
        // колонтитул в модель документа Word
        XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooterModel, docxModel);
        headerFooterPolicy.createFooter(
                XWPFHeaderFooterPolicy.DEFAULT,
                new XWPFParagraph[]{footerParagraph}
        );

        // создаем обычный параграф, который будет расположен слева,
        // будет синим курсивом со шрифтом 25 размера
        XWPFParagraph bodyParagraph = docxModel.createParagraph();
        bodyParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun paragraphConfig = bodyParagraph.createRun();
        //  paragraphConfig.setItalic(true);
        paragraphConfig.setFontSize(16);
        // HEX цвет без решетки #
        paragraphConfig.setColor("06357a");
        paragraphConfig.setText(
                "Отчет о статусе проекта\n\n"
        );


        //Write first Text in the beginning
        XWPFParagraph para = docxModel.createParagraph();
        XWPFRun run = para.createRun();
        run.setText("\nНазвание проекта: " + comboSelectProjectRisk2.getSelectionModel().getSelectedItem().getProject_name()+ "\n");
        //Write first Text in the beginning
        XWPFParagraph para2 = docxModel.createParagraph();
        XWPFRun run2 = para.createRun();
        run2.setText("\nСрок внедрения: " + comboSelectProjectRisk2.getSelectionModel().getSelectedItem().getProject_term_delivery()+ "\n");
        //Write first Text in the beginning
        XWPFParagraph para3 = docxModel.createParagraph();
        XWPFRun run3 = para.createRun();
        run3.setText("\nСтоимость: " +  comboSelectProjectRisk2.getSelectionModel().getSelectedItem().getProject_cost_delivery()+ "\n");
        //Write first Text in the beginning
        XWPFParagraph para4 = docxModel.createParagraph();
        XWPFRun run4 = para.createRun();
        run4.setText("\nОписание: " + comboSelectProjectRisk2.getSelectionModel().getSelectedItem().getProject_description()+ "\n");



        //Write first Text in the beginning
        XWPFParagraph para5 = docxModel.createParagraph();
        XWPFRun run5 = para.createRun();
        run5.setText("\n\nРиски проекта\n" );
        //create table
        XWPFTable table = docxModel.createTable();
        //create first row
        XWPFTableRow tableRowOne = table.getRow(0);
        tableRowOne.getCell(0).setText("Описание риска");tableRowOne.addNewTableCell().setText("Потери дохода");tableRowOne.addNewTableCell().setText("Потери дней");tableRowOne.addNewTableCell().setText("Вероятность возникновения");
        for (Risk pj : risksList) {
        XWPFTableRow tableRowTwo = table.createRow();
        tableRowTwo.getCell(0).setText(pj.getRisk_name());tableRowTwo.getCell(1).setText(pj.getRisk_loss_income());tableRowTwo.getCell(2).setText(pj.getRisk_loss_time_inday());tableRowTwo.getCell(3).setText(pj.getAvailability_name());
        }


        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Save Document");//Заголовок диалога
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Word (*.docx)", "*.docx");//Расширение
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(stage);//Указываем текущую сцену CodeNote.mainStage

        FileOutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
        docxModel.write(outputStream);
        outputStream.close();
    }

    private static CTP createFooterModel(String footerContent) {
        // создаем футер или нижний колонтитул
        CTP ctpFooterModel = CTP.Factory.newInstance();
        CTR ctrFooterModel = ctpFooterModel.addNewR();
        CTText cttFooter = ctrFooterModel.addNewT();

        cttFooter.setStringValue(footerContent);
        return ctpFooterModel;
    }

    private static CTP createHeaderModel(String headerContent) {
        // создаем хедер или верхний колонтитул
        CTP ctpHeaderModel = CTP.Factory.newInstance();
        CTR ctrHeaderModel = ctpHeaderModel.addNewR();
        CTText cttHeader = ctrHeaderModel.addNewT();

        cttHeader.setStringValue(headerContent);
        return ctpHeaderModel;
    }

    private void dialogAlert(String str){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(str);
        alert.setHeaderText(null);
        alert.setContentText(str);
        alert.showAndWait();
    }
    //endregion

    //region Settings and Activity
    @FXML
    private VBox settingsPage;
    @FXML
    private TableView<ActivityLog> activityTable;
    @FXML
    private TableColumn<ActivityLog, String> colTimeAcitv;
    @FXML
    private TableColumn<ActivityLog, String> colDateAcitv;
    @FXML
    private TableColumn<ActivityLog, String> colEmailAcitv;
    @FXML
    private TableColumn<ActivityLog, String> colNameUserAcitv;
    @FXML
    private TableColumn<ActivityLog, String> colRootUserAcitv;
    @FXML
    private TableColumn<ActivityLog, String> colIpActiv;
    @FXML
    private HBox settingsButton;
    @FXML
    void refreshActivityTable(MouseEvent event) throws SQLException {
        activityLogsList.clear();
        Factory factory = new Factory();
        ResultSet resultSet = factory.getActivity().selectActivity(t_user_name);
        while (resultSet.next()){
            activityLogsList.add(new ActivityLog(
                    resultSet.getString("log_time"),
                    resultSet.getString("log_date"),
                    resultSet.getString("log_user_ip"),
                    resultSet.getString("user_email"),
                    resultSet.getString("user_nickname"),
                    resultSet.getString("role_name")
            ));
            activityTable.setItems(activityLogsList);
        };
    } // reload activity table
    //MethodsActivity
    private void insertDataActivitiLog() throws UnknownHostException {
        Factory factory = new Factory();
        factory.getActivity().insertActivity(t_user_id);
    }
    private void loadDataActivity() throws SQLException {
        colTimeAcitv.setCellValueFactory(new PropertyValueFactory<>("log_time"));
        colDateAcitv.setCellValueFactory(new PropertyValueFactory<>("log_date"));
        colIpActiv.setCellValueFactory(new PropertyValueFactory<>("log_user_ip"));
        colEmailAcitv.setCellValueFactory(new PropertyValueFactory<>("user_email"));
        colNameUserAcitv.setCellValueFactory(new PropertyValueFactory<>("user_nickname"));
        colRootUserAcitv.setCellValueFactory(new PropertyValueFactory<>("role_name"));
        activityTable.setItems(getDataActivity());
    }
    private ObservableList<ActivityLog> getDataActivity() throws SQLException {
        Factory factory = new Factory();
        ObservableList<ActivityLog> activityLogs = FXCollections.observableArrayList();
        ResultSet resultSet = factory.getActivity().selectActivity(t_user_name);

        while (resultSet.next()){
            activityLogs.add(new ActivityLog(
                    resultSet.getString("log_time"),
                    resultSet.getString("log_date"),
                    resultSet.getString("log_user_ip"),
                    resultSet.getString("user_email"),
                    resultSet.getString("user_nickname"),
                    resultSet.getString("role_name")
            ));
        };
        return activityLogs;
    }
    //endregion

    //region Help
    @FXML
    private VBox helpPage;
    @FXML
    private WebView helpWebView;
    @FXML
    private HBox helpButton;
    //endregion

    //region Initialize FXML and load Data
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exportWordReport.setOnAction(event -> {
            try {
                createWordReport2();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        projectButton.setOnMouseClicked(event -> {
            pageProjects.toFront();
            try {
                loadDataProject();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        taskButton.setOnMouseClicked(event -> {
            pageTasks.toFront();
            try {
                loadDataTask();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                loadComboBoxTask();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        financeButton.setOnMouseClicked(event -> {
            pageFinance.toFront();
            try {
                loadDataRisk();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                loadComboBoxRisk();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        reportButton.setOnMouseClicked(event -> {
            pageReports.toFront();
            loadDataReport();
        });
        settingsButton.setOnMouseClicked(event -> {
            settingsPage.toFront();
            try {
                loadDataActivity();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        helpButton.setOnMouseClicked(event -> {
            helpPage.toFront();
            final WebEngine webEngine = helpWebView.getEngine();
            try {
                File file = new File("ProjectManager/src/help/index.html");
                URL location = file.toURI().toURL();
                System.out.println("Local URL: " + location.toString());
                webEngine.load(location.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
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
        comboSelectProjectTask.setOnAction(event -> {
            try {
                refreshDataTask(comboSelectProjectTask.getSelectionModel().getSelectedItem().getProject_id());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        comboSelectProjectRisk2.setOnAction(event -> {
            Project pr2 = comboSelectProjectRisk2.getSelectionModel().getSelectedItem();
            try {
                day_delivery.setText(pr2.getProject_term_delivery());
                cost_delivery.setText(pr2.getProject_cost_delivery());
                refreshDataRisk(pr2.getProject_id());

                ObservableList<Risk>  riskList = riskTable.getItems();
                int lossIncome = 0;
                System.out.println(cost_delivery.getText());
                double lossResourceIncome = Double.parseDouble(cost_delivery.getText().trim());
                double result = 0;

                for (Risk r: riskList){
                    lossIncome = Integer.parseInt(r.getRisk_loss_income());
                    if(r.getAvailability_name().equals("Нет")){
                        result += 0;
                    }else {
                        result += (lossResourceIncome + (lossIncome * 1));
                    }
                }
                calc_cost_delivery.setText(String.valueOf(result));

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    public void loadData(User obj) throws SQLException, UnknownHostException {
        // this.t_user_role = obj.getUserRole();
        this.t_user_id = obj.getUserId();
        this.t_user_name = obj.getUserNickname();

        usernameLabel.setText(t_user_name);
        insertDataActivitiLog();
        pageProjects.toFront();

        loadDataActivity();
        loadDataProject();
        loadDataTask();
        loadDataRisk();
        loadDataReport();
        loadComboBoxTask();
        loadComboBoxRisk();
    }
    //endregion

}
