package models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Risk {

    private SimpleIntegerProperty risk_id;
    private SimpleStringProperty risk_name;
    private SimpleDoubleProperty risk_loss_income;
    private SimpleIntegerProperty risk_loss_time_inday;
    private SimpleStringProperty project_name;
    private SimpleIntegerProperty project_id;
    private SimpleIntegerProperty project_user_id;


    public Risk(){}
    public Risk(int risk_id, String risk_name, double risk_loss_income, int risk_loss_time_inday,String project_name, int project_id, int project_user_id) {
        this.risk_id = new SimpleIntegerProperty(risk_id) ;
        this.risk_name = new SimpleStringProperty(risk_name);
        this.risk_loss_income = new SimpleDoubleProperty(risk_loss_income) ;
        this.risk_loss_time_inday = new SimpleIntegerProperty(risk_loss_time_inday);
        this.project_name =  new SimpleStringProperty(project_name);
        this.project_id = new SimpleIntegerProperty(project_id);
        this.project_user_id = new SimpleIntegerProperty(project_user_id);
    }

    public int getRisk_id() {
        return risk_id.get();
    }

    public void setRisk_id(int risk_id) {
        this.risk_id.set(risk_id);
    }

    public String getRisk_name() {
        return risk_name.get();
    }

    public void setRisk_name(String risk_name) {
        this.risk_name.set(risk_name);
    }

    public double getRisk_loss_income() {
        return risk_loss_income.get();
    }

    public void setRisk_loss_income(double risk_loss_income) {
        this.risk_loss_income.set(risk_loss_income);
    }

    public int getRisk_loss_time_inday() {
        return risk_loss_time_inday.get();
    }

    public void setRisk_loss_time_inday(int risk_loss_time_inday) {
        this.risk_loss_time_inday.set(risk_loss_time_inday);
    }

    public String getProject_name() {
        return project_name.get();
    }

    public void setProject_name(String project_name) {
        this.project_name.set(project_name);
    }

    public int getProject_id() {
        return project_id.get();
    }

    public void setProject_id(int project_id) {
        this.project_id.set(project_id);
    }

    public int getProject_user_id() {
        return project_user_id.get();
    }

    public void setProject_user_id(int project_user_id) {
        this.project_user_id.set(project_user_id);
    }
}
