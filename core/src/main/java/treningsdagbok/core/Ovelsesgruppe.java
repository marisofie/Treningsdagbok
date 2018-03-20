package treningsdagbok.core;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

public class Ovelsesgruppe implements ActiveDomainObject {

    private int gruppeID;
    private String gruppenavn;

    public Ovelsesgruppe(int gruppeID, String gruppenavn) {
        this.gruppeID = gruppeID;
        this.gruppenavn = gruppenavn;
    }

    public void setGruppeNavn(String gruppenavn) {
        this.gruppenavn = gruppenavn;
    }

    public String getGruppeNavn() {
        return gruppenavn;
    }

    public void setGruppeID(int gruppeID) {
        this.gruppeID = gruppeID;
    }

    public int getGruppeID() {
        return gruppeID;
    }

    @Override
    public void save(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into OvelsesGruppe values (" + this.gruppeID + "," + this.gruppenavn + ")");

            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "Ovelsesgruppe{" +
                "gruppeID=" + gruppeID +
                ", gruppeNavn='" + gruppenavn + '\'' +
                '}';
    }
}

