package treningsdagbok.core;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class TreningsdagbokMain {

    private Scanner scanner;
    private List<Integer> validInput = new ArrayList<>();

    public TreningsdagbokMain() {
        this.scanner = new Scanner(System.in);
        validInput.add(1);
        validInput.add(2);
        validInput.add(3);
        validInput.add(4);
        validInput.add(5);
        validInput.add(6);
    }

    public void run() throws SQLException {
        while (true) {
            String welcome = "Treningsdagbok";
            String choices = "Hva vil du gjøre?" + "\nTast 1 for å registrere apparat, øvelse eller treningsøkt"
                    + "\nTast 2 for å få opp informasjon om tidligere treningsøkter" + "\nTast 3 for å få opp resultatlogg"
                    + "\nTast 4 for å lage øvelsegruppe eller finne øvelser etter gruppe" + "\nTast 5 for å få opp form og prestasjon til treningsøkter"
                    + "\nSkriv 6 for å avslutte";

            System.out.println(welcome + "\n" + choices);

            int choice = this.scanner.nextInt();

            while (!validInput.contains(choice)) {
                System.out.println("Klarte ikke registrere valget ditt. Prøv igjen!");
                System.out.println(choices);
                choice = scanner.nextInt();
            }

            switch(choice) {
                case 1:
                    break;
                case 2:
                    SisteTreningsokterCtrl sisteTreningsokterCtrl = new SisteTreningsokterCtrl(this.scanner);
                    sisteTreningsokterCtrl.run();
                    break;
                case 3:
                    break;
                case 4:
                    OvelserISammeGruppeCtrl ovelserISammeGruppeCtrl = new OvelserISammeGruppeCtrl(this.scanner);
                    ovelserISammeGruppeCtrl.run();
                    break;
                case 5:
                    FormPrestasjonCtrl formPrestasjonCtrl = new FormPrestasjonCtrl(this.scanner);
                    formPrestasjonCtrl.run();
                    break;
                case 6:
                    System.out.println("God trening!");
                    break;
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        TreningsdagbokMain treningsdagbok = new TreningsdagbokMain();
        treningsdagbok.run();
    }
}
