import java.util.List;
import java.util.Collections;
import java.util.*;
public class TestZetAnalyzer
{
    
     //ZetDeck.getNumCards();
    public static void main (String [] args)
    {
        ZetDeck z1 = new ZetDeck();
        int len = 81;
        //for (int i = 0; i < length; i++)
        //{
            //System.out.println(z1[i]);
        //}
        ZetCard [] arrayZ1 = new ZetCard[81];
        for (int i = 0; i < 81; i++)
        {
            arrayZ1[i] = z1.takeTop();
        }
        if (ZetAnalyzer.findZet(arrayZ1) != null)
        {

            int[] x=ZetAnalyzer.findZet(arrayZ1);
            System.out.println(x[0]+"\t"+x[1]+"\t"+x[2]);
            System.out.println(ZetAnalyzer.isZet(arrayZ1[3],arrayZ1[6],arrayZ1[0]));
        
        }
    }
}
