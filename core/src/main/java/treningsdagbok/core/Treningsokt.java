package treningsdagbok.core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.Time;

public class Treningsokt implements ActiveDomainObject {

    private int oktID;
    private Date dato;
    private Time tidspunkt;
    private int varighet = -1;
    private int form = -1;
    private int prestasjon = -1;

    public Treningsokt(int oktID, Date dato, Time tidspunkt, int varighet, int form, int prestasjon) {
        this.oktID = oktID;
        this.dato = dato;
        this.tidspunkt = tidspunkt;
        this.varighet = varighet;
        this.form = form;
        this.prestasjon = prestasjon;

    }

    @Override
    public void save(Connection conn) {

    }

    public String toString() {
        return "Treningsøkt: " + this.oktID + " " + this.dato + " " + this.tidspunkt + " " + this.varighet + " " + this.form + " " + this.prestasjon;
    }
}
