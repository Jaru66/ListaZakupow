package AsystentZakupow.KreatorPodstawowejListyZakupow;

import AsystentZakupow.Menu.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Statement;

import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connect;
import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connection;
import static AsystentZakupow.Zarzadzanie.DodajProduktDoTabeli.dodajProduktDoTabeli;
import static AsystentZakupow.Zarzadzanie.DodajProduktyDoMojejListy.dodajProduktyDoMojejListy;
import static AsystentZakupow.Zarzadzanie.MojeProdukty.zrobOdstep;
import static AsystentZakupow.Zarzadzanie.SprawdzCzyProduktJestJuzWTabeli.sprawdzCzyProduktJestJuzWTabeli;
import static AsystentZakupow.Zarzadzanie.WczytajKategorieProduktuODanymIdZTabeli.wczytajKategorieProduktuODanymIdZTabeli;
import static AsystentZakupow.Zarzadzanie.WczytajnazweProduktuODanymIdZTabeli.wczytajNazweProduktuODanymIdZTabeli;
import static AsystentZakupow.Zarzadzanie.WyswietlWszystkieProduktyZTablicy.wyswietlWszystkieProduktyZTablicy;

public class DodajDoPodstawowejListy {
    static Statement statement;
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void dodajDoPodstawowejListy()    {

        String skadMaPobracProdukt="produkty";
        while (true)
        {
            zrobOdstep(15);
            try {
                connect();
                statement = connection.createStatement();
                wyswietlWszystkieProduktyZTablicy(skadMaPobracProdukt);
                System.out.println("Wybierz produkt ktory chcesz dodac do kreatora, lub wpisz q aby wyjsc do menu");
                String b = reader.readLine();
                if (b.contains("q")) {
                    Menu.menu();
                }


                int wybranyProdukt= Integer.parseInt(b);

                try {
                    System.out.println(wczytajNazweProduktuODanymIdZTabeli(wybranyProdukt,"produkty"));
                    //dodajProduktDoTabeli("produkty_podstawowe",wczytajNazweProduktuODanymIdZTabeli(wybranyProdukt,skadMaPobracProdukt),wczytajKategorieProduktuODanymIdZTabeli(wybranyProdukt,skadMaPobracProdukt));
                    if (sprawdzCzyProduktJestJuzWTabeli("produkty_podstawowe",      wczytajNazweProduktuODanymIdZTabeli(wybranyProdukt,"produkty")   )  ) {

                        System.out.println("Masz juz to na liscie!");

                        statement.close();
                        connection.close();
                        dodajDoPodstawowejListy();
                    }
                    String kategoriaProduktu=wczytajKategorieProduktuODanymIdZTabeli(wybranyProdukt,skadMaPobracProdukt);

                    String nazwaProduktu=wczytajNazweProduktuODanymIdZTabeli(wybranyProdukt,skadMaPobracProdukt);

                    dodajProduktDoTabeli("produkty_podstawowe",nazwaProduktu,kategoriaProduktu);


                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Nie znaleziono takiego produktu w bazie");
                    dodajProduktyDoMojejListy();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
