package treningsdagbok.core;

import java.util.Scanner;
import java.sql.*;

public class ResultatOvelseCtrl extends DBConnection {

    private Scanner scanner;

    public ResultatOvelseCtrl(Scanner scanner) {
        this.scanner = scanner;
        super.connect();
    }

    //skal hente ut økter i et visst intervall. Må joine.

    public void groupByID() {

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select OktID, Dato from Treningsokt");

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void run() {

    }

}
