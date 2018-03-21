package treningsdagbok.core;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class OvelseIOkt implements ActiveDomainObject {

    private int oktID;
    private int ovelseID;
    private int kilo;
    private int sett;
    private String beskrivelse;
    private boolean medApparat;


    public OvelseIOkt(int oktID, int ovelseID, int kilo, int sett, String beskrivelse, boolean medApparat) {
        this.oktID = oktID;
        this.ovelseID = ovelseID;
        this.kilo = kilo;
        this.sett = sett;
        this.beskrivelse = beskrivelse;
        this.medApparat = medApparat;
    }

    public int getOktID() {
        return this.oktID;
    }

    public void setOktID(int oktID) {
        this.oktID = oktID;
    }

    public int getOvelseID() {
        return this.ovelseID;
    }

    public void setOvelseID(int ovelseID) {
        this.ovelseID = ovelseID;
    }

    public int getKilo() {
        return kilo;
    }

    public void setKilo(int kilo) {
        this.kilo = kilo;
    }

    public int getSett() {
        return sett;
    }

    public void setSett(int sett) {
        this.sett = sett;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public boolean isMedApparat() {
        return medApparat;
    }

    public void setMedApparat(boolean medApparat) {
        this.medApparat = medApparat;
    }

    @Override
    public void save(Connection conn) {
        if (!medApparat) {
            try {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("insert into OvelseIOktUtenApparat values ("+this.oktID+","+this.ovelseID+","+this.beskrivelse+")");

                stmt.close();

            } catch (SQLException e) {
                throw new RuntimeException("Kunne ikke lagre Ovelse." + e);
            }

        }
        else {
            try {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("insert into OvelseIOktMedApparat values ("+this.oktID+","+this.ovelseID+","+this.kilo+","+this.sett+")");

                stmt.close();

            } catch (SQLException e) {
                throw new RuntimeException("Kunne ikke lagre Ovelse." + e);
            }

        }

    }

}

