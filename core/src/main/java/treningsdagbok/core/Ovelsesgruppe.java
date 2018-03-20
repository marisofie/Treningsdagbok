package treningsdagbok.core;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

public class Ovelsesgruppe implements ActiveDomainObject{

    private int gruppeID = -1;
    private String gruppeNavn;

    public Ovelsesgruppe(int gruppeID, String gruppeNavn) {
        this.gruppeID = gruppeID;
        this.gruppeNavn = gruppeNavn;
    }

    public void setGruppeNavn(String gruppeNavn) {
        this.gruppeNavn = gruppeNavn;
    }

    public String getGruppeNavn() {
        return this.gruppeNavn;
    }

    public void setGruppeID(int gruppeID) {
        this.gruppeID = gruppeID;
    }

    public int getGruppeID() {
        return this.gruppeID;
    }

    public void getByID(Connection connection) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select GruppeNavn from OvelsesGruppe where GruppeID = " + this.gruppeID);

            while (rs.next()) {
                this.gruppeNavn = rs.getString("GruppeNavn");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void save(Connection connection) {

        if (this.gruppeID == -1 || this.gruppeNavn == null){
            throw new IllegalStateException("Missing input.");
        }

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into OvelsesGruppe values ("+this.gruppeID+","+this.gruppeNavn+")");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String toString() {
        return "OvelsesGruppe: " + this.gruppeID + ", " + this.gruppeNavn;
    }
}
