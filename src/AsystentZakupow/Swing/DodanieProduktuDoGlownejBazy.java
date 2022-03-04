package AsystentZakupow.Swing;

import javax.swing.*;

import AsystentZakupow.Zarzadzanie.Kategorie;
import AsystentZakupow.Zarzadzanie.Kategorie.*;
import AsystentZakupow.Zarzadzanie.WczytajProduktyZBazyODanejKategorii;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import static AsystentZakupow.Swing.OdswiezListeProduktow.odswiezListeProduktow;
import static AsystentZakupow.Zarzadzanie.Kategorie.kategorie;

public class DodanieProduktuDoGlownejBazy extends OknoDodajDoListy {
    JTextField nazwaProduktu = new JTextField("" , 15);
    JButton dodaj = new JButton("Dodaj do bazy");
    JComboBox<String> kategoria = new JComboBox<String>();
    JLabel nazwaProduktuLabel = new JLabel("Nazwa Produktu");
    JLabel kategoriaLabel = new JLabel("Wybierz kategorie");


    public DodanieProduktuDoGlownejBazy()
    {
        for(int i=1;i<=kategorie.size();i++){kategoria.addItem(kategorie.get(i));}

        //dodajDoBazy.setAlwaysOnTop(true);
        dodajDoBazy.setResizable(false);
        dodajDoBazy.setVisible(true);
        nazwaProduktuLabel.setVisible(true);
        kategoriaLabel.setVisible(true);
       // dodajDoBazy.setBounds();
        dodajDoBazy.add(nazwaProduktu);
        dodajDoBazy.add(dodaj);
        dodajDoBazy.add(kategoria);
        dodajDoBazy.add(nazwaProduktuLabel);
        dodajDoBazy.add(kategoriaLabel);
        nazwaProduktuLabel.setBounds(40,0,300,20);
        nazwaProduktu.setBounds(40,20,300,40);
        dodaj.setBounds(600,20,150,40);
        kategoriaLabel.setBounds(360,10,250,20);
        kategoria.setBounds(340,30,250,20);
        //DodajProduktDoBazy.setVisible(false);

        dodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new PodajNazweProduktuIKategorieAbyDodac(nazwaProduktu.getText(),kategoria.getSelectedItem().toString());
                odswiezListeProduktow();

            }
        });
    }
}
