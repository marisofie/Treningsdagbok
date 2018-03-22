package treningsdagbok.core;

import java.sql.*;

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
            String sql = "INSERT INTO OvelseMedApparat VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, this.ovelseID);
            stmt.setString(2, this.ovelseNavn);
            stmt.setInt(3, this.apparatID);
            stmt.setInt(4, this.gruppeID);

            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Kunne registrere øvelse." + e);
        }
    }

    @Override
    public String toString() {
        return "Ovelse: " +
                "\nOvelseNavn: '" + ovelseNavn + '\'' +
                "\nApparatID: " + apparatID;
    }
}
