package database.risks;

import database.PostgresDriver;
import database.projects.SQLProject;
import models.Project;
import models.Risk;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLRisk implements IRisk{
    private static SQLRisk instance;
    private PostgresDriver connection;
    private ResultSet resultSet;

    public SQLRisk() {
        this.connection = PostgresDriver.getInstance();
    }

    public static synchronized SQLRisk getInstance() {
        if (instance == null){
            instance = new SQLRisk();
        } return instance;
    }


    @Override
    public ResultSet selectAvailabilityRisk() throws SQLException {
        String str = "SELECT * FROM availability_risk";
        return resultSet = connection.getResultSet(str);
    }
    @Override
    public ResultSet selectRisk(Risk obj) throws SQLException {
        String str = "SELECT * FROM risks_view WHERE project_id = '"+obj.getProject_id()+"' AND project_user_id = '"+ obj.getProject_user_id() + "'";
        return resultSet = connection.getResultSet(str);
    }

    @Override
    public void insertRisk(Risk obj) {
        String str = "INSERT INTO risks(RISK_LOSS_INCOME,RISK_LOSS_TIME_INDAY,RISK_NAME,RISK_AVAILABILITY_ID,RISK_PROJECT_ID) VALUES ('" + obj.getRisk_loss_income() +"','"+ obj.getRisk_loss_time_inday() +"','"+obj.getRisk_name() + "','" +obj.getRisk_avaibility_id() +"','" +obj.getProject_id()+"')";
        connection.execute(str);
    }

    @Override
    public void updateRisk(Risk obj) {
        String str = "UPDATE risks SET RISK_LOSS_INCOME ='"+ obj.getRisk_loss_income()+"',RISK_LOSS_TIME_INDAY ='"+ obj.getRisk_loss_time_inday()+"',RISK_NAME ='"+ obj.getRisk_name() +"',RISK_AVAILABILITY_ID ='"+ obj.getRisk_avaibility_id() +"' WHERE RISK_ID =" + obj.getRisk_id();
        connection.execute(str);
    }

    @Override
    public void deleteRisk(Risk obj) {
        String str = "DELETE FROM risks WHERE RISK_ID = " + obj.getRisk_id();
        connection.execute(str);
    }
}
