package AsystentZakupow.Zarzadzanie;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connect;
import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connection;
import static AsystentZakupow.Zarzadzanie.SprawdzCzyProduktJestJuzWTabeli.sprawdzCzyProduktJestJuzWTabeli;

public class DodajProduktDoTabeli {

    static Statement statement;
    public static Connection dodajProduktDoTabeli(String nazwaTabeli, String nazwaProduktu, String kategoriaProduktu) {

        try {
            sprawdzCzyProduktJestJuzWTabeli(nazwaTabeli,nazwaProduktu);
            connect();
            statement = connection.createStatement();
            String sql = "INSERT INTO `" + nazwaTabeli + "` (`ID`, `Nazwa Produktu`, `Kategoria produktu`) VALUES (NULL, '"+nazwaProduktu+"', '"+ kategoriaProduktu+"');";
            statement.executeUpdate(sql);
            System.out.println("Dodano "+nazwaProduktu+" do tabeli "+nazwaTabeli+" w kategorii "+kategoriaProduktu+".");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
