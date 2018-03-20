package treningsdagbok.core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OvelseUtenApparat extends Ovelse implements ActiveDomainObject {

    public void OvelseUtenApparat(int ovelseID, String ovelseNavn) {
        this.ovelseID = ovelseID;
        this.ovelseNavn = ovelseNavn;
    }

    /*public void getByID(Connection connection) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select OvelseNavn from OvelseUtenApparat where OvelseUAID = " + this.ovelseID);

            while (rs.next()) {
                this.ovelseNavn = rs.getString("OvelseNavn");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } */

    @Override
    public String toString() {
        return "OvelseUtenApparat{" +
                "ovelseID=" + ovelseID +
                ", ovelseNavn='" + ovelseNavn + '\'' +
                '}';
    }
}
