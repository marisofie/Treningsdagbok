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

    public int getApparatID() {
        return apparatID;
    }

    public void setApparatID(int apparatID) {
        this.apparatID = apparatID;
    }

    public String getApparatNavn() {
        return apparatNavn;
    }

    public void setApparatNavn(String apparatNavn) {
        this.apparatNavn = apparatNavn;
    }

    public String getFunksjonsbeskrivelse() {
        return funksjonsbeskrivelse;
    }

    public void setFunksjonsbeskrivelse(String funksjonsbeskrivelse) {
        this.funksjonsbeskrivelse = funksjonsbeskrivelse;
    }

    /*public void getByID(Connection connection) {
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

    } */

    @Override
    public void save(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into Apparat values ("+this.apparatID+","+this.apparatNavn+","+this.funksjonsbeskrivelse+")");

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Kunne ikke registrere apparat " + e);
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

            rs.close();
            stmt.close();

            return apparater;

        }catch (SQLException e) {
            throw new RuntimeException("Kunne ikke hente alle apparater" + e);
        }



    }

    @Override
    public String toString() {
        return "Apparat{" +
                "apparatID=" + apparatID +
                ", apparatNavn='" + apparatNavn + '\'' +
                ", funksjonsbeskrivelse='" + funksjonsbeskrivelse + '\'' +
                '}';
    }
}
