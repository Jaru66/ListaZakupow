package AsystentZakupow.Swing;

import AsystentZakupow.BazaDanych.PolaczenieZBaza;
import AsystentZakupow.Zarzadzanie.WyswietlWszystkieProduktyZTablicy;

import java.io.IOException;
import java.sql.SQLException;

import static AsystentZakupow.Swing.OknoDodajDoListy.listaProduktow;
import static AsystentZakupow.Swing.OknoDodajDoListy.listaZakupow;

public class OdswiezListeProduktow {
    public static void main(String args[]){}
    public static void odswiezListeZakupow(){
        try {listaZakupow.clear();
            PolaczenieZBaza.connect();
            //WyswietlWszystkieProduktyZTablicy.ZapiszProduktyZBazyNaLiscieProduktow.wyswietlWszystkieProduktyZTablicy("produkty");
            WyswietlWszystkieProduktyZTablicy.b="listaZakupo";
            WyswietlWszystkieProduktyZTablicy.ZapiszProduktyZBazyNaLiscieZakupow.wyswietlWszystkieProduktyZTablicy("moje_produkty");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }}
    public static void odswiezListeProduktow() {

        try {
            listaProduktow.clear();
            PolaczenieZBaza.connect();
            //WyswietlWszystkieProduktyZTablicy.ZapiszProduktyZBazyNaLiscieProduktow.wyswietlWszystkieProduktyZTablicy("produkty");
            WyswietlWszystkieProduktyZTablicy.b="listaProdukto";
            WyswietlWszystkieProduktyZTablicy.ZapiszProduktyZBazyNaLiscieProduktow.wyswietlWszystkieProduktyZTablicy("produkty");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }}


}
