package AsystentZakupow.Zarzadzanie;

import AsystentZakupow.Menu.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connection;
import static AsystentZakupow.Zarzadzanie.MojeProdukty.wyszukajWTabeliPoNazwie;
import static AsystentZakupow.Zarzadzanie.MojeProdukty.zrobOdstep;
import static AsystentZakupow.Zarzadzanie.SprawdzCzyProduktJestJuzWTabeli.sprawdzCzyProduktJestJuzWTabeli;
import static AsystentZakupow.Zarzadzanie.WyswietlWszystkieProduktyZTablicy.wyswietlWszystkieProduktyZTablicy;


public class DodajProduktyDoMojejListy {
    static Statement statement;
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void dodajProduktyDoMojejListy() throws IOException, SQLException {

        while (true) {
            zrobOdstep(5);
            wyswietlWszystkieProduktyZTablicy("produkty");
            wyswietlWszystkieProduktyZTablicy("moje_produkty");
            try {
                System.out.println("Podaj ID produktu Lub wpisz nazwe produktu (Moze byc tez sam poczatek mordo xD) ...");
                //String c = "";

                String b = reader.readLine();

                if (b.contains("q")) {
                    Menu.menu();
                } else if (wyszukajWTabeliPoNazwie("produkty", b)) {
                    b = reader.readLine();
                    if (b.contains("q")) {
                        dodajProduktyDoMojejListy();
                    }

                }

                int temp = Integer.parseInt(b);

                try {
                    statement = connection.createStatement();
                    String sql = "SELECT * FROM produkty where ID=" + temp + ";";
                    ResultSet rs = statement.executeQuery(sql);


                    rs.next();
                    String nazwaProduktu = rs.getString("Nazwa Produktu");
                    if (sprawdzCzyProduktJestJuzWTabeli("moje_produkty", nazwaProduktu)) {
                        System.out.println("Masz juz to na liscie!");

                        statement.close();
                        connection.close();
                        dodajProduktyDoMojejListy();
                    }
                    String kategoriaProduktu = rs.getString("kategoria produktu");
                    String sql1 = "INSERT INTO `moje_produkty` (`ID`, `Nazwa Produktu`, `kategoria produktu`) VALUES (NULL, '" + nazwaProduktu + "','"+kategoriaProduktu+"');";
                    statement.executeUpdate(sql1);
                    System.out.println("dodano " + nazwaProduktu + " do listy zakupow.");
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Nie znaleziono takiego produktu w bazie");
                    dodajProduktyDoMojejListy();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
