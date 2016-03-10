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
import java.util.Collections;
import java.util.*;

public class Deck
{
    ArrayList<Card> Deck;
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
    //----------------------------------------------------
    // Returns the number of cards in this deck.
    //----------------------------------------------------
    public int getNumCards()
    {
        return Deck.size();
    }
    //----------------------------------------------------
    // Adds a given card at the top of this deck.
    //----------------------------------------------------
    public void add(Card card)
    {
        Deck.add(card);
    }
    //----------------------------------------------------
    // Indicates whether this deck is empty.
    //----------------------------------------------------
    public boolean isEmpty()
    {
        if (Deck.size() == 0)
            return true;
        else
            return false;
    }
    //----------------------------------------------------
    // Removes and returns the top card from this deck.
    //----------------------------------------------------
    public Card takeTop()
    {
        //Note: The last element of the array is considered to be the top of the deck
        if (!isEmpty())
            return (Deck.remove(Deck.size()-1));
        else
            return null;
    }
    //----------------------------------------------------
    // Shuffles this deck.
    //----------------------------------------------------
    public void shuffle()
    {
        Collections.shuffle(Deck);
    }
    //----------------------------------------------------
    // Sorts this deck in acsending order of IDs.
    //----------------------------------------------------
    public void sort()
    {
        Collections.sort(Deck);
    }
    //----------------------------------------------------
    // Returns a string representation of this deck.
    //----------------------------------------------------
    public String toString()
    {
        String s = "";
        for (int i = 0; i<Deck.size(); i++)
            {
                s+=Deck.get(i) + " ";
            }
        return s;
    }
}
