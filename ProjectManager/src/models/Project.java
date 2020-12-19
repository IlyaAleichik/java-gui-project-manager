package models;

public class Project {

    private String project_name;
    private String project_description;
    private String project_date_creation;
    private String imgSrc;


    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_description() {
        return project_description;
    }

    public void setProject_description(String project_description) {
        this.project_description = project_description;
    }

    public String getProject_date_creation() {
        return project_date_creation;
    }

    public void setProject_date_creation(String project_date_creation) {
        this.project_date_creation = project_date_creation;
    }

}
