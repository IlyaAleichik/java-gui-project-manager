package database.users;

import models.User;

public interface IUsers {
    public void selectUser(User obj);
    public void insertUser(User obj);
    public void updateUser(User obj);
    public void deleteUser(User obj);
    public void deleteAllUser(User obj);
}
