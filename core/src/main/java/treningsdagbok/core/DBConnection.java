package treningsdagbok.core;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    protected Connection connection;

    public void connect() {

        try {
            MysqlDataSource dataSource = new MysqlDataSource();
            Properties dbProperties = new Properties();

            dbProperties.put("url", "jdbc:mysql://mysql.stud.ntnu.no/marisler_dbproject:3306");
            dbProperties.put("user", "marenwe_project");
            dbProperties.put("password", "WRHNB9pnZYCLYpKrGnrt");

            dataSource.setURL(dbProperties.getProperty("url"));
            dataSource.setUser(dbProperties.getProperty("user"));
            dataSource.setPassword(dbProperties.getProperty("password"));

            connection = dataSource.getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}