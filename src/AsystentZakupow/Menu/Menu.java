package AsystentZakupow.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import AsystentZakupow.Zarzadzanie.EksportujListeZakupowDoTxt;
import AsystentZakupow.Zarzadzanie.Kategorie;
import AsystentZakupow.Zarzadzanie.MojeProdukty;

import static AsystentZakupow.KreatorPodstawowejListyZakupow.DodajDoPodstawowejListy.dodajDoPodstawowejListy;
import static AsystentZakupow.KreatorPodstawowejListyZakupow.KreatorPodstawowejListy.kreatorPodstawowejListy;
import static AsystentZakupow.Zarzadzanie.DodajProduktDoGlownejBazy.dodajProduktDoGlownejBazy;
import static AsystentZakupow.Zarzadzanie.DodajProduktyDoMojejListy.dodajProduktyDoMojejListy;
import static AsystentZakupow.Zarzadzanie.SortowanieIDWBazie.posortujIdWBazie;
import static AsystentZakupow.Zarzadzanie.UsunProduktZTabeli.usunProduktZTablicy;
import static AsystentZakupow.Zarzadzanie.WyczyscListeZakupow.wyczyscListeZakupow;
import static AsystentZakupow.Zarzadzanie.WyswietlWszystkieProduktyZTablicy.wyswietlWszystkieProduktyZTablicy;
import static AsystentZakupow.Zarzadzanie.ZmienKategorieWGlownejLiscie.zmienKategorieWGlownejLiscie;

public class Menu {
    //public static void main(String[] args) throws IOException {}
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void menu() throws IOException, SQLException {
        Kategorie.kategorie();
        posortujIdWBazie("produkty");
        posortujIdWBazie("moje_produkty");
        MojeProdukty.zrobOdstep(15);

        System.out.println("Asystent tworzenia listy zakupów.");
        System.out.println();
        System.out.println("1. Dodaj produkty do glownej listy produktow");
        System.out.println("2. Usun produkty z glownej listy produktow");
        System.out.println();
        System.out.println("3. Dodaj produkty do swojej listy zakupow");
        System.out.println("4. Usun produkty ze swojej listy zakupow");
        System.out.println("5. Wyswietl produkty na twojej liscie zakupow");
        System.out.println("6. Wyczysc liste zakupow");
        System.out.println();
        System.out.println("7. Odpal kreator podstawowej listy");
        System.out.println("8. dodaj produkty do podstawowej listy");
        System.out.println("9. usun produkty do podstawowej listy");
        System.out.println("10. Zmien kategorie w glownej liscie produktow");
        System.out.println("11. Eksportuj liste zakupow do pliku tekstowego");


        // BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String b= reader.readLine();                                                //wczytanie z klawiatury zmiennej b

        int temp=Integer.parseInt(b);                                               //konwersja tekstu b do int o nazwie temp
        wybor(temp);                                                    //Klasa z metoda przechowywania produktow w listach bazy chyba beda lepsze

    }

    public static void wybor ( int wybrana) throws IOException, SQLException {

        switch (wybrana) {
            case 1:
                dodajProduktDoGlownejBazy();
                break;
            case 2:
                usunProduktZTablicy("produkty");
                break;
            case 3:
                dodajProduktyDoMojejListy();
                break;
            case 4:
                usunProduktZTablicy("moje_produkty");
                break;
            case 5:
                MojeProdukty.zrobOdstep(15);
                wyswietlWszystkieProduktyZTablicy("moje_produkty");
                break;
            case 6:
                wyczyscListeZakupow();
                break;
           case 7:
                kreatorPodstawowejListy();
                break;
           case 8:
                dodajDoPodstawowejListy();
                break;
           case 9:
                usunProduktZTablicy("produkty_podstawowe");
                break;
           case 10:
                zmienKategorieWGlownejLiscie();
                break;
           case 11:
                //eksportujListeZakupowDoTxt();
               EksportujListeZakupowDoTxt.eksportujTabeleDoTxt("moje_produkty");
                break;

            default:
                Menu.menu();
        }
        Menu.menu();
    }
}
