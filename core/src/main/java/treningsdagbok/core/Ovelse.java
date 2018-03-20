package treningsdagbok.core;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Ovelse implements ActiveDomainObject {

    protected int ovelseID;
    protected String ovelseNavn;

    public Ovelse(int ovelseID, String ovelseNavn) {
        this.ovelseID = ovelseID;
        this.ovelseNavn = ovelseNavn;
    }

    public int getOvelseID() {
        return ovelseID;
    }

    public void setOvelseID(int ovelseID) {
        this.ovelseID = ovelseID;
    }

    public String getOvelseNavn() {
        return ovelseNavn;
    }

    public void setOvelseNavn(String ovelseNavn) {
        this.ovelseNavn = ovelseNavn;
    }

    public void save(Connection connection) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into OvelseUtenApparat values ("+this.ovelseID+","+this.ovelseNavn+")");

            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
