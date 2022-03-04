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


public class usunZTablicyOPodanejNazwie{

        static Statement statement;
        public static void usunProduktZTablicy(String nazwaTablicy, String nazwaProduktu ) throws IOException, SQLException {


            try {
                connect();
                Statement statement = connection.createStatement();
                System.out.println(nazwaProduktu);
                    String sql = "DELETE from `"+ nazwaTablicy +"` WHERE `Nazwa Produktu`= '"+ nazwaProduktu +"';";
                    statement.executeUpdate(sql);
                posortujIdWBazie(nazwaTablicy);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }




