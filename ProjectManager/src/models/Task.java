package models;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.concurrent.RecursiveAction;

public class Task{

    private int task_id;
    private String  task_name;
    private String  task_note;
    private String  state_name;
    private String  priority_name;
    private String  project_name;
    private int state_id;
    private int priority_id;
    private int project_user_id;
    private int project_id;


    public Task(){}

    //delete
    public Task(int task_id){
        this.task_id = task_id;
    }

    //update
    public Task(String task_name,String task_note,int state_id, int priority_id, int task_id){
        this.task_name = task_name;
        this.task_note = task_note;
        this.state_id = state_id;
        this.priority_id = priority_id;
        this.task_id = task_id;
    }
    //insert
    public Task(int project_id, String task_name,String task_note,int state_id, int priority_id){
        this.task_name = task_name;
        this.task_note = task_note;
        this.state_id = state_id;
        this.priority_id = priority_id;
        this.project_id = project_id;
    }

    //selectComboBox
    public Task(int project_id, int user_id ){
        this.project_id = project_id;
        this.project_user_id = user_id;
    }

    //view select
    public Task(int task_id, String task_name, String task_note, String state_name, String priority_name, String project_name, int state_id,int priority_id, int project_user_id, int project_id) {
            this.task_id =  task_id;
            this.task_name =  task_name;
            this.task_note =  task_note;;
            this.state_name =  state_name;;
            this.priority_name =  priority_name;;
            this.project_name = project_name;;
            this.state_id = state_id;
            this.priority_id = priority_id;
            this.project_user_id = project_user_id;
            this.project_id = project_id;
        }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name= task_name;
    }

    public String getTask_note() {
        return task_note;
    }

    public void setTask_note(String task_note) {
        this.task_note = task_note;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getPriority_name() {
        return priority_name;
    }

    public void setPriority_name(String priority_name) { this.priority_name= priority_name; }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public int getState_id() {
        return state_id;
    }

    public void setState_id(int state_id) {
        this.state_id = state_id;
    }

    public int getPriority_id() {
        return priority_id;
    }

    public void setPriority_id(int priority_id) {
        this.priority_id = priority_id;
    }

    public int getProject_user_id() {
        return project_user_id;
    }

    public void setProject_user_id(int project_user_id) {
        this.project_user_id = project_user_id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }
}
