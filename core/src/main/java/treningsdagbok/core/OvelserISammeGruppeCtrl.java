package treningsdagbok.core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OvelserISammeGruppeCtrl extends DBConnection {

    private Scanner scanner;

    public OvelserISammeGruppeCtrl(Scanner scanner) {
        this.scanner = scanner;
        super.connect();
    }

    public List<Ovelse> getOvelserByGruppeID(int gruppeID) {
        List<Ovelse> ovelser = new ArrayList<>();
        Ovelse ovelse = null;

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT OvelseNavn FROM OvelseMedApparat WHERE OvelseMedApparat.GruppeID = " + gruppeID + " UNION SELECT OvelseNavn FROM OvelseUtenApparat WHERE OvelseUtenApparat.GruppeID = " + gruppeID);

            while (rs.next()) {
                boolean created = false;
                String ovelseNavn = rs.getString("OvelseNavn");
                ovelse = new OvelseUtenApparat(0, ovelseNavn, 0);
                ovelser.add(ovelse);
            }


            rs.close();
            stmt.close();

            return ovelser;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Kunne ikke hente øvelser i gruppe " + gruppeID);
        }
    }

    public void show(List<Ovelse> ovelser) {
        for (Ovelse ovelse : ovelser) {
            System.out.println(ovelse.toString());
            System.out.println("\n");
        }
    }

    public void run() throws SQLException{
        System.out.println("Oppgi hva du vil gjøre.");
        System.out.println("Tast 1 for å opprette ny øvelsesgruppe." + "\nTast 2 for å se øvelser i en gitt gruppe.");

        int choice = scanner.nextInt();

        switch(choice) {

            case(1):
                System.out.println("Oppgi gruppeID:");
                int gruppeID1 = scanner.nextInt();

                System.out.println("Oppgi gruppernavn:");
                String gruppenavn = scanner.next();

                Ovelsesgruppe ovelsesgruppe = new Ovelsesgruppe(gruppeID1, gruppenavn);

                ovelsesgruppe.save(conn);

                System.out.println("Øvelsesgruppen ble lagt til i dagboken.");

                break;

            case(2):
                System.out.println("Oppgi gruppeID til gruppen du ønsker å se:");
                int gruppeID2 = scanner.nextInt();

                List<Ovelse> ovelser = getOvelserByGruppeID(gruppeID2);

                if (ovelser.size() == 0) {
                    System.out.println("Gruppe " + gruppeID2 + " inneholder ingen øvelser enda.");
                }
                else {
                    System.out.println("Øvelser i gruppe " + gruppeID2 + ":");
                    show(ovelser);
                }

                break;
        }

        System.out.println("Hvis du vil tilbake til hovedmenyen skriv (1), hvis du vil fortsette skriv (2)");

        int choice2 = scanner.nextInt();

        switch(choice2) {
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
