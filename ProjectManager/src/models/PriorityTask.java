package models;

public class PriorityTask {

    int priority_id;
    String priority_name;

    public PriorityTask(){

    }
    public PriorityTask(int priority_id, String priority_name) {
        this.priority_id = priority_id;
        this.priority_name = priority_name;
    }

    public int getPriority_id() {
        return priority_id;
    }

    public void setPriority_id(int priority_id) {
        this.priority_id = priority_id;
    }

    public String getPriority_name() {
        return priority_name;
    }

    public void setPriority_name(String priority_name) {
        this.priority_name = priority_name;
    }

    @Override
    public String toString() {
        return this.getPriority_name();
    }
}
