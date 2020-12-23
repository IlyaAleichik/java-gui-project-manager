package models;

public class AvaibilityRisk {

    private int id_avaibilyty;
    private String name_avaibilyty;


    public AvaibilityRisk(){

    }
    public AvaibilityRisk(int id_avaibilyty, String name_avaibilyty) {
        this.id_avaibilyty = id_avaibilyty;
        this.name_avaibilyty = name_avaibilyty;
    }

    public int getId_avaibilyty() {
        return id_avaibilyty;
    }

    public void setId_avaibilyty(int id_avaibilyty) {
        this.id_avaibilyty = id_avaibilyty;
    }

    public String getName_avaibilyty() {
        return name_avaibilyty;
    }

    public void setName_avaibilyty(String name_avaibilyty) {
        this.name_avaibilyty = name_avaibilyty;
    }

    @Override
    public String toString() {
        return this.getName_avaibilyty();
    }


}
