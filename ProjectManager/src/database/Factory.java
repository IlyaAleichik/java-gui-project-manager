package database;

import database.users.SQLUsers;

public class Factory extends AbstractFactory{
    public SQLUsers getUsers(){return SQLUsers.getInstance();}
}
