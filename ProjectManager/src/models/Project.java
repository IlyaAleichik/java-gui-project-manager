package models;

import java.sql.Time;
import java.util.Date;

public class Project {
    private int project_id;
    private Date project_date_creation;
    private Time project_time_creation;
    private String project_term_delivery;
    private String project_cost_delivery;
    private String project_name;
    private int project_currency_id;
    private String project_description;
    private int project_user_id;


    public Project(){}

    public Project(int project_id,String project_name){
        this.project_name = project_name;
        this.project_id = project_id;
    }

    //deleteDataInizialize
    public Project(int project_id){
        this.project_id = project_id;
    }

    //insertDataInizialaize
    public Project(String project_term_delivery, String project_cost_delivery, String project_name, String project_description, int project_user_id){
        this.project_term_delivery = project_term_delivery;
        this.project_cost_delivery = project_cost_delivery;
        this.project_name = project_name;
        this.project_description = project_description;
        this.project_user_id = project_user_id;
    }

    //updateDataInizialize
    public Project(int project_id, String project_term_delivery, String project_cost_delivery, String project_name, String project_description, int project_user_id){
        this.project_id = project_id;
        this.project_term_delivery = project_term_delivery;
        this.project_cost_delivery = project_cost_delivery;
        this.project_name = project_name;
        this.project_description = project_description;
        this.project_user_id = project_user_id;
    }
    //getDataProject
    public Project(int project_id, Date project_date_creation, Time project_time_creation, String project_term_delivery, String project_cost_delivery, String project_name, String project_description, int project_user_id) {
        this.project_id = project_id;
        this.project_date_creation = project_date_creation;
        this.project_time_creation = project_time_creation;
        this.project_term_delivery = project_term_delivery;
        this.project_cost_delivery = project_cost_delivery;
        this.project_name = project_name;
        this.project_description = project_description;
        this.project_user_id = project_user_id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public Date getProject_date_creation() {
        return project_date_creation;
    }

    public void setProject_date_creation(Date project_date_creation) {
        this.project_date_creation = project_date_creation;
    }

    public Time getProject_time_creation() {
        return project_time_creation;
    }

    public void setProject_time_creation(Time project_time_creation) {
        this.project_time_creation = project_time_creation;
    }

    public String getProject_term_delivery() {
        return project_term_delivery;
    }

    public void setProject_term_delivery(String project_term_delivery) {
        this.project_term_delivery = project_term_delivery;
    }

    public String getProject_cost_delivery() {
        return project_cost_delivery;
    }

    public void setProject_cost_delivery(String project_cost_delivery) {
        this.project_cost_delivery = project_cost_delivery;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public int getProject_currency_id() {
        return project_currency_id;
    }

    public void setProject_currency_id(int project_currency_id) {
        this.project_currency_id = project_currency_id;
    }

    public String getProject_description() {
        return project_description;
    }

    public void setProject_description(String project_description) {
        this.project_description = project_description;
    }

    public int getProject_user_id() {
        return project_user_id;
    }

    public void setProject_user_id(int project_user_id) {
        this.project_user_id = project_user_id;
    }

    @Override
    public String toString() {
        return this.getProject_name();
    }

}
