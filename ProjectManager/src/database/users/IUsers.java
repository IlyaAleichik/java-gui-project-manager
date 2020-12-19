package database.users;

import models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IUsers {
    public ResultSet selectUser(User obj) throws SQLException;
    public void insertUser(User obj);
    public void updateUser(User obj);
    public void deleteUser(User obj);
    public void deleteAllUser(User obj);
}
