
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
    public TestZetDeck()
    {
        // initialise instance variables
        
        ZetDeck deck1=new ZetDeck();
        int i = 0;
        while (i<=2)

        {
            System.out.println(deck1.takeTop());
            
            i++;
        }
        
    }

    
}
