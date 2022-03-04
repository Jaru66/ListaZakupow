package AsystentZakupow.Zarzadzanie;

import AsystentZakupow.Menu.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Statement;

import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connect;
import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connection;

public class WyczyscListeZakupow {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static Statement statement;
    public static void wyczyscListeZakupow() throws IOException, SQLException {
        System.out.println("Czy napewno chcesz usunac cala liste zakupow? Tej operacji nie da sie cofnac.  wpisz 'usun' aby kontynuowac lub q aby wyjsc do menu.");
        String b = reader.readLine();
        if (b.contains("q")) {
            Menu.menu();
        } else if (b.contains("usun")) {
            try {
                connect();
                statement = connection.createStatement();
                String sql = "DELETE FROM moje_produkty";
                statement.executeUpdate(sql);
                statement.close();
                connection.close();
                Menu.menu();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void wyczyscListeZakupowBezPytania() throws IOException, SQLException {

            try {
                connect();
                statement = connection.createStatement();
                String sql = "DELETE FROM moje_produkty";
                statement.executeUpdate(sql);
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

}
