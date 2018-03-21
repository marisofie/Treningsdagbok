package treningsdagbok.core;

import java.util.Scanner;

public class OvelseResultaterCtrl extends DBConnection {

    private Scanner scanner;

    public OvelseResultaterCtrl(Scanner scanner) {
        this.scanner = scanner;
        super.connect();
    }


}
