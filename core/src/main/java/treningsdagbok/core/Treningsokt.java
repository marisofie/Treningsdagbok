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

        if (checkNumber(varighet)) {
            this.varighet = varighet;
        } else {
            throw new IllegalArgumentException("Varighet må være et tall mellom 1 og 10.");
        }

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
        if (checkNumber(varighet)) {
            this.varighet = varighet;
        } else {
            throw new IllegalArgumentException("Varighet må være et tall mellom 1 og 10.");
        }
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
            return true;
        } return false;
    }

    @Override
    public void save(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into Treningsokt values ("+this.oktID+","+this.dato+","+this.tidspunkt+","+this.varighet+","+this.form+","+this.prestasjon+")");

            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String toString() {
        return "TRENINGSOKT" + "\n" + "OktID" + "\t" + "Dato" + "\t" + "Tidspunkt" + "\t" + "Varighet" + "\t" + "Form" + "\t" + "Prestasjon"
                + "\n" + this.oktID + "\t" + this.dato + "\t" + this.tidspunkt + "\t" + this.varighet + "\t" + this.form + "\t" + this.prestasjon;
    }
}
