/**
 *  Provides static methods for finding sets.
 */

/*

  Tips:

  - Given a, b, c from {1, 2, 3}, they are all equal or all different if and only if
    (a + b + c) % 3 == 0.  This property can be used for testing three cards for a set
    easily.

    (For more mathematically inclined, the SET deck corresponds to the 4-dimensional vector
    space over the algebraic field {0, 1, 2} with modulo 3 arithmetic.  Three cards form a "set"
    if and only if the sum of the corresponding vectors is 0.)

  - isZet and findZet are static methods.

  - in isZet, don't forget to check that the cards are not all the same.

  - Note that an array passed to findZet can have nulls in some of the elements.

*/

public class ZetAnalyzer
{
    private static int a;
    private static int b;
    private static int c;
    private static int isTrueN = 0, isTrueS = 0, isTrueF = 0, isTrueC = 0;
    
    
    public static boolean isZet(ZetCard card1, ZetCard card2, ZetCard card3)
    {
        a = card1.getNumber();
        b = card2.getNumber();
        c = card3.getNumber();
        
        if ((a+b+c)%3 == 0) 
        {
            isTrueN = 1;
            
            
            
        }
        a = card1.getShape();
        b = card2.getShape();
        c = card3.getShape();
        if ((a+b+c)%3 == 0) 
        {
            isTrueS = 1;
            
            
            
        }
        a = card1.getFill();
        b = card2.getFill();
        c = card3.getFill();
        if ((a+b+c)%3 == 0) 
        {
            isTrueF = 1;
            
            
            
        }
        a = card1.getColor();
        b = card2.getColor();
        c = card3.getColor();
        if ((a+b+c)%3 == 0) 
        {
            isTrueC = 1;
            
            
            
        }
        
        if ((isTrueC == 1) && (isTrueF == 1) && (isTrueN == 1) && (isTrueS==1))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static int[] findZet(ZetCard[] cards)
    {
        int length = cards.length-1;
        for (int i =0; i <= length; i++)
        {
            for (int c =1; c <= length; c++)
        {
            for (int j =2; j <= length; j++)
        {
            if (isZet(cards[i], cards[j], cards[c]) == true)
            {
                int[] arrayZet = {i,j,c};
                //arrayZet[0] = card[i];
                //arrayZet[1] = card[j];
                //arrayZet[2] = card[c];
                return arrayZet;
                
                
            }
            
        }
        
    }

   }
   return null;
  }
}


