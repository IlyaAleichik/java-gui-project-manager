package views.dashboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import models.Project;
import models.Report;

public class ItemReportController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label nameReportLabel;

    @FXML
    private Button projectViewButton;

    @FXML
    private Label dateCreateReportLabel;

    private Report report;

    public void setDataReport(Report _report) {
        this.report = _report;
        nameReportLabel.setText(report.getNameReport());
        dateCreateReportLabel.setText(report.getDateCreationReport().toString());
    }
}