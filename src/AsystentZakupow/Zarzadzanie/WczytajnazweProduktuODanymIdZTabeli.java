package AsystentZakupow.Zarzadzanie;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connect;
import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connection;

public class WczytajnazweProduktuODanymIdZTabeli {
    static Statement statement;
    public static String wczytajNazweProduktuODanymIdZTabeli(int id,String nazwaTabeli) {

        String nazwaProduktu = "";
        try {
            connect();
            statement = connection.createStatement();
            String sql = "SELECT * FROM " + nazwaTabeli + " where ID=" + id + ";";
            ResultSet rs = statement.executeQuery(sql);

            rs.next();
            nazwaProduktu = rs.getString("Nazwa Produktu");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("wczytajNazweProduktu...."+nazwaProduktu+"....");
        return nazwaProduktu;
    }
}
