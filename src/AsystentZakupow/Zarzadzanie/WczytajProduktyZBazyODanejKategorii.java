package AsystentZakupow.Zarzadzanie;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connect;
import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connection;
import static AsystentZakupow.Swing.OknoDodajDoListy.listaProduktow;
import static AsystentZakupow.Swing.OknoDodajDoListy.listaZakupow;

public class WczytajProduktyZBazyODanejKategorii {
    static Statement statement;
    static String nazwaProduktu;
    public WczytajProduktyZBazyODanejKategorii(String nazwaTabeli,String kategoria){
        try {
            listaProduktow.clear();
            connect();
            statement = connection.createStatement();
            String sql = "SELECT * FROM " + nazwaTabeli + " where `kategoria produktu` = '"+ kategoria +"';";
            ResultSet rs = statement.executeQuery(sql);

                while (rs.next()){
                nazwaProduktu = rs.getString("Nazwa Produktu");
                listaProduktow.addElement(nazwaProduktu);
            }

            connection.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }
}
