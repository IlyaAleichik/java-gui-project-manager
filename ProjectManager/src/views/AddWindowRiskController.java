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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.AvaibilityRisk;
import models.Project;
import models.Risk;

public class AddWindowRiskController {


    ObservableList<AvaibilityRisk>  avaibilityRisks = FXCollections.observableArrayList();
    private int risk_id;
    private boolean update;
    private int  avaibility_id;
    private int project_id;
    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }



    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textFieldNameRisk;

    @FXML
    private TextField textFieldLossIncomeRisk;

    @FXML
    private TextField textFieldLossIndayRisk;

    @FXML
    private ComboBox<AvaibilityRisk> coboBoxAvailabilityRisk;

    @FXML
    private Button buttonCleanRisk;

    @FXML
    private Button buttonAboutRisk;

    @FXML
    private Button buttonAceptRisk;

    @FXML
    void aboutAddRisk(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void acceptAddRisk(MouseEvent event) {
        if (update == false) {

            Factory factory = new Factory();

            String NameRisk =  textFieldNameRisk.getText();
            String LossIncomeRisk = textFieldLossIncomeRisk.getText();
            String LossIndayRisk = textFieldLossIndayRisk.getText();

            Risk risk = new Risk(LossIncomeRisk, LossIndayRisk,NameRisk,avaibility_id, project_id);

            if (NameRisk.equals("")|| LossIncomeRisk.equals("")|| LossIndayRisk.equals("")){
                dialogAlert("Input error", "Пожалуйста введите все данные");
            }else {
                factory.getRisk().insertRisk(risk);
                dialogAlert("", "Данные успешно добавлены");
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }


        }else{
            Factory factory = new Factory();
            String NameRisk =  textFieldNameRisk.getText();
            String LossIncomeRisk = textFieldLossIncomeRisk.getText();
            String LossIndayRisk = textFieldLossIndayRisk.getText();

            Risk risk = new Risk(risk_id,LossIncomeRisk, LossIndayRisk,NameRisk,avaibility_id);

            if (NameRisk.equals("") || LossIncomeRisk.equals("") || LossIndayRisk.equals("")  ){
                dialogAlert("Input error", "Пожалуйста введите все данные");
            }else {
                factory.getRisk().updateRisk(risk);
                //System.out.println(project_id);
                dialogAlert("", "Данные успешно обнавлены");
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }
        }
    }

    @FXML
    void cleanAddRisk(MouseEvent event) {

    }

    @FXML
    void initialize() throws SQLException {
        loadComboBoxRisk();
        coboBoxAvailabilityRisk.setOnAction(event -> {
            AvaibilityRisk pr = coboBoxAvailabilityRisk.getSelectionModel().getSelectedItem();
            avaibility_id = pr.getId_avaibilyty();
        });
    }
    private void loadComboBoxRisk() throws SQLException {

        Factory factory = new Factory();
        ObservableList<AvaibilityRisk> avaibilityRisks = FXCollections.observableArrayList();
        AvaibilityRisk avaibilityRisk = new AvaibilityRisk();
        ResultSet resultSet = factory.getRisk().selectAvailabilityRisk();

        while (resultSet.next()){
            avaibilityRisks.add(new AvaibilityRisk(
                    resultSet.getInt("availability_id"),
                    resultSet.getString("availability_name")
            ));
        };
        coboBoxAvailabilityRisk.setItems(avaibilityRisks);
    }





    public void setUpdate(boolean b) {
        this.update = b;
    }

    public void setTextField(int risk_id, String risk_loss_income, String risk_loss_time_inday, String risk_name) {
        this.risk_id = risk_id;
        textFieldLossIncomeRisk.setText(risk_loss_income);
        textFieldLossIndayRisk.setText(risk_loss_time_inday);
        textFieldNameRisk.setText(risk_name);
    }
    private void dialogAlert(String type ,String str){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(type);
        alert.setHeaderText(null);
        alert.setContentText(str);
        alert.showAndWait();
    }
}
