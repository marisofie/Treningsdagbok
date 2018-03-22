package treningsdagbok.core;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class Apparat implements ActiveDomainObject {

    private int apparatID;
    private String apparatNavn;
    private String funksjonsbeskrivelse;

    public Apparat(int apparatID, String apparatNavn, String funksjonsbeskrivelse) {
        this.apparatID = apparatID;
        this.apparatNavn = apparatNavn;
        this.funksjonsbeskrivelse = funksjonsbeskrivelse;
    }

    public int getApparatID() {
        return apparatID;
    }

    public String getApparatNavn() {
        return apparatNavn;
    }

    public void setApparatNavn(String apparatNavn) {
        this.apparatNavn = apparatNavn;
    }

    public String getFunksjonsbeskrivelse() {
        return funksjonsbeskrivelse;
    }

    public void setFunksjonsbeskrivelse(String funksjonsbeskrivelse) {
        this.funksjonsbeskrivelse = funksjonsbeskrivelse;
    }

    @Override
    public void save(Connection conn) {
        try {

            String sql = "insert into Apparat values (?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, this.apparatID);
            stmt.setString(2, this.apparatNavn);
            stmt.setString(3, this.funksjonsbeskrivelse);

            stmt.executeUpdate();

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Kunne ikke registrere apparat." + e);
        }
    }

    public static List<Apparat> getAll(Connection connection) {

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Apparat");

            List<Apparat> apparater = new ArrayList<>();


            while (rs.next()) {
                int apparatID = rs.getInt("ApparatID");
                String apparatNavn = rs.getString("ApparatNavn");
                String funkbeskrivelse = rs.getString("Funksjonsbeskrivelse");

                apparater.add(new Apparat(apparatID, apparatNavn, funkbeskrivelse));
                System.out.print(apparater);
            }

            rs.close();
            stmt.close();

            return apparater;

        } catch (SQLException e) {
            throw new RuntimeException("Kunne ikke hente alle apparater" + e);
        }

    }

    @Override
    public String toString() {
        return "Apparat{" +
                "apparatID=" + apparatID +
                ", apparatNavn='" + apparatNavn + '\'' +
                ", funksjonsbeskrivelse='" + funksjonsbeskrivelse + '\'' +
                '}';
    }
}
