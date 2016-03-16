
/**
 * Write a description of class ZetDeck here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class ZetDeck extends Deck
{
    public ZetDeck() 

    {ArrayList<ZetCard> al=new ArrayList<ZetCard>();

        
        
        for (int i=0;i<=2; i++)
        {
            for (int Shape=0 ; Shape<=2; Shape++)
            {
                for (int Fill=0; Fill<=2; Fill++)
                {
                    for (int Color=0; Color<=2; Color++)
                    {
                        ZetCard zetCard=new ZetCard(i,Shape,Fill,Color);
                        al.add(zetCard);
                    }
                }
            }
        }

    }
}

