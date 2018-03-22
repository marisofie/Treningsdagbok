package treningsdagbok.core;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

public class OvelseIOkt implements ActiveDomainObject {

    private int oktID;
    private int ovelseID;
    private int kilo;
    private int sett;
    private String ovelseNavn;
    private String beskrivelse;
    private Date dato;
    private boolean medApparat;

    public OvelseIOkt(int oktID, int ovelseID, String ovelseNavn, int kilo, int sett, String beskrivelse, Date dato, boolean medApparat) {
        this.oktID = oktID;
        this.ovelseID = ovelseID;
        this.ovelseNavn = ovelseNavn;
        this.kilo = kilo;
        this.sett = sett;
        this.beskrivelse = beskrivelse;
        this.dato = dato;
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

    public String getOvelseNavn() {
        return ovelseNavn;
    }

    public void setOvelseNavn(String ovelseNavn) {
        this.ovelseNavn = ovelseNavn;
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

    public Date getDato() {
        return dato;
    }

    public void setDato(Date dato) {
        this.dato = dato;
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
                stmt.executeUpdate("INSERT INTO OvelseIOktUtenApparat VALUES ("+this.oktID+","+this.ovelseID+","+this.beskrivelse+")");

                stmt.close();

            } catch (SQLException e) {
                throw new RuntimeException("Kunne ikke lagre Øvelse." + e);
            }

        }
        else {
            try {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO OvelseIOktMedApparat VALUES ("+this.oktID+","+this.ovelseID+","+this.kilo+","+this.sett+")");

                stmt.close();

            } catch (SQLException e) {
                throw new RuntimeException("Kunne ikke lagre Øvelse." + e);
            }

        }

    }

}

