package models;

public class StateTask {



    int state_id;
    String state_name;


    public StateTask(){

    }
    public StateTask(int state_id, String state_name) {
        this.state_id = state_id;
        this.state_name = state_name;
    }

    public int getState_id() {
        return state_id;
    }

    public String getState_name() {
        return state_name;
    }
    public void setState_id(int state_id) {
        this.state_id = state_id;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    @Override
    public String toString() {
        return this.state_name;
    }
}
