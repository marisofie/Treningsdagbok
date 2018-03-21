package treningsdagbok.core;

import java.util.Scanner;
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
        if (date.after(maxDate)) {
            return true;
        }
        return false;
    }

    //skal hente ut økter i et visst intervall. Må joine.

    public void groupByID() {

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select ");

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void run() {

    }

}
