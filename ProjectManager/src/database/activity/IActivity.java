package database.activity;

import models.ActivityLog;
import models.Project;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IActivity {
    public ResultSet selectActivity(String strq) throws SQLException;

    public void insertActivity(int user_id) throws UnknownHostException;
}
