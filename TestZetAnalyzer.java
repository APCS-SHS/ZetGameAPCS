public class TestZetAnalyzer
{
    
    
    public static void main (String [] args)
    {
        ZetDeck z1 = new ZetDeck();
        int length = z1.length() - 1;
        for (int i = 0; i < length; i++)
        {
            System.out.println(z1[i]);
        }
        
        if (z1.findZet(z1) != null)
        {

            System.out.println(findZet(z1));
      
    
        
        }
    }
}
