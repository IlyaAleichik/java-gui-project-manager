package models;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.concurrent.RecursiveAction;

public class Task{

    private SimpleIntegerProperty task_id;
    private SimpleStringProperty  task_name;
    private SimpleStringProperty  task_note;
    private SimpleStringProperty  state_name;
    private SimpleStringProperty  priority_name;
    private SimpleStringProperty  project_name;
    private SimpleIntegerProperty project_user_id;


    public Task(){}
    public Task(int task_id, String task_name, String task_note, String state_name, String priority_name, String project_name, int project_user_id) {
        this.task_id = new SimpleIntegerProperty(task_id);
        this.task_name = new SimpleStringProperty(task_name);;
        this.task_note = new SimpleStringProperty(task_note);;
        this.state_name = new SimpleStringProperty(state_name);;
        this.priority_name = new SimpleStringProperty(priority_name);;
        this.project_name = new SimpleStringProperty(project_name);;
        this.project_user_id = new SimpleIntegerProperty(project_user_id);;
    }

    public int getTask_id() {
        return task_id.get();
    }

    public void setTask_id(int task_id) {
        this.task_id.set(task_id);
    }

    public String getTask_name() {
        return task_name.get();
    }

    public void setTask_name(String task_name) {
        this.task_name.set(task_name);
    }

    public String getTask_note() {
        return task_note.get();
    }

    public void setTask_note(String task_note) {
        this.task_note.set(task_note);
    }

    public String getState_name() {
        return state_name.get();
    }

    public void setState_name(String state_name) {
        this.state_name.set(state_name);
    }

    public String getPriority_name() {
        return priority_name.get();
    }

    public void setPriority_name(String priority_name) { this.priority_name.set(priority_name); }

    public String getProject_name() {
        return project_name.get();
    }

    public void setProject_name(String project_name) {
        this.project_name.set(project_name);
    }

    public int getProject_user_id() {
        return project_user_id.get();
    }

    public void setProject_user_id(int project_user_id) {
        this.project_user_id.set(project_user_id);
    }

}
