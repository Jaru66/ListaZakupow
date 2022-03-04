package AsystentZakupow.Zarzadzanie;

import AsystentZakupow.Swing.OknoDodajDoListy;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connect;
import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connection;
import static AsystentZakupow.Zarzadzanie.SortowanieIDWBazie.posortujIdWBazie;

public class WyswietlWszystkieProduktyZTablicy {
    public static String nazwaTablicy;
    static Statement statement;
    public static String b;

    public static void zapiszElementyNaLiscie(String name){

        System.out.println(b);
        if (b.contains("Produkto")){OknoDodajDoListy.wczytajProduktyZBazyDoListyProduktow(name);}
        else if (b.contains("Zakupo")){OknoDodajDoListy.wczytajProduktyZBazyDoListyZakupow(name);}

    }
    public static void wyswietlWszystkieProduktyZTablicy(String nazwaTablicy) throws IOException, SQLException {



        posortujIdWBazie(nazwaTablicy);
        connect();
        String wyswietl = "Zla nazwa tablicy";
        if (nazwaTablicy.equals("moje_produkty")) {
            wyswietl = "Moja Lista Zakupow";
        } else if (nazwaTablicy.equals("produkty")) wyswietl = "Lista wszystkich produktow";
        System.out.println("-----------------------" + wyswietl + "---------------------------");
        statement = connection.createStatement();



        String sql = "SELECT * FROM " + nazwaTablicy + ";";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("Nazwa Produktu");

            // DODANIE PRODUKTU DO LISTY DLA SWINGA
            zapiszElementyNaLiscie(name);

            String kategoriaProduktu = rs.getString("Kategoria Produktu");
            System.out.println(id + " " + name+ "       ["+ kategoriaProduktu+"]");
        }
        System.out.println("--------------------------------------------------");



//        System.out.println("Wpisz e aby weyksportowac liste do dokumentu tekstowego lub wpisz q aby cofnac.");
//        String b= reader.readLine();
//        if (b.contains("q")) {
//            Menu.menu();
//        }else if(b.contains("e")){
//            System.out.println("Tutaj bedzie funkcja do eksprotu listy zakupow");
//        }
    }

    public static class ZapiszProduktyZBazyNaLiscieProduktow extends WyswietlWszystkieProduktyZTablicy {
        public static String b;
        //String b="listaProdukto";
    } public class ZapiszProduktyZBazyNaLiscieZakupow extends WyswietlWszystkieProduktyZTablicy {
        public static String b;
    }

}
