/**
 * Write a description of class ZetCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ZetCard extends Card
{
    private int number, shape, fill, color;
    
    
    public ZetCard(int number, int shape, int fill, int color){
        super((1000*(number)) + (100*(shape))+10*(fill)+(color));
        this.number = number;
        this.shape = shape;
        this.fill = fill;
        this.color = color;
        
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
