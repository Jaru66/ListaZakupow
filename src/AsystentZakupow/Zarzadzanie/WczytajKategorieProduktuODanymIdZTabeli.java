package AsystentZakupow.Zarzadzanie;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connect;
import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connection;

public class WczytajKategorieProduktuODanymIdZTabeli {
    static Statement statement;
    public static String wczytajKategorieProduktuODanymIdZTabeli(int id, String nazwaTabeli) {

        String kategoriaProduktu = null;
        try {
            connect();
            statement = connection.createStatement();
            String sql = "SELECT * FROM " + nazwaTabeli + " where ID=" + id + ";";
            ResultSet rs = statement.executeQuery(sql);

            rs.next();
            //String nazwaProduktu = rs.getString("Nazwa Produktu");
            kategoriaProduktu = rs.getString("kategoria produktu");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return kategoriaProduktu;
    }
}
