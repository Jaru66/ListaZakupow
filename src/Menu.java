import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Menu {
    //public static void main(String[] args) throws IOException {}
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void menu() throws IOException, SQLException {
        System.out.println();
        System.out.println("Asystent tworzenia listy zakupów.");
        System.out.println();
        System.out.println("1. Dodaj produkty do glownej listy produktow");
        System.out.println("2. Usun produkty z glownej listy produktow");
        System.out.println();
        System.out.println("3. Dodaj produkty do swojej listy zakupow");
        System.out.println("4. Wyswietl produkty na twojej liscie zakupow");
        System.out.println("5. Wyczysc liste zakupow");


        // BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String b= reader.readLine();                                                //wczytanie z klawiatury zmiennej b

        int temp=Integer.parseInt(b);                                               //konwersja tekstu b do int o nazwie temp
        wybor(temp);                                                    //Klasa z metoda przechowywania produktow w listach bazy chyba beda lepsze

    }

    public static void wybor ( int wybrana) throws IOException, SQLException {

        switch (wybrana) {
            case 1:
                //MojeProdukty.wyswietlWszystkieProduktyZBazy();
                //MojeProdukty.dodajProduktyDoMojejListy();
               //MojeProdukty.sprawdzCzyIdSieDubluje();
                MojeProdukty.dodajProduktDoGlownejBazy();
                break;
            case 2:
                //MojeProdukty.wyswietlWszystkieProduktyZListy();
                MojeProdukty.usunProduktZGlownejBazy();
                break;
            case 3:
                //MojeProdukty.usunProduktyZMojejListy();
                break;

            default:
                Menu.menu();
        }
    }
}
