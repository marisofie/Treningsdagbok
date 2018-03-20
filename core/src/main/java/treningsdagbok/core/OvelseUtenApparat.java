package treningsdagbok.core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OvelseUtenApparat extends Ovelse {

    public OvelseUtenApparat(int ovelseID, String ovelseNavn) {
        super(ovelseID, ovelseNavn);
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
