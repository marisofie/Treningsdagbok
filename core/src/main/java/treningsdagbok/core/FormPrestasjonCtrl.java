package treningsdagbok.core;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.*;


public class FormPrestasjonCtrl extends DBConnection {

    private Scanner scanner;

    public FormPrestasjonCtrl(Scanner scanner) {
        this.scanner = scanner;
        super.connect();
    }

    public Date getMaxDate() {
        Date maxDate = Date.valueOf("1900-01-01");

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Dato from Treningsokt ORDER BY Dato ASC LIMIT 1");

            while (rs.next()) {
                maxDate = rs.getDate("Dato");
            }

            rs.close();
            stmt.close();

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return maxDate;
    }


    public Boolean dateOK(Date date, Date maxDate) {
        if (date.after(maxDate)) {
            return true;
        }
        return false;
    }


    public List<Treningsokt> getFormPrestasjon(Date date) {

        Date today = Date.valueOf("2020-03-21");

        try {

            Statement stmt = conn.createStatement();
            //Trenger kanskje endring, må sjekkes.
            ResultSet rs = stmt.executeQuery("SELECT OktID, Dato, Form, Prestasjon FROM Treningsokt WHERE Dato BETWEEN " + date + " AND " + today);

            List<Treningsokt> treningsokter = new ArrayList<>();

            while (rs.next()) {
                int oktID = rs.getInt("OktID");
                Date dato = rs.getDate("Dato");
                int form = rs.getInt("Form");
                int prestasjon = rs.getInt("Prestasjon");

                treningsokter.add(new Treningsokt(oktID, dato, null, 0, form, prestasjon));

            }

            rs.close();
            stmt.close();


            return treningsokter;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Kan ikke hente fra treningsokt.");
        }
    }


    public void show(List<Treningsokt> treningsokter) {
        for (Treningsokt okt : treningsokter) {
            System.out.println("OktID: " + okt.getOktID() + "\nDato: " + okt.getDato() + "\nForm: " + okt.getForm() + "\nPrestasjon: " + okt.getPrestasjon());
            System.out.println("\n");
        }
    }


    public void run() {
        System.out.println("Oppgi en dato for å få opp form og prestasjon for et tidsintervall, på formen YYYY-MM-DD");

        Date maxDate = getMaxDate();

        String input = scanner.next().trim();
        Date dato = Date.valueOf(input);

        while (!dateOK(dato, maxDate)) {

            System.out.println("Du har ikke treningsøkter registrert så langt tilbake i tid. Tidsintervallet må være lik eller etter " + maxDate);
            System.out.println("Oppgi dato på formen YYYY-MM-DD");

            input = scanner.next();
            dato = Date.valueOf(input);
        }

        List<Treningsokt> treningsokter = getFormPrestasjon(dato);
        show(treningsokter);
        super.disconnect();
    }
}
