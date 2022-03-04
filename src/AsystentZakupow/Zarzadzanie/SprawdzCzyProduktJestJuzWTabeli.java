package AsystentZakupow.Zarzadzanie;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connect;
import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connection;

public class SprawdzCzyProduktJestJuzWTabeli {

    static Statement statement;

    public static boolean sprawdzCzyProduktJestJuzWTabeli(String nazwaTablicy, String nazwaProduktu) throws IOException, SQLException {
        connect();
        statement = connection.createStatement();
        String sql = "SELECT * FROM " + nazwaTablicy + ";";

        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {

            String nazwa= rs.getString("Nazwa Produktu");
            if (nazwaProduktu.equals(nazwa)) {
                connection.close();
                statement.close();
                System.out.println("true");
                return true;
            }
        }
        System.out.println("false");
        return false;
    }
}
