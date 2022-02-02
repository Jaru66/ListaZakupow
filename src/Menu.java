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
        System.out.println("1. Dodaj produkty do swojej listy");
        System.out.println("2. Wyswietl produkty na twojej liscie");
        System.out.println("3. Usun produkt z mojej listy");

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
               MojeProdukty.sprawdzCzyIdSieDubluje();
                break;
            case 2:
                MojeProdukty.wyswietlWszystkieProduktyZListy();
                break;
            case 3:
                MojeProdukty.usunProduktyZMojejListy();
                break;

            default:
                Menu.menu();
        }
    }
}
