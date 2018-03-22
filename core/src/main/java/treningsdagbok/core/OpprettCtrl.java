package treningsdagbok.core;

import java.util.Scanner;
import java.sql.Date;
import java.sql.Time;
import java.sql.SQLException;

public class OpprettCtrl extends DBConnection {

    Scanner scanner;

    public OpprettCtrl(Scanner scanner) {
        this.scanner = scanner;
        super.connect();
    }

    public void run() throws SQLException {
        System.out.println("Oppgi hva du vil gjøre.");
        System.out.println("Tast 1 for å registrere et nytt apparat." + "\nTast 2 for å registrere ny øvelse uten apparat." + "\nTast 3 for å registrere ny øvelse med apparat." + "\nTast 4 for å registrere ny treningsøkt" + "\nTast 5 for å gå tilbake til hovedmenyen.");

        int choice = scanner.nextInt();

        while (true) {
            switch (choice) {

                case 1:
                    System.out.println("Oppgi apparatID:");
                    int apparatID = scanner.nextInt();

                    System.out.println("Oppgi navn på apparatet:");
                    String apparatNavn = scanner.nextLine();

                    System.out.println("Beskriv funksjonen til apparatet:");
                    String funksjonsbeskrivelse = scanner.nextLine();

                    Apparat apparat = new Apparat(apparatID, apparatNavn, funksjonsbeskrivelse);

                    apparat.save(conn);

                    System.out.println("Apparatet er registrert.");

                case 2:
                    System.out.println("Oppgi navn på øvelsen:");
                    String ovelseUANavn = scanner.nextLine();

                    System.out.println("Oppgi gruppeID:");
                    int gruppeID1 = scanner.nextInt();

                    OvelseUtenApparat ovelseUtenApparat = new OvelseUtenApparat(ovelseUANavn, gruppeID1);

                    ovelseUtenApparat.save(conn);

                    System.out.println("Øvelsen er registrert.");

                case 3:
                    System.out.println("Oppgi navn på øvelsen:");
                    String ovelseMANavn = scanner.nextLine();

                    System.out.println("Oppgi apparatID:");
                    int ovelseApparatID = scanner.nextInt();

                    System.out.println("Oppgi gruppeID:");
                    int gruppeID2 = scanner.nextInt();

                    OvelseMedApparat ovelseMedApparat = new OvelseMedApparat(ovelseMANavn, ovelseApparatID, gruppeID2);

                    ovelseMedApparat.save(conn);

                    System.out.println("Øvelsen er registrert.");

                case 4:
                    System.out.println("Oppgi øktID:");
                    int oktID = scanner.nextInt();

                    System.out.println("Oppgi dato (format: yyyy-mm-dd):");
                    String datoString = scanner.nextLine();
                    Date dato = Date.valueOf(datoString);

                    System.out.println("Oppgi tidspunkt (format: hh:mm:ss):");
                    String tidspunktString = scanner.nextLine();
                    Time tidspunkt = Time.valueOf(tidspunktString);

                    System.out.println("Oppgi varighet:");
                    int varighet = scanner.nextInt();

                    System.out.println("Oppgi form (1-10):");
                    int form = scanner.nextInt();

                    System.out.println("Oppgi prestasjon (1-10):");
                    int prestasjon = scanner.nextInt();

                    System.out.println("Vil du opprette notat? (ja/nei)");
                    String choiceNotat = scanner.nextLine();

                    Treningsokt treningsokt = new Treningsokt(oktID, dato, tidspunkt, varighet, form, prestasjon);

                    switch (choiceNotat) {
                        case "ja":
                            System.out.println("Oppgi notatID:");
                            int notatID = scanner.nextInt();

                            System.out.println("Hva var formålet med treningsøkten?");
                            String formaal = scanner.nextLine();

                            System.out.println("Hvordan opplevde du treningsøkten?");
                            String opplevelse = scanner.nextLine();

                            Notat notat = new Notat(notatID, formaal, opplevelse, oktID);
                            notat.save(conn);

                            System.out.println("Notatet er registrert.");

                        case "nei":
                            break;
                    }

                    System.out.println("Vil du legge til en øvelse (med/uten) apparat eller (ingen) øvelse?");
                    String choiceOvelse = scanner.nextLine();

                    while (true) {
                        switch (choiceOvelse) {
                            case "med":
                                System.out.println("Oppgi øvelseID:");
                                int ovelseID1 = scanner.nextInt();

                                System.out.println("Oppgi antall kilo:");
                                int kilo = scanner.nextInt();

                                System.out.println("Oppgi antall sett:");
                                int sett = scanner.nextInt();

                                OvelseIOkt ovelseIOkt1 = new OvelseIOkt(oktID, ovelseID1, null, kilo, sett, null, null, true);
                                ovelseIOkt1.save(conn);

                                System.out.println("Øvelsen er registrert.");
                            case "uten":
                                System.out.println("Oppgi øvelseID:");
                                int ovelseID2 = scanner.nextInt();

                                System.out.println("Beskriv øvelsen:");
                                String beskrivelse = scanner.nextLine();

                                OvelseIOkt ovelseIOkt2 = new OvelseIOkt(oktID, ovelseID2, null, -1, -1, beskrivelse, null, false);
                                ovelseIOkt2.save(conn);

                                System.out.println("Øvelsen er registrert.");

                            case "ingen":
                                break;
                        }
                    }

                case 5:
                    super.disconnect();
                    TreningsdagbokMain treningsdagbokMain = new TreningsdagbokMain();
                    treningsdagbokMain.run();
                    break;
            }
        }
    }
}

