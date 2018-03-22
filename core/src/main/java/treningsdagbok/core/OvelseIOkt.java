package treningsdagbok.core;

import java.sql.*;

public class OvelseIOkt implements ActiveDomainObject {

    private int oktID;
    private int ovelseID;
    private int kilo;
    private int sett;
    private String ovelseNavn;
    private String beskrivelse;
    private Date dato;
    private boolean medApparat;

    //Til fremvisning
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

    //Til lagring
    public OvelseIOkt(int oktID, int ovelseID, String beskrivelse) {
        this.oktID = oktID;
        this.ovelseID = ovelseID;
        this.beskrivelse = beskrivelse;
        this.medApparat = false;
    }

    //Til lagring
    public OvelseIOkt(int oktID, int ovelseID, int kilo, int sett) {
        this.oktID = oktID;
        this.ovelseID = ovelseID;
        this.kilo = kilo;
        this.sett = sett;
        this.medApparat = true;
    }

    public int getOktID() {
        return this.oktID;
    }

    public int getOvelseID() {
        return this.ovelseID;
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
                String sql = "INSERT INTO OvelseIOktUtenApparat VALUES (?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setInt(1, this.oktID);
                stmt.setInt(2, this.ovelseID);
                stmt.setString(3, this.beskrivelse);


                stmt.executeUpdate();
                stmt.close();

            } catch (SQLException e) {
                throw new RuntimeException("Kunne ikke lagre Øvelse." + e);
            }

        }
        else {
            try {
                String sql = "INSERT INTO OvelseIOktMedApparat VALUES (?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setInt(1, this.oktID);
                stmt.setInt(2, this.ovelseID);
                stmt.setInt(3, this.kilo);
                stmt.setInt(4, this.sett);

                stmt.executeUpdate();
                stmt.close();

            } catch (SQLException e) {
                throw new RuntimeException("Kunne ikke lagre Øvelse." + e);
            }

        }

    }

}

