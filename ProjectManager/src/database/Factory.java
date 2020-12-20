package database;

import database.projects.SQLProject;
import database.risks.SQLRisk;
import database.tasks.SQLTask;
import database.users.SQLUsers;

public class Factory extends AbstractFactory{
    @Override
    public SQLUsers getUsers(){return SQLUsers.getInstance();}

    @Override
    public SQLProject getProject() { return SQLProject.getInstance(); }
    public SQLTask getTask() { return SQLTask.getInstance(); }
    public SQLRisk getRisk() { return SQLRisk.getInstance(); }
}
