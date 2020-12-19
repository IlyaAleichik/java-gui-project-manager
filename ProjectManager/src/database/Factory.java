package database;

import database.projects.SQLProject;
import database.users.SQLUsers;

public class Factory extends AbstractFactory{
    @Override
    public SQLUsers getUsers(){return SQLUsers.getInstance();}

    @Override
    public SQLProject getProject() { return SQLProject.getInstance(); }
}
