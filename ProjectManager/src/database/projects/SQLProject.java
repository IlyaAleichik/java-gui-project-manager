package database.projects;

import database.PostgresDriver;
import models.Project;
import models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLProject implements IProject{

    private static SQLProject instance;
    private PostgresDriver connection;
    private ResultSet resultSet;

    public SQLProject() {
        this.connection = PostgresDriver.getInstance();
    }

    public static synchronized SQLProject getInstance() {
        if (instance == null){
            instance = new SQLProject();
        } return instance;
    }

    @Override
    public ResultSet selectProject(Project obj, int user_id) throws SQLException {
        String str = "SELECT * FROM projects WHERE project_user_id = '"+user_id+"'";
        return this.resultSet = connection.getResultSet(str);
    }

    @Override
    public void insertProject(Project obj) {

    }

    @Override
    public void updateProject(Project obj) {

    }

    @Override
    public void deleteProject(Project obj) {

    }
}
