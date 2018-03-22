package treningsdagbok.core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OvelseMedApparat extends Ovelse {

    private int apparatID;

    //Til lagring av data
    public OvelseMedApparat(String ovelseNavn, int apparatID, int gruppeID) {
        this.ovelseID = IDCounter++;
        this.ovelseNavn = ovelseNavn;
        this.apparatID = apparatID;
        this.gruppeID = gruppeID;
    }

    // Til uthenting av data
    public OvelseMedApparat(int ovelseID, String ovelseNavn, int apparatID, int gruppeID) {
        this.ovelseID = ovelseID;
        this.ovelseNavn = ovelseNavn;
        this.apparatID = apparatID;
        this.gruppeID = gruppeID;
    }

    public int getApparatID() {
        return apparatID;
    }

    public void setApparatID(int apparatID) {
        this.apparatID = apparatID;
    }


    @Override
    public void save(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO OvelseMedApparat VALUES ("+this.ovelseID+","+this.ovelseNavn+","+this.apparatID+")");

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Kunne registrere øvelse." + e);
        }
    }

    /* public void getByID(Connection connection) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select OvelseNavn from OvelseMedApparat where OvelseMAID = " + this.ovelseID);

            while (rs.next()) {
                this.ovelseNavn = rs.getString("OvelseNavn");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } */

    @Override
    public String toString() {
        return "Ovelse: " +
                "\nOvelseNavn: '" + ovelseNavn + '\'' +
                "\nApparatID: " + apparatID;
    }
}
