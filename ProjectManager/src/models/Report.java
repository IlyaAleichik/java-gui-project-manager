package models;

import java.util.Date;

public class Report {
    private String nameReport;
    private String dateCreationReport;

    public Report(){}
    public Report(String nameReport, String dateCreationReport) {
        this.nameReport = nameReport;
        this.dateCreationReport = dateCreationReport;
    }

    public String getNameReport() {
        return nameReport;
    }

    public void setNameReport(String nameReport) {
        this.nameReport = nameReport;
    }

    public String getDateCreationReport() {
        return dateCreationReport;
    }

    public void setDateCreationReport(String dateCreationReport) {
        this.dateCreationReport = dateCreationReport;
    }

}
