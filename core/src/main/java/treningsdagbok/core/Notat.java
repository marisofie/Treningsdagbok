package treningsdagbok.core;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Notat implements ActiveDomainObject{

    private int notatID;
    private String formaal;
    private String opplevelse;
    private int oktID


    public Notat(int notatID, String formaal, String opplevelse, int oktID) {
        this.notatID = notatID;
        this.formaal = formaal;
        this.opplevelse = opplevelse;
        this.oktID = oktID;
    }

    public void setNotatID(int notatID) {
        this.notatID = notatID;
    }

    public int getNotatID() {
        return notatID;
    }

    public void setFormaal(String formaal) {
        this.formaal = formaal;
    }

    public String getFormaal() {
        return formaal;
    }

    public void setOpplevelse(String opplevelse) {
        this.opplevelse = opplevelse;
    }

    public String getOpplevelse() {
        return opplevelse;
    }


    @Override
    public void save(Connection connection) {

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into Notat values ("+this.notatID+","+this.formaal+","+this.opplevelse+")");

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Kunne ikke lagre notat." + e);        }

    }

    public void getByID(Connection conn) {

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select Formaal, Opplevelse from Notat where NotatID = " + this.notatID);

            while (rs.next()) {
                this.formaal = rs.getString("Formaal");
                this.opplevelse = rs.getString("Opplevelse");
            }

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Kunne ikke lagre notat." + e);
        }
    }

    @Override
    public String toString() {
        return "Notat{" +
                "notatID=" + notatID +
                ", formaal='" + formaal + '\'' +
                ", opplevelse='" + opplevelse + '\'' +
                ", oktID=" + oktID +
                '}';
    }
}