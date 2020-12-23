package database;

import database.projects.SQLActivity;
import database.projects.SQLProject;
import database.risks.SQLRisk;
import database.tasks.SQLTask;
import database.users.SQLUsers;

public abstract class AbstractFactory {
    public abstract SQLUsers getUsers();
    public abstract SQLProject getProject();
    public abstract SQLTask getTask();
    public abstract SQLRisk getRisk();
    public abstract SQLActivity getActivity();
}
