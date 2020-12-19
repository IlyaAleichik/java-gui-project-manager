package database;

import database.projects.SQLProject;
import database.users.SQLUsers;

public abstract class AbstractFactory {
    public abstract SQLUsers getUsers();
    public abstract SQLProject getProject();
}
