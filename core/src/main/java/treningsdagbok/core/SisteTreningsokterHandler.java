package treningsdagbok.core;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SisteTreningsokterHandler extends DBConnection {

    private Scanner scanner;

    public SisteTreningsokterHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getMaximumN() {
        super.connect();
        int maxN = -1;

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as maxN FROM Treningsokt");

            while (rs.next()) {
                maxN = rs.getInt("maxN");
            }

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

            return treningsokter;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot get last n Treningsokter.");
        }
    }

    public void show(List<Treningsokt> treningsokter) {
        for (Treningsokt okt : treningsokter) {
            System.out.println(okt.toString());
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

    public static void main(String[] args) throws SQLException {
        SisteTreningsokterHandler x = new SisteTreningsokterHandler(new Scanner(System.in));
        x.run();
    }
}
