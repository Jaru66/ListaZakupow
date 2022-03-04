package AsystentZakupow.Zarzadzanie;

import AsystentZakupow.Menu.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import static AsystentZakupow.Zarzadzanie.DodajProduktDoTabeli.dodajProduktDoTabeli;
import static AsystentZakupow.Zarzadzanie.Kategorie.kategorie;
import static AsystentZakupow.Zarzadzanie.MojeProdukty.zrobOdstep;
import static AsystentZakupow.Zarzadzanie.SprawdzCzyProduktJestJuzWTabeli.sprawdzCzyProduktJestJuzWTabeli;

public class DodajProduktDoGlownejBazy {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void dodajProduktDoGlownejBazy() throws IOException, SQLException {

        try {
            zrobOdstep(15);


            System.out.println("Wpisz nazwe produktu jaki chcesz dodac (wszystkie litery male, slowa oddzielane spacja) lub wpisz q aby wyjsc do menu");
            String b = reader.readLine();
            if (b.contains("q")) {
                Menu.menu();
            }

            try {
                if (sprawdzCzyProduktJestJuzWTabeli("produkty", b)) {
                    System.out.println("produkt jest juz w bazie!");
                    dodajProduktDoGlownejBazy();
                } else {
                    System.out.println("Wybierz kategorie produktu z listy: ");
                    for(int i =1;i<=kategorie.size();i++){System.out.println(i+". "+kategorie.get(i));}
                    String c = reader.readLine();
                    int wybranaWartosc = Integer.parseInt(c);
                    dodajProduktDoTabeli("produkty",b, kategorie.get(wybranaWartosc));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("__Cos poszlo nie tak__");
        }
        dodajProduktDoGlownejBazy();
    }
}
