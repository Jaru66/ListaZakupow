package AsystentZakupow.Swing;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

import static AsystentZakupow.Swing.SwingWindow.frame;
import static AsystentZakupow.Zarzadzanie.DodajProduktDoTabeli.dodajProduktDoTabeli;
import static AsystentZakupow.Zarzadzanie.Kategorie.kategorie;
import static AsystentZakupow.Zarzadzanie.SprawdzCzyProduktJestJuzWTabeli.sprawdzCzyProduktJestJuzWTabeli;

public class PodajNazweProduktuIKategorieAbyDodac {

    public  PodajNazweProduktuIKategorieAbyDodac(String nazwaProduktu, String kategoria){
        try {
            if (sprawdzCzyProduktJestJuzWTabeli("produkty", nazwaProduktu)) {
                System.out.println("produkt jest juz w bazie!");
                JOptionPane.showMessageDialog(frame,"Produkt jest juz w bazie");
            } else {
                dodajProduktDoTabeli("produkty",nazwaProduktu, kategoria);
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


}
