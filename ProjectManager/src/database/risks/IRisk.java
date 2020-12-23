package database.risks;

import models.Project;
import models.Risk;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IRisk {
    public ResultSet selectAvailabilityRisk() throws SQLException;
    public ResultSet selectRisk(Risk obj) throws SQLException;
    public void insertRisk(Risk obj);
    public void updateRisk(Risk obj);
    public void deleteRisk(Risk obj);
}
