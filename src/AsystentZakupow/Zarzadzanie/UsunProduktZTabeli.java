package AsystentZakupow.Zarzadzanie;

import AsystentZakupow.Menu.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Statement;

import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connect;
import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connection;
import static AsystentZakupow.Zarzadzanie.MojeProdukty.zrobOdstep;
import static AsystentZakupow.Zarzadzanie.SortowanieIDWBazie.posortujIdWBazie;
import static AsystentZakupow.Zarzadzanie.WyswietlWszystkieProduktyZTablicy.wyswietlWszystkieProduktyZTablicy;

public class UsunProduktZTabeli {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static Statement statement;
    public static void usunProduktZTablicy(String nazwaTablicy) throws IOException, SQLException {


        try {
            connect();
            zrobOdstep(15);
            Statement statement = connection.createStatement();

            wyswietlWszystkieProduktyZTablicy(nazwaTablicy);

            System.out.println("Wpisz numer ID produktu do usuniecia lub wpisz q aby wrocic do menu");
            String b = reader.readLine();
            if (b.contains("q")) {
                Menu.menu();
            }
            int temp = Integer.parseInt(b);
            try {
                String sql = "DELETE from " + nazwaTablicy + " WHERE ID=" + temp + ";";
                statement.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("usunieto");
            posortujIdWBazie(nazwaTablicy);
            usunProduktZTablicy(nazwaTablicy);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
