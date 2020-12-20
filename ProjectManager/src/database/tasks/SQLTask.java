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
    public ResultSet selectTask(Task obj) throws SQLException {
        String str = "SELECT task_id,\n" +
                "task_name,\n" +
                "task_note,\n" +
                "states_tasks.state_name,\n" +
                "priority_tasks.prioroty_name,\n" +
                "projects.project_name,\n" +
                "projects.project_user_id\n" +
                "from tasks\n" +
                "inner join states_tasks on states_tasks.state_id = task_state_id\n" +
                "inner join priority_tasks on priority_tasks.priority_id = task_priority_id\n" +
                "inner join projects on projects.project_id = task_project_id";
        return  resultSet = connection.getResultSet(str);
    }

    @Override
    public void insertTask(Task obj) {

    }

    @Override
    public void updateTask(Task obj) {

    }

    @Override
    public void deleteTask(Task obj) {

    }
}
