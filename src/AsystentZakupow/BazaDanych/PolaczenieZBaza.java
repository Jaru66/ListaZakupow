package AsystentZakupow.BazaDanych;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PolaczenieZBaza {
   public static Connection connection;
   public static Statement statement;

    public static Connection connect() throws IOException, SQLException {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost/asystent_zakupow";
            String username = "user";
            String password = "password";
            Class.forName(driver);
            try {
                connection = DriverManager.getConnection(url, username, password);
                statement = connection.createStatement();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
