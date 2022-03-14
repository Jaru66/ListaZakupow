package AsystentZakupow.Przepisy;

public class Przepis {
    public static void main(String args[]){
        //gramy zapisujemy g
        //ml zapisujmej ml
        //sztuki zapisujkemy szt
        //etc
        String kalafiorowa="kalafior,300g,ziemniaki,200g,maka,15g,smietana,200ml";

        String[] podzielone = kalafiorowa.split(",");

        for(int i=0;i<kalafiorowa.length();i++)
        {System.out.println(podzielone[i]+" "+podzielone[i+1]);

        i++
        ;}


    }

}
