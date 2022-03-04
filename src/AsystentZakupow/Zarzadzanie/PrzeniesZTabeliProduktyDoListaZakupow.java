package AsystentZakupow.Zarzadzanie;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connect;
import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connection;

public class PrzeniesZTabeliProduktyDoListaZakupow {
    static Statement statement;
    public static void Przenies(String nazwaProduktuDoPrzeniesienia){
        try {
            connect();
            statement = connection.createStatement();
            String sql = "SELECT `kategoria produktu` FROM `produkty` WHERE `Nazwa Produktu`='"+ nazwaProduktuDoPrzeniesienia +"';";
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
                    String kategoria= rs.getString("kategoria produktu");
            System.out.println(kategoria);
                    if(!SprawdzCzyProduktJestJuzWTabeli.sprawdzCzyProduktJestJuzWTabeli("moje_produkty",nazwaProduktuDoPrzeniesienia)) {
                        String sql1 = "INSERT INTO `moje_produkty` (`ID`, `Nazwa Produktu`, `kategoria produktu`) VALUES (NULL, '"+ nazwaProduktuDoPrzeniesienia +"','"+ kategoria +"');";
                        statement.executeUpdate(sql1);
                    }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
