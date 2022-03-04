package AsystentZakupow.Swing;

import AsystentZakupow.BazaDanych.PolaczenieZBaza;
import AsystentZakupow.Zarzadzanie.PrzeniesZTabeliProduktyDoListaZakupow;
import AsystentZakupow.Zarzadzanie.WczytajProduktyZBazyODanejKategorii;
import AsystentZakupow.Zarzadzanie.WyswietlWszystkieProduktyZTablicy;
import AsystentZakupow.Zarzadzanie.usunZTablicyOPodanejNazwie;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import static AsystentZakupow.Zarzadzanie.EksportujListeZakupowDoTxt.eksportujTabeleDoTxt;
import static AsystentZakupow.Zarzadzanie.Kategorie.kategorie;
import static AsystentZakupow.Swing.OdswiezListeProduktow.*;
import static AsystentZakupow.Zarzadzanie.WyczyscListeZakupow.wyczyscListeZakupowBezPytania;

public class OknoDodajDoListy extends SwingWindow {

    public static DefaultListModel<String> listaProduktow;
    public static DefaultListModel<String> listaZakupow;
    boolean produktJestWLiscie;

    public OknoDodajDoListy(){  //konstruktor okna dodawania do listy
    frame.remove(KreatorListy);
    frame.remove(DodajDoListyZakupow);
    Wroc.setBounds(WielkoscOkna.height*98/100,WielkoscOkna.width/2-100,100,40);
    frame.add(Wroc);
    frame.add(DodajProduktDoBazy);
        listaProduktow = new DefaultListModel<>();
        listaZakupow = new DefaultListModel<>();


    //WYSWIETL INFO O LISTACH Z BAZY DANYCH
        try {
            PolaczenieZBaza.connect();
            WyswietlWszystkieProduktyZTablicy.b="listaProdukto";
            //WyswietlWszystkieProduktyZTablicy.wyswietlWszystkieProduktyZTablicy("produkty");
            WyswietlWszystkieProduktyZTablicy.ZapiszProduktyZBazyNaLiscieProduktow.wyswietlWszystkieProduktyZTablicy("produkty");
            //WyswietlWszystkieProduktyZTablicy.ZapiszProduktyZBazyNaLiscieProduktow.wyswietlWszystkieProduktyZTablicy("produkty");
            WyswietlWszystkieProduktyZTablicy.b="listaZakupo";
            WyswietlWszystkieProduktyZTablicy.ZapiszProduktyZBazyNaLiscieZakupow.wyswietlWszystkieProduktyZTablicy("moje_produkty");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

        JList<String> listaZakupo = new JList<>(listaZakupow);
        JScrollPane scrollZakupy = new JScrollPane(listaZakupo);
        scrollZakupy.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollZakupy.setBounds(380,40,300,900);
        frame.add(scrollZakupy);

        JList<String> listaProdukto = new JList<>(listaProduktow);
        JScrollPane scrollProdukty = new JScrollPane(listaProdukto);
        scrollProdukty.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollProdukty.setBounds(40,80,300,860);
        frame.add(scrollProdukty);

    kategoriaProduktu.setBounds(40,40,200,20);
    for(int i=1;i<=kategorie.size();i++){kategoriaProduktu.addItem(kategorie.get(i));}
    frame.add(kategoriaProduktu);

    JLabel produkty = new JLabel("Produkty");
    produkty.setBounds(80,0,100,40);
    produkty.setFont(new Font("Serif",Font.PLAIN, 20));
    frame.add(produkty);

    JLabel zakupy = new JLabel("Twoja lista");
    zakupy.setBounds(380,10,100,40);
    zakupy.setFont(new Font("Serif",Font.PLAIN, 20));
    frame.add(zakupy);

    JButton eksportujListeDoTxt = new JButton("Eksportuj liste do pliku txt");
    eksportujListeDoTxt.setBounds(380,960,300,40);
    frame.add(eksportujListeDoTxt);

    JButton wyczyscListeZakupow = new JButton("Wyczysc liste zakupow");
    wyczyscListeZakupow.setBounds(1380,960,300,40);
    frame.add(wyczyscListeZakupow);


    JButton otworzGoogleKeep = new JButton("Otworz Google Keep w przegladarce");
    otworzGoogleKeep.setBounds(1380,660,300,40);
        frame.add(otworzGoogleKeep);
        otworzGoogleKeep.addActionListener(e -> {

                String url = "https://keep.google.com/";
            try {
                Desktop.getDesktop().browse(java.net.URI.create(url));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });



    wyczyscListeZakupow.addActionListener(e -> {
        try {
            wyczyscListeZakupowBezPytania();
            odswiezListeZakupow();
        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
        }
    });

    eksportujListeDoTxt.addActionListener(e -> {
        try {
            eksportujTabeleDoTxt("moje_produkty");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    });
    kategoriaProduktu.addActionListener(e -> new WczytajProduktyZBazyODanejKategorii("produkty", kategoriaProduktu.getSelectedItem().toString()));
    listaProdukto.addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if(e.getValueIsAdjusting())return;
            String name = listaProdukto.getSelectedValue();
            produktJestWLiscie=false;


            for(int a=0;a < listaZakupow.getSize();a++){
                if (listaZakupo.getModel().getElementAt(a).equals(listaProdukto.getSelectedValue())){
                    produktJestWLiscie=true;
                   }
            } if(!produktJestWLiscie) {
                PrzeniesZTabeliProduktyDoListaZakupow.Przenies(listaProdukto.getSelectedValue());
                odswiezListeZakupow();
            }

        }
    });
        listaZakupo.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {

                    String nazwaProduktu=listaZakupo.getSelectedValue();
                    listaZakupow.remove(listaZakupo.getSelectedIndex());
                    try {
                        usunZTablicyOPodanejNazwie.usunProduktZTablicy("moje_produkty",nazwaProduktu);
                    } catch (IOException | SQLException ex) {
                        ex.printStackTrace();
                    }
                    odswiezListeZakupow();
                }
            }});

   Wroc.addActionListener(new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {
           frame.setVisible(false);
           SwingWindow.main();
       }
   });
    DodajProduktDoBazy.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //pole tekstowe visible true
            //combobox wybor kategorii visible true
            //przycisk dodaj do bazy visible true

        }
    });
    }
    public static void wczytajProduktyZBazyDoListyProduktow(String nazwaProduktu){
        listaProduktow.addElement(nazwaProduktu);

    }
    public static void wczytajProduktyZBazyDoListyZakupow(String nazwaProduktu){
        listaZakupow.addElement(nazwaProduktu);
    }
    public static void main(){

        new OknoDodajDoListy();
    }
}
