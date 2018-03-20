package treningsdagbok.core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OvelseUtenApparat extends Ovelse implements ActiveDomainObject {

    private String ovelseNavn;
    private int ovelseID = -1;

    public void OvelseUtenApparat(int ovelseID, String ovelseNavn) {
        this.ovelseID = ovelseID;
        this.ovelseNavn = ovelseNavn;
    }

    public void setOvelseID(int ovelseID) {
        this.ovelseID = ovelseID;
    }

    public int getOvelseID() {
        return this.ovelseID;
    }

    public void setOvelseNavn(String ovelseNavn) {
        this.ovelseNavn = ovelseNavn;
    }

    public String getOvelseNavn() {
        return this.ovelseNavn;
    }

    public void getByID(Connection connection) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select OvelseNavn from OvelseUtenApparat where OvelseUAID = " + this.ovelseID);

            while (rs.next()) {
                this.ovelseNavn = rs.getString("OvelseNavn");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void save(Connection connection) {

        if (this.ovelseID== -1 || this.ovelseNavn == null){
            throw new IllegalStateException("Missing input.");
        }


        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into OvelseUtenApparat values ("+this.ovelseID+","+this.ovelseNavn+")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String toString() {
        return "OvelseUtenApparat: " + this.ovelseID + ", " + this.ovelseNavn;
    }

}
