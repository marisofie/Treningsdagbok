package treningsdagbok.core;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    protected Connection conn;

    public void connect() {

        try {
            MysqlDataSource dataSource = new MysqlDataSource();
            Properties dbProperties = new Properties();

            dbProperties.put("url", "jdbc:mysql://mysql.stud.ntnu.no:3306/marisler_dbproject?autoReconnect=true&useSSL=false");
            dbProperties.put("user", null);
            dbProperties.put("password", null);

            dataSource.setURL(dbProperties.getProperty("url"));
            dataSource.setUser(dbProperties.getProperty("user"));
            dataSource.setPassword(dbProperties.getProperty("password"));

            conn = dataSource.getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void disconnect() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}