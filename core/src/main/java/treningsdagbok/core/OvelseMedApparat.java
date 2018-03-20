package treningsdagbok.core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OvelseMedApparat extends Ovelse implements ActiveDomainObject {

    private int apparatID;

    public void OvelseMedApparat(int ovelseID, String ovelseNavn, int apparatID) {
        this.ovelseID = ovelseID;
        this.ovelseNavn = ovelseNavn;
        this.apparatID = apparatID;
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
            stmt.executeUpdate("insert into OvelseMedApparat values ("+this.ovelseID+","+this.ovelseNavn+","+this.apparatID+")");

            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
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
        return "OvelseMedApparat{" +
                "ovelseID=" + ovelseID +
                ", ovelseNavn='" + ovelseNavn + '\'' +
                ", apparatID=" + apparatID +
                '}';
    }
}
