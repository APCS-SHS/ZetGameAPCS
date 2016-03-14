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
        int [] arrayZ1 = new int [81];
        for (int i = 0; i < 81; i++)
        {
            arrayZ1[i] = z1.get(i);
        }
        if (ZetAnalyzer.findZet(arrayZ1) != null)
        {

            System.out.println(findZet(z1));
      
    
        
        }
    }
}
