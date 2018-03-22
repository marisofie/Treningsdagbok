package treningsdagbok.core;

import java.sql.*;

public class OvelseUtenApparat extends Ovelse {

    //Til lagring av data
    public OvelseUtenApparat(String ovelseNavn, int gruppeID) {
        this.ovelseID = IDCounter++;
        this.ovelseNavn = ovelseNavn;
        this.gruppeID = gruppeID;
    }

    //Til uthenting av data
    public OvelseUtenApparat(int ovelseID, String ovelseNavn, int gruppeID) {
        this.ovelseID = ovelseID;
        this.ovelseNavn = ovelseNavn;
        this.gruppeID = gruppeID;
    }

    public void save(Connection conn) {
        try {
            String sql = "INSERT INTO  OvelseUtenApparat VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, this.ovelseID);
            stmt.setString(2, this.ovelseNavn);
            stmt.setInt(3, this.gruppeID);

            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Kunne ikke registrere øvelse." + e);        }
    }

    @Override
    public String toString() {
        return "Ovelse: " +
                "\nOvelseNavn: '" + this.ovelseNavn + '\'';
    }
}
