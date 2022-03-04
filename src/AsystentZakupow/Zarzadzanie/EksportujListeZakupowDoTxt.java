package AsystentZakupow.Zarzadzanie;

import AsystentZakupow.BazaDanych.PolaczenieZBaza;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connect;
import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connection;

public class EksportujListeZakupowDoTxt {
    static Statement statement;
    public static void eksportujTabeleDoTxt(String nazwaTablicy)throws FileNotFoundException {
        try {
            PrintWriter zapis = new PrintWriter("C:\\Users\\User\\Desktop\\lista.txt");

            connect();
            statement = connection.createStatement();
            String sql = "SELECT * FROM " + nazwaTablicy + " ORDER BY `"+nazwaTablicy+"`.`kategoria produktu` ASC;";

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("Nazwa Produktu");
                String kategoriaProduktu = rs.getString("Kategoria Produktu");
                zapis.println(name);

            }
            System.out.println("Lista zakupow--- lista.txt --- zostala wygenerowana na pulpicie");
            zapis.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
