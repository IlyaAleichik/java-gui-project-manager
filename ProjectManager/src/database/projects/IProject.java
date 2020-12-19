package database.projects;

import models.Project;
import models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IProject {
    public ResultSet selectProject(Project obj,int iser_id) throws SQLException;
    public void insertProject(Project obj);
    public void updateProject(Project obj);
    public void deleteProject(Project obj);
}
