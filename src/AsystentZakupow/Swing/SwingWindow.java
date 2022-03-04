package AsystentZakupow.Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingWindow  {

    static JFrame frame;
    JButton KreatorListy;
    JButton Wroc;
    JButton WyslijListeZakupowNaTelefon;
    JButton DodajDoListyZakupow;
    JButton ZarzadzanieGlownaBazaProduktow;
    JButton DodajProduktDoBazy;
    Dimension WielkoscOkna;
    JDialog dodajDoBazy;
    JComboBox<String> kategoriaProduktu;

    public SwingWindow() {
        
        kategoriaProduktu = new JComboBox<String>();
        frame = new JFrame();
        frame.setTitle("Asystemt listy zakupow");
        frame.setLayout(null);
        frame.setVisible(true);//making the frame visible
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);

        KreatorListy        = new JButton("Uruchom kreator szybkiej listy zakupow");
        DodajDoListyZakupow = new JButton("Dodaj produkty do listy zakupow");
        Wroc                = new JButton("Wroc");
        DodajProduktDoBazy  = new JButton("Dodaj produkt do glownej bazy");

        dodajDoBazy         = new JDialog(frame);


        WielkoscOkna = frame.getSize();
        KreatorListy.setBounds(WielkoscOkna.height * 1 / 100, 40, 300, 40);
        DodajDoListyZakupow.setBounds(WielkoscOkna.height * 1 / 100, 120, 300, 40);
        DodajProduktDoBazy.setBounds(40,960,300,40);
        dodajDoBazy.setSize(800,120);


        frame.add(KreatorListy);
        frame.add(DodajDoListyZakupow);

        DodajDoListyZakupow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                OknoDodajDoListy.main();
            }
        });
        DodajProduktDoBazy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new DodanieProduktuDoGlownejBazy();
            }
        });

    }
    public static void main(){
        new SwingWindow();
    }
}

