
/**
 * Write a description of class ZetDeck here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class ZetDeck extends Deck
{
    private ArrayList<ZetCard> Zet=new ArrayList<ZetCard>();
    public ZetDeck() 
    
    {
        super(81);
        for (int i=1;i<=3; i++)
        {
            for (int Shape=1 ; Shape<=3; Shape++)
            {
                for (int Fill=1; Fill<=3; Fill++)
                {
                    for (int Color=1; Color<=3; Color++)
                    {
                        ZetCard zetCard=new ZetCard(i,Shape,Fill,Color);
                        this.add(zetCard);
                    }
                }
            }
        }
        
    }
    
}

