package treningsdagbok.core;

import java.util.Scanner;

public class OpprettCtrl extends DBConnection {

    Scanner scanner;

    public OpprettCtrl(Scanner scanner) {
        this.scanner = scanner;
        super.connect();
    }

    public void run() {
        System.out.println("Oppgi hva du vil gjøre.");
        System.out.println("Tast 1 for å registrere et nytt apparat." + "\nTast 2 for å registrere ny øvelse." + "\nTast 3 for å registrere ny treningsøkt");

        int choice = scanner.nextInt();

        switch(choice) {

            case 1:
        }

    }
}
