package treningsdagbok.core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OvelseUtenApparat extends Ovelse {

    public OvelseUtenApparat(String ovelseNavn) {
        this.ovelseID = IDCounter++;
        this.ovelseNavn = ovelseNavn;
    }

    public void save(Connection connection) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into OvelseUtenApparat values ("+this.ovelseID+","+this.ovelseNavn+")");

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Kunne ikke registrere øvelse." + e);        }
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
