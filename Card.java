
/**
 * Write a description of class Card here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Card implements Comparable <Card>
{
    private int id;
    private int comparePriority;
    public Card (int iD)
    {
        id=iD;
    }
    public int compareTo(Card other)
    {
        
        if (other.getId()>this.getId())
        {
            comparePriority=-1;
        }
        if (other.getId()<this.getId())
        {
            comparePriority=1;
        }
        if (other.getId()==this.getId())
        {
            comparePriority=0;
        }
        return comparePriority;
    }
    public boolean equals(Card other)
    {
        if (getId()==other.getId())
        {
            return true;
        }
        else
            return false;
    }
    public int getId()
    {
        return id;
    }
    public String toString()
    {
        return ("This card has ID#: " + getId());
    }
    
}
