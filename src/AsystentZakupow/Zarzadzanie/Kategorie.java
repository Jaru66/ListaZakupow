package AsystentZakupow.Zarzadzanie;

import java.util.HashMap;

public class Kategorie {

    //wszystkie kategorie produktow znajdujacych sie w sklepie
    public static HashMap<Integer,String> kategorie = new HashMap<>();
    public static void kategorie() {
        kategorie.put(1, "chemia gospodarcza i kosmetyki");
        kategorie.put(2, "przyprawy");
        kategorie.put(3, "mieso i wedliny");
        kategorie.put(4, "nabial");
        kategorie.put(5, "produkty konserwowe");
        kategorie.put(6, "pieczywo");
        kategorie.put(7, "napoje");
        kategorie.put(8, "owowce i warzywa");
        kategorie.put(9, "przekaski");
        kategorie.put(10, "leki");
        kategorie.put(11, "sypkie");
        kategorie.put(12, "mrozonki");
        kategorie.put(13, "kremy, pasty i smarowidla");
        kategorie.put(14, "sosy");

    }
}
