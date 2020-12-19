package database;

import database.users.SQLUsers;

public abstract class AbstractFactory {
    public abstract SQLUsers getUsers();
}
