
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

    {
        ArrayList al=new ArrayList();
        
        for (int i=0;i<=80; i++)
        {
            Card zetCard=new Card(i);
            al.add(zetCard);
        }
        
    }
}


