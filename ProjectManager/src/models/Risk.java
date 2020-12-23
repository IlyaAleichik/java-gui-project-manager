package models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Risk {

    private int risk_id;
    private String risk_name;
    private String risk_loss_income;
    private String risk_loss_time_inday;
    private String project_name;
    private String availability_name;
    private int project_id;
    private int project_user_id;
    private int risk_avaibility_id;



    public Risk(){}

    //delete
    public Risk(int risk_id){
        this.risk_id = risk_id;
    }

    //update
    public Risk(int risk_id, String risk_loss_income, String risk_loss_time_inday, String risk_name, int risk_avaibility_id) {
        this.risk_id =  risk_id;
        this.risk_loss_income = risk_loss_income ;
        this.risk_loss_time_inday = risk_loss_time_inday;
        this.risk_name = risk_name;
        this.risk_avaibility_id = risk_avaibility_id;
    }

    //insert
    public Risk( String risk_loss_income, String risk_loss_time_inday, String risk_name, int risk_avaibility_id,int project_id) {
        this.risk_loss_income = risk_loss_income ;
        this.risk_loss_time_inday = risk_loss_time_inday;
        this.risk_name = risk_name;
        this.risk_avaibility_id = risk_avaibility_id;
        this.project_id =  project_id;
    }

    ///refreshDataRisk for ComboBox
    public Risk(int project_id, int project_user_id){
        this.project_id = project_id;
        this.project_user_id = project_user_id;
    }

    //view
    public Risk(int risk_id, String risk_name, String risk_loss_income, String risk_loss_time_inday,String project_name,String availability_name, int project_id, int project_user_id) {
        this.risk_id = risk_id ;
        this.risk_name = risk_name;
        this.risk_loss_income = risk_loss_income;
        this.risk_loss_time_inday = risk_loss_time_inday;
        this.project_name =  project_name;
        this.availability_name =  availability_name;
        this.project_id = project_id;
        this.project_user_id = project_user_id;
    }


    public int getRisk_id() {
        return risk_id;
    }

    public void setRisk_id(int risk_id) {
        this.risk_id = risk_id;
    }

    public String getRisk_name() {
        return risk_name;
    }

    public void setRisk_name(String risk_name) {
        this.risk_name = risk_name;
    }

    public String getRisk_loss_income() {
        return risk_loss_income;
    }

    public void setRisk_loss_income(String risk_loss_income) {
        this.risk_loss_income = risk_loss_income;
    }

    public String getRisk_loss_time_inday() {
        return risk_loss_time_inday;
    }

    public void setRisk_loss_time_inday(String risk_loss_time_inday) {
        this.risk_loss_time_inday = risk_loss_time_inday;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getAvailability_name() {
        return availability_name;
    }

    public void setAvailability_name(String availability_name) {
        this.availability_name = availability_name;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public int getProject_user_id() {
        return project_user_id;
    }

    public void setProject_user_id(int project_user_id) {
        this.project_user_id = project_user_id;
    }

    public int getRisk_avaibility_id() {
        return risk_avaibility_id;
    }

    public void setRisk_avaibility_id(int risk_avaibility_id) {
        this.risk_avaibility_id = risk_avaibility_id;
    }

}
