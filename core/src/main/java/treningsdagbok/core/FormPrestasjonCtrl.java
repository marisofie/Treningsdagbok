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
        if (date.after(maxDate) || date.compareTo(maxDate) == 0) {
            return true;
        }
        return false;
    }


    public List<Treningsokt> getFormPrestasjon(Date start, Date slutt) {

        try {

            String sql = "SELECT OktID, Dato, Form, Prestasjon FROM Treningsokt WHERE Dato BETWEEN ? AND ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setDate(1, start);
            stmt.setDate(2, slutt);

            ResultSet rs = stmt.executeQuery();

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


    public void run() throws SQLException {
        System.out.println("Oppgi en startdato for å få opp form og prestasjon for et tidsintervall, på formen YYYY-MM-DD");
        Date maxDate = getMaxDate();

        String input = scanner.next().trim();
        Date start = Date.valueOf(input);

        while (!dateOK(start, maxDate)) {

            System.out.println("Du har ikke treningsøkter registrert så langt tilbake i tid. Tidsintervallet må være lik eller etter " + maxDate);
            System.out.println("Oppgi dato på formen YYYY-MM-DD");

            input = scanner.next();
            start = Date.valueOf(input);
        }

        System.out.println("Oppgi en sluttdato for å få opp form og prestasjon for et tidsintervall, på formen YYYY-MM-DD");

        String sluttDato = scanner.next();
        Date slutt = Date.valueOf(sluttDato);

        List<Treningsokt> treningsokter = getFormPrestasjon(start, slutt);
        show(treningsokter);

        System.out.println("Hvis du vil tilbake til hovedmenyen skriv (1), hvis du vil fortsette skriv (2)");

        int choice = scanner.nextInt();

        switch(choice) {
            case 1:
                super.disconnect();
                TreningsdagbokMain treningsdagbokMain = new TreningsdagbokMain();
                treningsdagbokMain.run();
                break;

            case 2:
                this.run();

        }
    }
}
