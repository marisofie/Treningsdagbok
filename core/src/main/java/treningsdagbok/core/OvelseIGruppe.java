package treningsdagbok.core;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class OvelseIGruppe implements ActiveDomainObject {

    private int gruppeID;
    private int ovelseID;

    public OvelseIGruppe(int gruppeID, int ovelseID) {
        this.gruppeID = gruppeID;
        this.ovelseID = ovelseID;
    }

    public int getGruppeID() {
        return gruppeID;
    }

    public void setGruppeID(int gruppeID) {
        this.gruppeID = gruppeID;
    }

    public int getOvelseID() {
        return ovelseID;
    }

    public void setOvelseID(int ovelseID) {
        this.ovelseID = ovelseID;
    }

    @Override
    public void save(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into OvelseIGruppe values ("+this.gruppeID+","+this.ovelseID+")");

            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "OvelseIGruppe{" +
                "gruppeID=" + gruppeID +
                ", ovelseID=" + ovelseID +
                '}';
    }
}
