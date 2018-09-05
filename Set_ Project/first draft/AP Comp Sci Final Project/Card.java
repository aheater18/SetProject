import javax.swing.ImageIcon;
import javax.swing.Icon;
import java.awt.*;

public class Card{
    
    ImageIcon theFace;
    
    private SHAPE shape;
    private SHADING shading;
    private Color color;
    private int num;
    
    public Card(SHAPE s, SHADING shade, Color c, int n){
        shape = s;
        shading = shade;
        color = c;
        num = n;
    }
    
    public SHAPE getShape(){
        return shape;
    }
    
    public SHADING getShading(){
        return shading;
    }
    
    public Color getColor(){
        return color;
    }
    
    public int getNumber(){
        return num;
    }
    
    public void drawComponent(Graphics g){
        
    }
    
    public boolean equals(Card otherCard){
        if(otherCard.color == this.color &&
           otherCard.shape == this.shape &&
           otherCard.shading ==this.shading &&
           otherCard.num == this.num)
            
            return true;
        return false;
    }
    
    public int compareTo(Card otherCard){
        return 0;
    }
    
    public String toString(){
        return shape + " " + color + " " + shading + " " + num;
    }
}
