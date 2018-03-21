package treningsdagbok.core;

import java.util.Scanner;
import java.sql.Date;
import java.sql.Time;

public class OpprettCtrl extends DBConnection {

    Scanner scanner;

    public OpprettCtrl(Scanner scanner) {
        this.scanner = scanner;
        super.connect();
    }

    public void run() {
        System.out.println("Oppgi hva du vil gjøre.");
        System.out.println("Tast 1 for å registrere et nytt apparat." + "\nTast 2 for å registrere ny øvelse uten apparat." + "\nTast 3 for å registrere ny øvelse med apparat." + "\nTast 4 for å registrere ny treningsøkt");

        int choice = scanner.nextInt();

        switch(choice) {

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

                super.disconnect();


            case 2:
                System.out.println("Oppgi øvelseID:");
                int ovelseUAID = scanner.nextInt();

                System.out.println("Oppgi navn på øvelsen:");
                String ovelseUANavn = scanner.nextLine();

                OvelseUtenApparat ovelseUtenApparat = new OvelseUtenApparat(ovelseUAID, ovelseUANavn);

                ovelseUtenApparat.save(conn);

                System.out.println("Øvelsen er registrert.");

                super.disconnect();

            case 3:
                System.out.println("Oppgi øvelseID:");
                int ovelseMAID = scanner.nextInt();

                System.out.println("Oppgi navn på øvelsen:");
                String ovelseMANavn = scanner.nextLine();

                System.out.println("Oppgi apparatID:");
                int ovelseApparatID = scanner.nextInt();

                OvelseMedApparat ovelseMedApparat = new OvelseMedApparat(ovelseMAID, ovelseMANavn, ovelseApparatID);

                ovelseMedApparat.save(conn);

                System.out.println("Øvelsen er registrert.");

                super.disconnect();

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

                Treningsokt treningsokt = new Treningsokt(oktID, dato, tidspunkt, varighet, form, prestasjon);

                super.disconnect();
        }

    }
}
