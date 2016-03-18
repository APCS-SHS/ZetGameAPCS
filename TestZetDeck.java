
/**
 * Write a description of class TestZetDeck here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestZetDeck
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class TestZetDeck
     */
    public static void main(String [] args)
    {
        // initialise instance variables
        
        Deck deck1=new ZetDeck();
        System.out.println(deck1.getNumCards());
        int i = 0;
        while (i<=2)

        {
            System.out.println(deck1.takeTop());
            
            i++;
        }
        System.out.println(deck1);
        
    }

    
}
