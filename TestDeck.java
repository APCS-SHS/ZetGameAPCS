
/**
 * Write a description of class TestDeck here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestDeck
{
    public static void main (String[] args)
    {
        Deck deck1 = new Deck();
        
        Card green1 = new Card(1);
        Card red2 = new Card(2);
        Card blue3 = new Card(3);
        
        deck1.add(green1);
        deck1.add(red2);
        deck1.add(blue3);
        
        
        System.out.println(deck1);
        deck1.shuffle();
        System.out.println(deck1);
        deck1.sort();
        System.out.println(deck1);
        
        for (int i = deck1.getNumCards()-1; i >= 0 ; i--)
        {
            
            System.out.println(deck1.takeTop());
            
            //System.out.println(deck1.takeTop());
            //System.out.print("i= "+i+"\t deck1.getNumCards()= "+deck1.getNumCards()+"\n");
        }
    }
}
