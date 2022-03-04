package AsystentZakupow.Zarzadzanie;
import AsystentZakupow.Menu.Menu;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.sql.DriverManager;

import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connect;
import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connection;
import static AsystentZakupow.Zarzadzanie.Kategorie.kategorie;
import static AsystentZakupow.Zarzadzanie.SortowanieIDWBazie.posortujIdWBazie;

public class MojeProdukty {

    static Statement statement;
    //static Connection connection;
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static String b = null;


    public static Boolean wyszukajWTabeliPoNazwie(String nazwaTablicy, String nazwaProduktu) throws IOException, SQLException {
        boolean znalezionoCos = false;


        String nazwaProduktuWTablicy;

        connect();
        statement = connection.createStatement();


        String sql = "SELECT * FROM " + nazwaTablicy + ";";
        try {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                nazwaProduktuWTablicy = rs.getString("Nazwa Produktu");
                if (nazwaProduktuWTablicy.contains(nazwaProduktu)) {
                    System.out.println(id + " " + nazwaProduktuWTablicy);
                    znalezionoCos = true;
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //dodaj produkt do mojej listy, albo jesli jest wpisane q to cofnij do wpisywania nazwy produktu
        //String wpisanaNazwa = reader.readLine();
        //if (wpisanaNazwa.contains("q")){wyszukajWTablicyPoNazwie(nazwaTablicy, nazwaProduktu);}

        return znalezionoCos;
    }



    public static void zrobOdstep(int ileLinijekOdstepu) {
        for (int i=0;i<=ileLinijekOdstepu;i++){
        System.out.println("");
    }}

}