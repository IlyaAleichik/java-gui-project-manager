package database.users;

import database.PostgresDriver;
import models.User;

public class SQLUsers implements  IUsers {

    private static SQLUsers instance;
    private PostgresDriver connection;

    private SQLUsers(){
        connection = PostgresDriver.getInstance();
    }

    public static synchronized SQLUsers getInstance() {
        if (instance == null) {
            instance = new SQLUsers();
        }
        return instance;
    }

    @Override
    public void selectUser(User obj){}

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
