package database.tasks;

import models.Task;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ITask {
    public ResultSet selectStateTask() throws SQLException;
    public ResultSet selectPriorityTask() throws SQLException;
    public ResultSet selectTask(Task obj) throws SQLException;
    public void insertTask(Task obj);
    public void updateTask(Task obj);
    public void deleteTask(Task obj);
}
