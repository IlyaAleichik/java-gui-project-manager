package database.users;

import database.PostgresDriver;
import models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUsers implements  IUsers {

    private static SQLUsers instance;
    private PostgresDriver connection;
    private ResultSet resultSet;

    private SQLUsers(){
        this.connection = PostgresDriver.getInstance();
    }
    public static synchronized SQLUsers getInstance() {
        if (instance == null) {
            instance = new SQLUsers();
        }
        return instance;
    }

    @Override
    public ResultSet selectUser(User obj) throws SQLException {
        String str = "SELECT * FROM users WHERE (user_nickname = '"+ obj.getUserNickname() +"' OR user_email = '" + obj.getUserEmail() +"') AND user_password = '"+ obj.getUserPassword() +"'";
       return this.resultSet = connection.getResultSet(str);
    }

    @Override
    public void insertUser(User obj){
        String str = "INSERT INTO users(USER_NAME, USER_SURNAME,USER_PATRONYMIC,USER_PASSWORD,USER_PHONE, USER_EMAIL,USER_NICKNAME,USER_ROLE_ID) VALUES ('"+obj.getUserName()+"','"+obj.getUserSurname()+"','"+obj.getUserPatronymic()+"','"+obj.getUserPassword()+"','"+obj.getUserPhone()+"','"+obj.getUserEmail()+"','"+obj.getUserNickname()+"',DEFAULT)";
        connection.execute(str);
    }

    @Override
    public void updateUser(User obj){}

    @Override
    public void deleteUser(User obj){}

    @Override
    public void deleteAllUser(User obj){}
}
