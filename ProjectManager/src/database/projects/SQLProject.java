package database.projects;

import database.PostgresDriver;
import models.Project;
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
        String str = "SELECT * FROM projects_view WHERE project_user_id = '"+user_id+"'";
        return this.resultSet = connection.getResultSet(str);
    }

    @Override
    public void insertProject(Project obj) {
        String str = "INSERT INTO projects(PROJECT_DATE_CREATION,PROJECT_TIME_CREATION,PROJECT_TERM_DELIVERY,PROJECT_COST_DELIVERY,PROJECT_NAME,PROJECT_DESCRIPTION,PROJECT_CURRENCY_ID,PROJECT_USER_ID) VALUES \n" +
                "(CURRENT_DATE, CURRENT_TIME, '"+ obj.getProject_term_delivery()+"',"+ obj.getProject_cost_delivery() +"," +"'" +obj.getProject_name()+ "'"+"," +"'" + obj.getProject_description() + "', "+ "1" +"," + "'"+ obj.getProject_user_id() + "')";
        connection.execute(str);
    }

    @Override
    public void updateProject(Project obj) {
        String str = "UPDATE projects SET \n" +
                "PROJECT_DATE_CREATION = CURRENT_DATE, \n" +
                "PROJECT_TIME_CREATION = CURRENT_TIME,\n" +
                "PROJECT_TERM_DELIVERY ='"+ obj.getProject_term_delivery() +"',\n" +
                "PROJECT_COST_DELIVERY ='"+ obj.getProject_cost_delivery() +"',\n" +
                "PROJECT_NAME ='"+ obj.getProject_name() +"',\n" +
                "PROJECT_DESCRIPTION = '"+obj.getProject_description() + "' WHERE PROJECT_ID =" +obj.getProject_id();
        connection.execute(str);
    }

    @Override
    public void deleteProject(Project obj) {
        String str = "DELETE FROM projects WHERE PROJECT_ID ="+ obj.getProject_id();
        connection.execute(str);
    }
}
