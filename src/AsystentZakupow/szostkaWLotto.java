package AsystentZakupow;

import java.util.Random;
import java.util.random.*;

public class szostkaWLotto {
    static int a1,a2,a3,a4,a5,a6;
    static int liczbaLosowan=0;
    static int liczbaSzostek=0;
    static int liczbaPiatek=0;
    static int liczbaCzworek=0;
    static int liczbaTrojek=0;
    static int liczbaDwojek=0;
    static int liczbaJedynek=0;
    static int wagaTrafionej=0;
    static int max = 49;
    static int min = 1;
    static int range = max - min + 1;
    static boolean powtarzajaSie;
    static boolean contin;
    static boolean liczbySiePowtarzaja = true;
    static int[]losowe = new int[6];
    static int[]wczytane = new int[6];
    public static void losuj(){
        int max = 49;
        int min = 1;
        int range = max - min + 1;

        a1= (int)(Math.random() * range) + min;
        a2= (int)(Math.random() * range) + min;
        a3= (int)(Math.random() * range) + min;
        a4= (int)(Math.random() * range) + min;
        a5= (int)(Math.random() * range) + min;
        a6= (int)(Math.random() * range) + min;

    }

    public static void licz(int a, int b, int c, int d, int e, int f)
    {
        wczytane[0]=a;
        wczytane[1]=b;
        wczytane[2]=c;
        wczytane[3]=d;
        wczytane[4]=e;
        wczytane[5]=f;



        do {

            //czyszxczenie ablicy
            for(int i=0;i<=5;i++){losowe[i]=(int)(Math.random() * range) + min;}

            int wygenerowana =(int) (Math.random() * range) + min;

//            for(int i=0;i<=5;i++){
//                wygenerowana =(int) (Math.random() * range) + min;
//                for(int u=0;u<=i;u++){
//
//                    if(wygenerowana==losowe[u])
//                    {
//                        System.out.println("wygenerowana :"+wygenerowana +" = losowe " +u + " "+ losowe[u]);
//                        wygenerowana=(int) (Math.random() * range) + min;
//                        System.out.println("nowa losowa "+ wygenerowana);
//                        System.out.println();
//                        //u=0;
//                    }else losowe[i]=wygenerowana;
//
//                }
//
//            }
            do {
                liczbySiePowtarzaja=false;
                if (losowe[1] == losowe[0]) {
                    liczbySiePowtarzaja = true;
                    losowe[1] =(int)(Math.random() * range) + min;
                } else if (losowe[2]==losowe[1]||losowe[2]==losowe[0]){
                    liczbySiePowtarzaja = true;
                    losowe[2] =(int)(Math.random() * range) + min;
                } else if (losowe[3]==losowe[2]||losowe[3]==losowe[1]||losowe[3]==losowe[0]){
                    liczbySiePowtarzaja = true;
                    losowe[3] =(int)(Math.random() * range) + min;
                } else if (losowe[4]==losowe[3]||losowe[4]==losowe[2]||losowe[4]==losowe[1]||losowe[4]==losowe[0]){
                    liczbySiePowtarzaja = true;
                    losowe[4] =(int)(Math.random() * range) + min;
                } else if (losowe[5]==losowe[4]||losowe[5]==losowe[3]||losowe[5]==losowe[2]||losowe[5]==losowe[1]||losowe[5]==losowe[0]) {
                    liczbySiePowtarzaja = true;
                    losowe[5] = (int) (Math.random() * range) + min;
                }
//            else if (wczytane[6]==wczytane[5]||wczytane[6]==wczytane[4]||wczytane[6]==wczytane[3]||wczytane[6]==wczytane[2]||wczytane[6]==wczytane[1]||wczytane[6]==wczytane[0]) {
//                liczbySiePowtarzaja = true;
//                wczytane[6] = (int) (Math.random() * range) + min;
//            }
            }while (liczbySiePowtarzaja==true);
//            do{
//            for(int y=0;y<5;y++)
//            {
//                for(int u=1;u<=5;u++)
//                {
//                    if(losowe[u]==losowe[y])
//                    {
//                        losowe[u]=(int) (Math.random() * range) + min;
//                        powtarzajaSie=true;
//                    }else powtarzajaSie=false;
//
//                }
//            }
//            }while(!powtarzajaSie);

//sortowanie
            for(int s=0;s<=5;s++){

                for(int j=5;j>=1;j--)
                {
                    if (losowe[j]<losowe[j-1]){
                        int tymczasowoNajmniejsza = losowe[j-1];
                        losowe[j-1]=losowe[j];
                        losowe[j]=tymczasowoNajmniejsza;
                    }
                }

            }

            for(int y=0;y<=5;y++)
            {
                for(int u=0;u<=5;u++)
                {
                    if(losowe[u]==wczytane[y])
                    {
                        wagaTrafionej++;
                    }

                }
            }
            System.out.println();
            System.out.println(losowe[0] + " " +losowe[1] + " " + losowe[2] + " " + losowe[3] + " " + losowe[4] + " " + losowe[5] + " ");
            System.out.println(wczytane[0] + " " +wczytane[1] + " " + wczytane[2] + " " + wczytane[3] + " " + wczytane[4] + " " + wczytane[5] + " ");
            liczbaLosowan++;
            System.out.println("LOSOWANIE: "+liczbaLosowan);
            System.out.println("trafiles: "+wagaTrafionej);
            System.out.println("liczba jedynek: "+liczbaJedynek);
            System.out.println("liczba dwojek: "+liczbaDwojek);
            System.out.println("liczba trojek: "+liczbaTrojek);
            System.out.println("liczba czworek: "+liczbaCzworek);
            System.out.println("liczba piatek: "+liczbaPiatek);
            System.out.println("liczba szostek: "+liczbaSzostek);

            switch (wagaTrafionej)
            {
                case 1: liczbaJedynek++; break;
                case 2: liczbaDwojek++; break;
                case 3: liczbaTrojek++; break;
                case 4: liczbaCzworek++; break;
                case 5: liczbaPiatek++; break;
                case 6: liczbaSzostek++; contin=true; break;
            }

//            if (a == losowe[0] && b == losowe[1] && c == losowe[2] && d == losowe[3] && e == losowe[4] && f == losowe[5]) {
//                System.out.println(losowe[0] + " " +losowe[1] + " " + losowe[2] + " " + losowe[3] + " " + losowe[4] + " " + losowe[5] + " ");
//                System.out.println(+a + " " + b + " " + c + " " + d + " " + e + " " + f + " ");
//                System.out.println("Liczba losowan: " + liczbaLosowan);
//                contin = true;
//            }
//
//            else {
//                liczbaLosowan++;
//                System.out.println(losowe[0] + " " +losowe[1] + " " + losowe[2] + " " + losowe[3] + " " + losowe[4] + " " + losowe[5] + " ");
//                System.out.println(+a+" "+b+" "+c+" "+d+" "+e+" "+f+" ");
//                System.out.println("Liczba losowan: "+liczbaLosowan);
//                System.out.println();
//            }

            wagaTrafionej=0;
        }while (!contin) ;

    }

}
