package database.projects;

import database.PostgresDriver;
import database.activity.IActivity;
import models.ActivityLog;
import models.Project;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLActivity implements IActivity {

    private static SQLActivity instance;
    private PostgresDriver connection;
    private ResultSet resultSet;

    public SQLActivity() {
        this.connection = PostgresDriver.getInstance();
    }

    public static synchronized SQLActivity getInstance() {
        if (instance == null){
            instance = new SQLActivity();
        } return instance;
    }

    @Override
    public ResultSet selectActivity(String strq) throws SQLException {
        String str = "SELECT * FROM activity_log_view WHERE user_nickname = '"+strq+ "'";
        return this.resultSet = connection.getResultSet(str);
    }

    @Override
    public void insertActivity(int user_id) throws UnknownHostException{
        String str = "INSERT INTO activity(LOG_TIME,LOG_DATE,LOG_USER_IP,LOG_USER_ID) VALUES (CURRENT_TIME,CURRENT_DATE,'"+ Inet4Address.getLocalHost().getHostAddress().toString()+"','"+ user_id + "');\n";
        connection.execute(str);
    }

}
