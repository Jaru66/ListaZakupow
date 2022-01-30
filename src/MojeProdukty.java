import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.sql.DriverManager;
import java.util.ArrayList;
public class MojeProdukty {



    static Statement statement= null;
    static Connection connection = null;
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static String b = null;
    public static ArrayList idProduktuNaLiscie = new ArrayList();
    public static ArrayList idProduktuWGlownejBazie = new ArrayList();



    public static Connection connect() throws IOException, SQLException {
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost/asystent_zakupow";
            String username = "user";
            String password = "password";
            Class.forName(driver);
            try {connection = DriverManager.getConnection(url,username,password);
            } catch (SQLException e){e.printStackTrace();}
            catch (Exception e) {e.printStackTrace();}
        } catch (ClassNotFoundException e) {e.printStackTrace();}
        return null;
    }

    public static Connection dodajProduktyDoMojejListy() throws  IOException, SQLException {
        while(true) {
            wyswietlWszystkieProduktyZBazy();
            try {
                String b = reader.readLine();
                if (b.contains("q")) {
                    Menu.menu();
                }
                int temp = Integer.parseInt(b);

                try{
                String sql= "SELECT 'Nazwa Produktu' FROM produkty where ID="+temp+";";
                statement.executeQuery(sql);



                    String nazwaProduktu = rs.getString("Nazwa Produktu");
                    String sql1 = "INSERT INTO `moje_produkty` (`ID`, `Nazwa Produktu`) VALUES (NULL, '" + nazwaProduktu + "');";
                    statement.executeUpdate(sql1);
                }catch (SQLException e){e.printStackTrace(); System.out.println("Nie znaleziono takiego produktu w bazie");dodajProduktyDoMojejListy();};






            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("__Cos poszlo nie tak__");
            }
        }
    }

    public static Connection wyswietlWszystkieProduktyZBazy() throws IOException, SQLException {
        sprawdzCzyIdSieDubluje();
        connect();
        System.out.println("--------------------------------------------------");
                statement = connection.createStatement();
                String sql= "SELECT * FROM produkty;";

                ResultSet rs = statement.executeQuery(sql);
                while(rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("Nazwa Produktu");
                    System.out.println(id+" "+ name);
                }
        System.out.println("--------------------------------------------------");
        return null;
    }
    public static Connection wyswietlWszystkieProduktyZListy() throws IOException, SQLException {
       // sprawdzCzyIdSieDubluje();
        connect();
        System.out.println("--------------------------------------------------");
        statement = connection.createStatement();
        String sql= "SELECT * FROM moje_produkty;";

        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("Nazwa Produktu");
            System.out.println(id+" "+ name);
        }
        System.out.println("--------------------------------------------------");
        return null;
    }

    public static Connection wyswietlProduktyNaMojejLiscie()throws IOException, SQLException{
        /////////////////////////////////pobranie Numeru produktu powiazanego z glowna baza z mojej listy oraz pobranie id produktu z mojej listy
        try{
            connect();
            System.out.println("--------------------------------------------------");
            statement = connection.createStatement();
            String sql= "SELECT * FROM moje_produkty;"; //zapytanie do tabeli z moja lista produktow
            ResultSet rs = statement.executeQuery(sql);

            idProduktuWGlownejBazie.clear();
            idProduktuNaLiscie.clear(); //czyszczenie listy ID przed petla, zeby nie zdublowac produktow
            while(rs.next()) {              //wczytanie wszystkich wierszy z tabeli moje produkty
                int numerProduktu = rs.getInt("Numer Produktu");    //Numer produktu z glownej bazy zapisany w tabeli z moimi produktami
                int idProduktu = rs.getInt("ID");       // id produktu z tabeli z moimi produktami

                idProduktuWGlownejBazie.add(numerProduktu);            //dodanie do listy array numeru produktu nalezacego do glownej bazy
                idProduktuNaLiscie.add(idProduktu);         //id produktu z mojej listy

            }

            int wielkoscListy = idProduktuNaLiscie.size();  //Wielkosc listy z numerem produktu
            int id=0;
            int i=0;

            for(i=0;i<wielkoscListy;i++){
                id= (int) idProduktuWGlownejBazie.get(i);
                String sql1= "SELECT * FROM produkty WHERE ID ="+id+"";
                ResultSet rs1 = statement.executeQuery(sql1);
                rs1.next();
                String nazwaProduktu = rs1.getString("Nazwa Produktu");
                System.out.println(idProduktuNaLiscie.get(i)+". "+ nazwaProduktu);
            }

            connection.close();
            System.out.println("--------------------------------------------------");
            Menu.menu();
            return null;
        }catch (SQLException e){e.printStackTrace();}
        return  null;
    }

    public static Connection usunProduktyZMojejListy()throws IOException, SQLException{
        while(true) {

            try {
                System.out.println("--------------------------------------------------");
                connect();
                statement = connection.createStatement();
                String sql = "SELECT * FROM moje_produkty;"; //zapytanie do tabeli z moja lista produktow
                ResultSet rs = statement.executeQuery(sql);

                idProduktuWGlownejBazie.clear();
                idProduktuNaLiscie.clear(); //czyszczenie listy ID przed petla, zeby nie zdublowac produktow
                while (rs.next()) {              //wczytanie wszystkich wierszy z tabeli moje produkty
                    int numerProduktu = rs.getInt("Numer Produktu");    //Numer produktu z glownej bazy zapisany w tabeli z moimi produktami
                    int idProduktu = rs.getInt("ID");       // id produktu z tabeli z moimi produktami

                    idProduktuWGlownejBazie.add(numerProduktu);            //dodanie do listy array numeru produktu nalezacego do glownej bazy
                    idProduktuNaLiscie.add(idProduktu);         //id produktu z mojej listy

                }

                int wielkoscListy = idProduktuNaLiscie.size();  //Wielkosc listy z numerem produktu
                int id = 0;
                int i = 0;

                for (i = 0; i < wielkoscListy; i++) {
                    id = (int) idProduktuWGlownejBazie.get(i);
                    String sql1 = "SELECT * FROM produkty WHERE ID =" + id + "";
                    ResultSet rs1 = statement.executeQuery(sql1);
                    rs1.next();
                    String nazwaProduktu = rs1.getString("Nazwa Produktu");
                    System.out.println(idProduktuNaLiscie.get(i) + ". " + nazwaProduktu);
                }

                System.out.println("--------------------------------------------------");

                connection.close();


                connect();
                String b = reader.readLine();
                if (b.contains("q")) {
                    Menu.menu();
                }
                int temp = Integer.parseInt(b);
                String sql1 = "DELETE from moje_produkty WHERE ID=" + temp + ";";
                statement.executeUpdate(sql1);
                System.out.println("Usunieto");
            }catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("__Cos poszlo nie tak__");
            }
            connection.close();
        }
    }

        public static void sprawdzCzyIdSieDubluje()throws IOException, SQLException{
        try {


            connect();
            System.out.println("--------------------------------------------------");
            statement = connection.createStatement();
            String sql = "SELECT * FROM produkty;";
            int najnizszeRealneId = -1;
            int id = 0;
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()){
                statement = connection.createStatement();
                if (najnizszeRealneId < 0) {
                    najnizszeRealneId = rs.getInt("id");
                    najnizszeRealneId = najnizszeRealneId - 1;
                }
                id = rs.getInt("id");
                String name = rs.getString("Nazwa Produktu");
                if (najnizszeRealneId + 1 == id) {
                    najnizszeRealneId++;
                    //System.out.println("True" + najnizszeRealneId + " " + id);
                } else {
                    najnizszeRealneId++;
                    //System.out.println("False" + id + " " + najnizszeRealneId);

                    //System.out.println("INSERT INTO `produkty` (`ID`, `Nazwa Produktu`) VALUES ('" + najnizszeRealneId + "', '" + name + "');");
                    String sql1 = "INSERT INTO `produkty` (`ID`, `Nazwa Produktu`) VALUES ('" + najnizszeRealneId + "', '" + name + "');";
                    statement.executeUpdate(sql1);
                    //System.out.println("przed usunieciem");
                    String sql2 = "DELETE FROM `produkty` WHERE `produkty`.`ID` = "+id+";";
                    statement.executeUpdate(sql2);
                    //System.out.println("po usunieciem");

                }
            }

            System.out.println("--------------------------------------------------");
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("__Cos poszlo nie tak__");
        }
            statement.close();
            connection.close();
            System.out.println("__POSORTOWANE__");
        //Menu.menu();
        }

    public static Connection dodajProduktDoGlownejBazy() throws IOException, SQLException{

        try {
            System.out.println("Wpisz nazwe produktu jaki chcesz dodac (wszystkie litery male, slowa oddzielane spacja) lub wpisz q aby wyjsc do menu");
            String b = reader.readLine();
            if (b.contains("q")) {
                Menu.menu();
            }

            try{
                if(sprawdzCzyProduktJestJuzWBazie(b)==true){
                    System.out.println("produkt jest juz w bazie!");
                    dodajProduktDoGlownejBazy();
                }else{
                    String sql= "INSERT INTO `produkty` (`ID`, `Nazwa Produktu`, `Miejsce W Sklepie`) VALUES (NULL, '"+ b +"', '0');";
                    statement.executeUpdate(sql);
                    System.out.println("Dodano "+b+" do bazy danych produktow.");
                }
            }catch (SQLException e){e.printStackTrace();};
        } catch (Exception e) {
            System.out.println("__Cos poszlo nie tak__");
        }
        dodajProduktDoGlownejBazy();
        return null;
    }

    public static void usunProduktZGlownejBazy() throws IOException, SQLException{

        try{
            wyswietlWszystkieProduktyZBazy();
            //connect();
            System.out.println("Wpisz numer ID produktu do usuniecia lub wpisz q aby wrocic do menu");
            String b = reader.readLine();
            if (b.contains("q")) {
                Menu.menu();
            }
            int temp = Integer.parseInt(b);
            try{String sql="DELETE from produkty WHERE ID=" + temp + ";";
            statement.executeUpdate(sql);}catch (SQLException e) {e.printStackTrace();}
            System.out.println("usunieto");
            usunProduktZGlownejBazy();
        }catch(SQLException e){e.printStackTrace();}
    }

    public static boolean sprawdzCzyProduktJestJuzWBazie(String name) throws IOException, SQLException{
        connect();
        statement = connection.createStatement();
        String sql= "SELECT * FROM produkty;";

        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()) {
            if (name.contains(rs.getString("Nazwa Produktu")))
                {
                    connection.close();statement.close();
                return true;
                }
        }
        return false;
    }
}