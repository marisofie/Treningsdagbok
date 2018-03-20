package treningsdagbok.core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OvelseMedApparat extends Ovelse implements ActiveDomainObject {

    private String ovelseNavn;
    private int ovelseID = -1;
    private int apparatID = -1;

    public void OvelseMedApparat(int ovelseID, String ovelseNavn, int apparatID) {
        this.ovelseID = ovelseID;
        this.ovelseNavn = ovelseNavn;
        this.apparatID = apparatID;
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

    public void setApparatID(int apparatID) {
        this.apparatID = apparatID;
    }

    public int getApparatID() {
        return this.apparatID;
    }

    public void getByID(Connection connection) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select OvelseNavn from OvelseMedApparat where OvelseMAID = " + this.ovelseID);

            while (rs.next()) {
                this.ovelseNavn = rs.getString("OvelseNavn");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void save(Connection connection) {

        if (this.apparatID == -1 || this.ovelseNavn == null || this.ovelseID == -1){
            throw new IllegalStateException("Missing input.");
        }

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into OvelseMedApparat values ("+this.ovelseID+","+this.ovelseNavn+")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "OvelseMedApparat: " + this.ovelseID + ", " + this.ovelseNavn + ", " + this.apparatID;
    }

}
