/**
 *  Represents a generic deck of cards.
 */

/*

Tips:

- Use an ArrayList<Card> to hold the cards.
- Add and remove cards at the end of the list.
- Use Collections.shuffle and Collections.sort to shuffle and sort the deck,
or write your own methods.  If you write your own, use selection sort
to sort and a similar algorithm to shuffle.  Use Math.random().
- In the toString method, separate strings for individual cards with "\n".

 */
import java.util.List;
import java.util.*;

ArrayList<Card> Deck;

public class Deck
{
    //----------------------------------------------------
    // Constructs an empty deck of cards
    //----------------------------------------------------
    public Deck()
    {
        Deck = new ArrayList<Card>();
    }
    //----------------------------------------------------
    // Constructs an empty deck of cards of given capacity
    //----------------------------------------------------
    public Deck(int capacity)
    {
        Deck = new ArrayList<Card>(capacity);
    }
}
