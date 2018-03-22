package treningsdagbok.core;

import java.sql.*;

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
            String sql = ("INSERT INTO Ovelsesgruppe VALUES ((?), (?))"); //(" + this.gruppeID + "," + this.gruppenavn + ")");
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, this.gruppeID);
            stmt.setString(2, this.gruppenavn);

            stmt.executeUpdate();

            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "Ovelsesgruppe: " +
                "\nGruppeID: " + gruppeID +
                "\nGruppeNavn: '" + gruppenavn + '\'';
    }
}

