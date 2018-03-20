package treningsdagbok.core;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class Apparat implements ActiveDomainObject {

    private int apparatID = -1;
    private String apparatNavn;
    private String funksjonsbeskrivelse;

    public Apparat(int apparatID, String apparatNavn, String funksjonsbeskrivelse) {
        this.apparatID = apparatID;
        this.apparatNavn = apparatNavn;
        this.funksjonsbeskrivelse = funksjonsbeskrivelse;
    }

    public void setApparatID(int apparatID) {
        this.apparatID = apparatID;
    }

    public int getApparatID() {
        return this.apparatID;
    }

    public void setApparatNavn(String apparatNavn) {
        this.apparatNavn = apparatNavn;
    }

    public String getApparatNavn(){
        return this.apparatNavn;
    }

    public void setFunksjonsBeskrivelse(String funksjonsbeskrivelse) {
        this.funksjonsbeskrivelse = funksjonsbeskrivelse;
    }

    public String getFunksjonsbeskrivelse() {
        return this.funksjonsbeskrivelse;
    }

    public void getByID(Connection connection) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select ApparatNavn, Funksjonsbeskrivelse from Apparat where ApparatID = " + this.apparatID);

            while (rs.next()) {
                this.apparatNavn = rs.getString("ApparatNavn");
                this.funksjonsbeskrivelse = rs.getString("Funksjonsbeskrivelse");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void save(Connection connection) {
        if (this.apparatID == -1 || this.apparatNavn == null || this.funksjonsbeskrivelse == null){
            throw new IllegalStateException("ApparatID, ApparatNavn and Funksjonsbeskrivelse must be set.");
        }

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into Apparat values ("+this.apparatID+","+this.apparatNavn+","+this.funksjonsbeskrivelse+")");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //metode for å få ut alle apparater fra database.
    public static List<Apparat> getAll(Connection connection) {

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Apparat");

            List<Apparat> apparater = new ArrayList<>();


            while (rs.next()) {
                int apparatID = rs.getInt("ApparatID");
                String apparatNavn = rs.getString("ApparatNavn");
                String funkbeskr = rs.getString("Funksjonsbeskrivelse");

                apparater.add(new Apparat(apparatID, apparatNavn, funkbeskr));
                System.out.print(apparater);
            }

            return apparater;

        }catch (SQLException e) {
            throw new RuntimeException("Unable to load all Apparater" + e);
        }



    }

    public String toString() {
        return "Apparat: " + this.apparatID +", " + this.apparatNavn + ", " + this.funksjonsbeskrivelse;
    }

}
