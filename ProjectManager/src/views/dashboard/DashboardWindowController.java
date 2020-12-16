package views.dashboard;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DashboardWindowController {

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
    private VBox pageProjects;

    @FXML
    private VBox pageTasks;

    @FXML
    private VBox pageFinance;

    @FXML
    private VBox pageReports;

    @FXML
    void initialize() {
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
    }
}
