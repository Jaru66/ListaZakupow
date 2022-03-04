package AsystentZakupow;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import AsustentZakupow.*;

import AsystentZakupow.Menu.Menu;
import AsystentZakupow.Swing.OknoDodajDoListy;
import AsystentZakupow.Swing.SwingWindow;
import AsystentZakupow.Zarzadzanie.MojeProdukty;


import static AsystentZakupow.Zarzadzanie.Kategorie.kategorie;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        kategorie();    //wczytanie wszystkich kategorii produktow - musi byc bo bedzie lipa

        SwingWindow.main();
        //Menu.menu();
        //OknoDodajDoListy.main();
    }
}
