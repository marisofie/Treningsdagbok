package treningsdagbok.core;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SisteTreningsokterCtrl extends DBConnection {

    private Scanner scanner;

    public SisteTreningsokterCtrl(Scanner scanner) {
        this.scanner = scanner;
        super.connect();
    }

    public int getMaximumN() {
        int maxN = -1;

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as maxN FROM Treningsokt");

            while (rs.next()) {
                maxN = rs.getInt("maxN");
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maxN;
    }

    public Boolean nOk(int n, int maxN) {
        if (n <= maxN) {
            return true;
        } else {
            return false;
        }
    }

    public List<Treningsokt> getAll(int n) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Treningsokt ORDER BY Dato DESC LIMIT " + n);

            List<Treningsokt> treningsokter = new ArrayList<>();


            while (rs.next()) {
                int oktID = rs.getInt("OktID");
                Date dato = rs.getDate("Dato");
                Time tidspunkt = rs.getTime("Tidspunkt");
                int varighet = rs.getInt("Varighet");
                int form = rs.getInt("Form");
                int prestasjon = rs.getInt("Prestasjon");

                treningsokter.add(new Treningsokt(oktID, dato, tidspunkt, varighet, form, prestasjon));
            }

            rs.close();
            stmt.close();

            return treningsokter;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Kan ikke hente de siste " + n + " treningsoktene.");
        }
    }

    public Notat getNotat(Treningsokt okt) {

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select NotatID, Formaal, Opplevelse FROM Notat WHERE OktID =" + okt.getOktID());

            Notat notat;

            while (rs.next()) {
                int notatID = rs.getInt("NotatID");
                String formaal = rs.getString("Formaal");
                String opplevelse = rs.getString("Opplevelse");

                notat = new Notat(notatID, formaal, opplevelse);
                return notat;
            }

            rs.close();
            stmt.close();


        } catch (SQLException e) {
            throw new RuntimeException("Kan ikke hente Notat");
        }

        return null;
    }

    public void show(List<Treningsokt> treningsokter) {
        for (Treningsokt okt : treningsokter) {
            System.out.println(okt.toString());
            System.out.println(getNotat(okt));
            System.out.println("\n");
        }
    }

    public void run() {
        System.out.println("Oppgi antall siste gjennomførte treningsøkter du vil se");

        int maxN = this.getMaximumN();

        int n = scanner.nextInt();

        while (!nOk(n, maxN)) {
            System.out.println("Du har ikke nok registrerte treningsøkter. Antallet treningsøkter må være mindre eller lik" + maxN);
            System.out.println("Oppgi antall siste gjennomførte treningsøkter du vil se");
            n = scanner.nextInt();
        }

        List<Treningsokt> treningsokter = getAll(n);
        show(treningsokter);
        super.disconnect();

    }
}
