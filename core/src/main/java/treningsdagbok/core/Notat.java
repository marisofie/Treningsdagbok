package treningsdagbok.core;

import java.sql.*;

public class Notat implements ActiveDomainObject{

    private int notatID;
    private String formaal;
    private String opplevelse;
    private int oktID;


    public Notat(int notatID, String formaal, String opplevelse, int oktID) {
        this.notatID = notatID;
        this.formaal = formaal;
        this.opplevelse = opplevelse;
        this.oktID = oktID;
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
    public void save(Connection conn) {

        try {
            String sql = "INSERT INTO Notat VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, this.notatID);
            stmt.setString(2, this.formaal);
            stmt.setString(3, this.opplevelse);
            stmt.setInt(4, this.oktID);

            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Kunne ikke lagre notat." + e);        }

    }

    public void getByID(Connection conn) {

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Formaal, Opplevelse FROM Notat WHERE NotatID = " + this.notatID);

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
        return "Notat: " +
                "\nNotatID: " + notatID +
                "\nFormaal: '" + formaal + '\'' +
                "\nOpplevelse: '" + opplevelse + '\'';
    }
}