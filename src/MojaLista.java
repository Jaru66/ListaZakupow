import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MojaLista {
    private static int liczbaRealnychProduktowWTablicy;

    static String[] wszystkieProdukty = new String[]{"woda","jajka","ketchup","cukier","kawa","szynka","ser","maslo","folia aluminiowa","sol","pieprz","maggi","piwo","herbata","oliwki","pomidory","makaron","smietana","czosnek","mleko","mieta","ogorki","ser zolty","mozarella","almette","mleko czekoladowe","koncentrat pomidorowy","maka pszenna","cukier bialy","cukier waniliowy","soda oczyszczona","przyprawa gyros","majonez"};

    static String[] mojeProdukty = new String[100];


    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    //public static void CzytajZBazy(){        ResultSet result = statement.executequery    }

    //public static void main(String[] args) {    }
        public static void usunProduktZMojejListy () throws IOException {
            System.out.println("Podaj numer produktu do usuniecia:");
            System.out.println("Wpisz menu aby wyjsc");
            String b = reader.readLine();//wczytanie z klawiatury zmiennej b
            if (b.contains("menu")) {
                try {
                    Menu.menu();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            int temp = Integer.parseInt(b);                                               //konwersja tekstu b do int o nazwie temp
            System.out.println("Produkt:" + mojeProdukty[temp] + " zostal usuniety");
            sprawdzIleJestProduktow();

            mojeProdukty[temp] = mojeProdukty[99];                //nie moglem usunac elementu, wiec ustawilem go na taki sam jak ostatni element czyli null
            sprawdzPusteMiejscaWLiscie();
        }

        public static void sprawdzPusteMiejscaWLiscie () throws IOException {
            int ostatniprodukt = 0;
            int pierwszynull = 99;
            for (int i = 99; i >= ostatniprodukt; i--) {
                System.out.println(i);
                if (mojeProdukty[i] == null) {                 //sprawdzenie tablicy zaczynajac od konca w poszukiwaniu produktu
                } else {
                    ostatniprodukt = i;
                    break;
                }         //wpisanie numeru tablicy ostatniego produktu
            }
            for (int a = 0; a <= pierwszynull; a++) {
                if (mojeProdukty[a] == null) {
                    mojeProdukty[a] = mojeProdukty[ostatniprodukt];
                    mojeProdukty[ostatniprodukt] = mojeProdukty[99];
                    break;
                }  //sprawdzenie tablicy w poszukiwaniu pierwszego nulla, podstawienie w jego miejsca ostatniego produktu, i usuniecie ostatniego produktu przez podstawwienie w jego miejsce ostatniej wartosci tablkicy
            }

        }



        public static void mojaLista () throws IOException {
            System.out.println("Lista moich produktow:");
            sprawdzIleJestProduktow();                                  //funkcja sprawdzajaca czy sa produkty w tablicy
            if (liczbaRealnychProduktowWTablicy == 0) {
                System.out.println("Na twojej liscie nie ma produktow. Dodaj produkty do listy");
                try {
                    Menu.menu();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }   //Sprawdzenie czy sa jakies produkty

            for (int i = 0; i <= mojeProdukty.length - 1; i++) {               //petla wyswietlajaca tablice z moimi produktami
                if (mojeProdukty[i] == null) {
                    break;
                }                       //przerywa petle od pierwszego nulla
                else System.out.println(i + ". " + mojeProdukty[i]);    //wyswietla liste produktow do pierwszego nulla
            }


            try {
                Menu.menu();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public static void sprawdzIleJestProduktow () throws IOException {
            liczbaRealnychProduktowWTablicy = 0;
            for (int i = 0; i <= mojeProdukty.length - 1; i++) {
                if (mojeProdukty[i] == null) {
                    break;
                } else liczbaRealnychProduktowWTablicy++;
            }

        }

        public static void produkty () throws IOException {
            //tutaj mamy tablice ze wszystkimi produktami dostepnymi w sklepie

            System.out.println("Lista wszystkich produktow");
            for (int i = 0; i <= wszystkieProdukty.length - 1; i++) {
                System.out.println(i + ". " + wszystkieProdukty[i]);
            }

            System.out.println("Wpisz menu aby cofnac");
            System.out.println("Wpisz numer produktu aby dodac go do listy");
            //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String b = reader.readLine();                                    //wczytanie z klawiatury zmiennej b
            String menu = "menu";                                            //po wpisaniu menu cofnie do menu glownego
            if (b.contains(menu)) {                       //sprawdzeni co ma zrobic w podmenu Lista Wszystkich Produktow
                try {
                    Menu.menu();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                int temp = Integer.parseInt(b);                           //konwersja tekstu b do int o nazwie temp
                System.out.println("dodano " + wszystkieProdukty[temp] + " do twojej listy zakupow. Wybierz kolejny produkt lub wpisz menu aby cofnac.");
                sprawdzIleJestProduktow();
                mojeProdukty[liczbaRealnychProduktowWTablicy] = wszystkieProdukty[temp];
                produkty();
            }
            try {
                Menu.menu();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
