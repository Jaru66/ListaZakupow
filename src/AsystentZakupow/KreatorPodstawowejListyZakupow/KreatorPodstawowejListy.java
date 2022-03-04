package AsystentZakupow.KreatorPodstawowejListyZakupow;

import AsystentZakupow.Menu.Menu;

import javax.swing.plaf.nimbus.State;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connect;
import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connection;
import static AsystentZakupow.Zarzadzanie.DodajProduktDoTabeli.dodajProduktDoTabeli;
import static AsystentZakupow.Zarzadzanie.SprawdzCzyProduktJestJuzWTabeli.sprawdzCzyProduktJestJuzWTabeli;
import static AsystentZakupow.Zarzadzanie.WczytajKategorieProduktuODanymIdZTabeli.wczytajKategorieProduktuODanymIdZTabeli;

public class KreatorPodstawowejListy {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static Statement statement;
    public static void kreatorPodstawowejListy()    {
        try {
            connect();
            statement = connection.createStatement();
            String sql = "SELECT * FROM produkty_podstawowe;";
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()){
                String nazwaProduktu = rs.getString("Nazwa Produktu");
                System.out.println("Czy chcesz dodac:        "+nazwaProduktu+"       do twojej listy zakupow?");
                System.out.println();
                System.out.println("Wpisz a zeby dodac lub wcisnij enter aby pominac produkt.");
                System.out.println("Wpisz q zeby wyjsc do menu.");

                String b= reader.readLine();
                if (b.contains("q")){
                    Menu.menu();}
                else if(b.contains("a")){if(sprawdzCzyProduktJestJuzWTabeli("moje_produkty",nazwaProduktu)==false)  dodajProduktDoTabeli("moje_produkty",nazwaProduktu, wczytajKategorieProduktuODanymIdZTabeli(rs.getInt("ID"),"produkty_podstawowe")  );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
