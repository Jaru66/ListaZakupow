package AsystentZakupow.Zarzadzanie;

import AsystentZakupow.Menu.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Statement;

import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connect;
import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connection;
import static AsystentZakupow.Zarzadzanie.Kategorie.kategorie;
import static AsystentZakupow.Zarzadzanie.WczytajnazweProduktuODanymIdZTabeli.wczytajNazweProduktuODanymIdZTabeli;
import static AsystentZakupow.Zarzadzanie.WyswietlWszystkieProduktyZTablicy.wyswietlWszystkieProduktyZTablicy;

public class ZmienKategorieWGlownejLiscie {
    static Statement statement;
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void zmienKategorieWGlownejLiscie(){
        try {
            connect();
            statement = connection.createStatement();
            wyswietlWszystkieProduktyZTablicy("produkty");
            System.out.println("Wybierz w ktorym produkcie chcesz zmienic kategorie: ");
            System.out.println("'q' wyjscie");
            String b = reader.readLine();

            if(b.contains("q")){
                Menu.menu();}
            int idProduktu=Integer.parseInt(b);
            String nazwaProduktu = wczytajNazweProduktuODanymIdZTabeli(idProduktu,"produkty");
            String sql1="DELETE from produkty where ID="+idProduktu+";";
            statement.executeUpdate(sql1);

            System.out.println("Podaj nowa kategorie produktu dla:"+nazwaProduktu+ "");
            System.out.println("Wybierz kategorie produktu z listy: ");

            System.out.println(Kategorie.kategorie.size());
            for(int i =1;i<=kategorie.size();i++){System.out.println(i+". "+kategorie.get(i));}
            String c = reader.readLine();
            int wybranaKategoria = Integer.parseInt(c);



            String sql = "INSERT INTO `produkty` (`ID`, `Nazwa Produktu`, `kategoria produktu`) VALUES ('"+idProduktu+"','"+nazwaProduktu+"','"+kategorie.get(wybranaKategoria)+"');";
            statement.executeUpdate(sql);
            zmienKategorieWGlownejLiscie();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
