/**
 * Write a description of class ZetCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ZetCard extends Card
{
    private int number, shape, fill, color;
    private static int iD = 0;
    
    public ZetCard(int number, int shape, int fill, int color){
        super(iD);
        this.number = number;
        this.shape = shape;
        this.fill = fill;
        this.color = color;
        iD++;
    }
    
    public int getNumber(){
        return number;
    }
    
    public int getShape(){
        return shape;
    }
    
    public int getFill(){
        return fill;
    }
    
    public int getColor(){
        return color;
    }
    
    public String toString(){
        return("This card's ID is "+super.getId());
    }
}
