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
    public ResultSet selectRisk(Risk obj) throws SQLException {
        String str = "SELECT\n" +
                "risk_id,\n" +
                "risk_name,\n" +
                "risk_loss_income,\n" +
                "risk_loss_time_inday,\n" +
                "projects.project_name,\n" +
                "projects.project_id,\n" +
                "projects.project_user_id\n" +
                "FROM risks\n" +
                "inner join availability_risk on availability_risk.availability_id = risk_availability_id\n" +
                "inner join projects on projects.project_id = risk_project_id";
        return resultSet = connection.getResultSet(str);
    }

    @Override
    public void insertRisk(Risk obj) {

    }

    @Override
    public void updateRisk(Risk obj) {

    }

    @Override
    public void deleteRisk(Risk obj) {

    }
}
