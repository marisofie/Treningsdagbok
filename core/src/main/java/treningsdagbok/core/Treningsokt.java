package treningsdagbok.core;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Time;

public class Treningsokt implements ActiveDomainObject {

    private int oktID;
    private Date dato;
    private Time tidspunkt;
    private int varighet;
    private int form;
    private int prestasjon;

    public Treningsokt(int oktID, Date dato, Time tidspunkt, int varighet, int form, int prestasjon) {
        this.oktID = oktID;
        this.dato = dato;
        this.tidspunkt = tidspunkt;
        this.varighet = varighet;

        if (checkNumber(form)) {
            this.form = form;
        } else {
            throw new IllegalArgumentException("Form må være et tall mellom 1 og 10.");
        }

        if (checkNumber(prestasjon)) {
            this.prestasjon = prestasjon;
        } else {
            throw new IllegalArgumentException("Prestasjon må være et tall mellom 1 og 10.");
        }

    }

    public int getOktID() {
        return oktID;
    }

    public void setOktID(int oktID) {
        this.oktID = oktID;
    }

    public Date getDato() {
        return dato;
    }

    public void setDato(Date dato) {
        this.dato = dato;
    }

    public Time getTidspunkt() {
        return tidspunkt;
    }

    public void setTidspunkt(Time tidspunkt) {
        this.tidspunkt = tidspunkt;
    }

    public int getVarighet() {
        return varighet;
    }

    public void setVarighet(int varighet) {
        this.varighet = varighet;
    }

    public int getForm() {
        return form;
    }

    public void setForm(int form) {
        if (checkNumber(form)) {
            this.form = form;
        } else {
            throw new IllegalArgumentException("Form må være et tall mellom 1 og 10.");
        }
    }

    public int getPrestasjon() {
        return prestasjon;
    }

    public void setPrestasjon(int prestasjon) {
        if (checkNumber(prestasjon)) {
            this.prestasjon = prestasjon;
        } else {
            throw new IllegalArgumentException("Prestasjon må være et tall mellom 1 og 10.");
        }
    }

    public Boolean checkNumber(int number) {
        if (number < 1 || number > 10) {
            return false;
        } return true;
    }

    @Override
    public void save(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Treningsokt VALUES ("+this.oktID+","+this.dato+","+this.tidspunkt+","+this.varighet+","+this.form+","+this.prestasjon+")");

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Kunne ikke lagre treningsøkt." + e);
        }

    }

    @Override
    public String toString() {
        return "Treningsokt: \n" +
                "oktID: " + oktID +
                "\nDato: " + dato +
                "\nTidspunkt: " + tidspunkt + " minutter" +
                "\nVarighet: " + varighet +
                "\nForm: " + form +
                "\nPrestasjon: " + prestasjon;
    }
}
