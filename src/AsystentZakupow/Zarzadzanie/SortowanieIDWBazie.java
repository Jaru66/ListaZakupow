package AsystentZakupow.Zarzadzanie;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connect;
import static AsystentZakupow.BazaDanych.PolaczenieZBaza.connection;



public class SortowanieIDWBazie {
    static Statement statement;

    public static void posortujIdWBazie(String nazwaTablicy) throws IOException, SQLException {
        try {

            //PolaczenieZBaza.connect();
            connect();
            //System.out.println("--------------------------------------------------");
            statement = connection.createStatement();
            String sql = "SELECT * FROM " + nazwaTablicy + ";";
            int najnizszeIdWTabeli = 1;
            int id = 0;
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                statement = connection.createStatement();

                id = rs.getInt("id");
                String name = rs.getString("Nazwa Produktu");
                String kategoriaProduktu= rs.getString("Kategoria produktu");
                if (najnizszeIdWTabeli == id) {
                    najnizszeIdWTabeli++;
                } else {
                    String sql1 = "INSERT INTO `" + nazwaTablicy + "` (`ID`, `Nazwa Produktu`, `kategoria produktu`) VALUES ('"+najnizszeIdWTabeli+"', '"+name+"','"+kategoriaProduktu+"');";
                    statement.executeUpdate(sql1);
                    String sql2 = "DELETE FROM `" + nazwaTablicy + "` WHERE `" + nazwaTablicy + "`.`ID` = " + id + ";";
                    statement.executeUpdate(sql2);
                    najnizszeIdWTabeli++;
                }
            }

            //System.out.println("--------------------------------------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("__Cos poszlo nie tak__");
        }
        statement.close();
        connection.close();

    }
}
