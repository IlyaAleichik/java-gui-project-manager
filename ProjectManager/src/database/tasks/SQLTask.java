package database.tasks;

import database.PostgresDriver;
import database.projects.SQLProject;
import database.risks.SQLRisk;
import models.Project;
import models.Task;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLTask implements ITask {
    private static SQLTask instance;
    private PostgresDriver connection;
    private ResultSet resultSet;

    public SQLTask() {
        this.connection = PostgresDriver.getInstance();
    }

    public static synchronized SQLTask getInstance() {
        if (instance == null){
            instance = new SQLTask();
        } return instance;
    }

    @Override
    public ResultSet selectStateTask() throws SQLException {
        String str = "SELECT * FROM states_tasks";
        return  resultSet = connection.getResultSet(str);
    }

    @Override
    public ResultSet selectPriorityTask() throws SQLException {
        String str = "SELECT * FROM priority_tasks";
        return  resultSet = connection.getResultSet(str);
    }

    @Override
    public ResultSet selectTask(Task obj) throws SQLException {
        String str = "SELECT * FROM tasks_view WHERE project_id = '" +obj.getProject_id() +"' AND project_user_id = '"+obj.getProject_user_id() +"'";
        return  resultSet = connection.getResultSet(str);
    }

    @Override
    public void insertTask(Task obj) {
        String str = "INSERT INTO tasks(TASK_NAME,TASK_NOTE,TASK_STATE_ID,TASK_PRIORITY_ID,TASK_PROJECT_ID) VALUES ('"+ obj.getTask_name() +"','" + obj.getTask_note() +"','" +obj.getState_id()+"','"+obj.getPriority_id()+"','"+obj.getProject_id()+"')";
        connection.execute(str);
    }

    @Override
    public void updateTask(Task obj) {
        String str = "UPDATE tasks SET TASK_NAME = '"+ obj.getTask_name()+"', TASK_NOTE = '" + obj.getTask_note() + "', TASK_STATE_ID = '"+ obj.getState_id() +"', TASK_PRIORITY_ID = '"+ obj.getPriority_id() +"' WHERE TASK_ID = "+ obj.getTask_id();
        connection.execute(str);
    }

    @Override
    public void deleteTask(Task obj) {
        String str = "DELETE FROM tasks WHERE TASK_ID ="+ obj.getTask_id();
        connection.execute(str);
    }
}
