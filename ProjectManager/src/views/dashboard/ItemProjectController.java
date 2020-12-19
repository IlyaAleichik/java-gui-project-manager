package views.dashboard;

import com.gluonhq.charm.glisten.control.ProgressBar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Project;

public class ItemProjectController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imgProjectImageView;

    @FXML
    private Button projectViewButton;

    @FXML
    private Label nameProjectLabel;

    @FXML
    private Label descriptionProjectLabel;

    @FXML
    private ProgressBar progressProjectBar;

    @FXML
    private Label dateCreateProjectLabel;

    private Project project;

    Image image;

    public void setData(Project project) {
        this.project = project;
        nameProjectLabel.setText(project.getProject_name());
        descriptionProjectLabel.setText(project.getProject_description());
        dateCreateProjectLabel.setText(project.getProject_date_creation().toString());
        imgProjectImageView.setImage(image);
    }
}
