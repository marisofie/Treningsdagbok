package treningsdagbok.core;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;


public class ResultatOvelseCtrl extends DBConnection {

    private Scanner scanner;

    public ResultatOvelseCtrl(Scanner scanner) {
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

    public List<OvelseIOkt> getOvelseResultater(Date start, Date end, String ovelseNavn, boolean medApparat) {

        List<OvelseIOkt> ovelser = new ArrayList<>();



        if (medApparat) {

            try {

                String sql = "SELECT OvelseNavn, Kilo, Sett, Dato FROM ((OvelseMedApparat INNER JOIN OvelseIOktMedApparat ON (OvelseMedApparat.OvelseMAID = OvelseIOktMedApparat.OvelseMAID)) INNER JOIN Treningsokt ON (Treningsokt.OktID = OvelseIOktMedApparat.OktID)) WHERE OvelseNavn = ? AND Dato BETWEEN ? AND ?";
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setString(1, ovelseNavn);
                stmt.setDate(2, start);
                stmt.setDate(3, end);

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    String ovelsenavn = rs.getString("OvelseNavn");
                    int kilo = rs.getInt("Kilo");
                    int sett = rs.getInt("Sett");
                    Date dato = rs.getDate("Dato");

                    ovelser.add(new OvelseIOkt(0,0, ovelseNavn, kilo, sett, null, dato, true));
                }

                rs.close();
                stmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Kan ikke hente øvelsene.");
            }
        }

        else if(!medApparat) {
            try {

                String sql = "SELECT OvelseNavn, Beskrivelse, Dato FROM (OvelseIOktUtenApparat INNER JOIN OvelseUtenApparat ON (OvelseUtenApparat.OvelseUAID = OvelseIOktUtenApparat.OvelseUAID) INNER JOIN Treningsokt ON (Treningsokt.OktID = OvelseIOktUtenApparat.OktID)) WHERE (OvelseNavn = ? AND Dato BETWEEN ? AND ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setString(1, ovelseNavn);
                stmt.setDate(2, start);
                stmt.setDate(3, end);

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    String ovelsenavn = rs.getString("OvelseNavn");
                    String beskrivelse = rs.getString("Beskrivelse");
                    Date dato = rs.getDate("Dato");

                    ovelser.add(new OvelseIOkt(0, 0, ovelseNavn,0,  0, beskrivelse, dato, false));
                }

                rs.close();
                stmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Kan ikke hente øvelsene.");
            }

        }
        return ovelser;

    }

    public void show(List<OvelseIOkt> ovelser, boolean medApparat) {
        if (medApparat) {
            for (OvelseIOkt ovelse : ovelser) {
                System.out.println("OvelseNavn: " + ovelse.getOvelseNavn() +
                        "\nDato: " + ovelse.getDato() + "\nKilo: " + ovelse.getKilo() +
                        "\nSett: " + ovelse.getSett());
                System.out.println("\n");
            }
        } else {
            for (OvelseIOkt ovelse : ovelser) {
                System.out.println("OvelseNavn: " + ovelse.getOvelseNavn() +
                        "\nDato: " + ovelse.getDato() + "\nBeskrivelse: " + ovelse.getBeskrivelse());
                System.out.println("\n");
            }
        }
    }


    public void run() throws SQLException {
        System.out.println("Oppgi en startdato for ovelsene du vil se, på formen YYYY-MM-DD");
        String startDate = scanner.next().trim();
        Date start = Date.valueOf(startDate);

        Date maxDate = getMaxDate();


        while (!dateOK(start, maxDate)) {

            System.out.println("Oppgi en startdato for ovelsene du vil se, på formen YYYY-MM-DD");
            startDate = scanner.next().trim();
            start = Date.valueOf(startDate);
        }

        System.out.println("Oppgi en sluttdato for ovelsene du vil se, på formen YYYY-MM-DD");
        String stopDate = scanner.next().trim();
        Date stop = Date.valueOf(stopDate);

        System.out.println("Oppgi ovelsenavnet på ovelsen du vil se");
        String ovelsenavn = scanner.next().trim();

        System.out.println("Er det en øvelse med apparat, tast (1), er den uten tast (2)");

        int medApparat = scanner.nextInt();

        switch(medApparat) {
            case 1:
                List<OvelseIOkt> ovelserMA = getOvelseResultater(start, stop, ovelsenavn, true);
                if (ovelserMA.size() == 0) {
                    System.out.println("Du har ingen registrerte resultater i oppgitt tidsintervall for øvelsen " + ovelsenavn);
                } else {
                    show(ovelserMA, true);
                }

            case 2:
                List<OvelseIOkt> ovelserUA = getOvelseResultater(start, stop, ovelsenavn, false);
                if (ovelserUA.size() == 0) {
                    System.out.println("Du har ingen registrerte resultater i oppgitt tidsintervall for øvelsen " + ovelsenavn);
                } else {
                    show(ovelserUA, false);
                }
        }

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
