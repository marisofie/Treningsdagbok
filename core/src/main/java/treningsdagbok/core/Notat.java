package treningsdagbok.core;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Notat implements ActiveDomainObject{

    private int notatID = -1;
    private String formaal;
    private String opplevelse;


    public Notat(int notatID, String formaal, String opplevelse) {
        this.notatID = notatID;
        this.formaal = formaal;
        this.opplevelse = opplevelse;
    }

    public void setNotatID(int notatID) {
        this.notatID = notatID;
    }

    public int getNotatID() {
        return this.notatID;
    }

    public void setFormaal(String formaal) {
        this.formaal = formaal;
    }

    public String getFormaal() {
        return this.formaal;
    }

    public void setOpplevelse(String opplevelse) {
        this.opplevelse = opplevelse;
    }

    public String getOpplevelse() {
        return this.opplevelse;
    }

    public void getByID(Connection connection) {

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select Formaal, Opplevelse from Notat where NotatID = " + this.notatID);

            while (rs.next()) {
                this.formaal = rs.getString("Formaal");
                this.opplevelse = rs.getString("Opplevelse");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void save(Connection connection) {

        if (this.notatID == -1 || this.opplevelse == null || this.formaal == null){
            throw new IllegalStateException("Missing input.");
        }

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("insert into Notat values ("+this.notatID+","+this.formaal+","+this.opplevelse+")");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String toString() {
        return "Notat: " + this.notatID + ", " + this.formaal + ", " + this.opplevelse;
    }
}