import javax.swing.ImageIcon;
import javax.swing.Icon;
import java.awt.*;

public class Card{
    
    ImageIcon theFace;
    
    private SHAPE shape;
    private SHADING shading;
    private Color color;
    private Color lightColor;
    private int num;
    
    private boolean used;
    
    public Card(SHAPE s, SHADING shade, Color c, Color l, int n){
        shape = s;
        shading = shade;
        color = c;
        lightColor = l;
        num = n;
        used = false;
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
    
    public void use(){
        used = true;
    }
    public void unuse(){
        used = false;
    }
    
    public void drawComponent(Graphics g){
        
    }
    
    public boolean equals(Card secondCard, Card thirdCard){
        boolean shapeEqual = false;
        boolean shadingEqual = false;
        boolean colorEqual = false;
        boolean numEqual = false;
        if((this.shape == secondCard.shape && this.shape == thirdCard.shape) ||
           (this.shape != secondCard.shape && 
            secondCard.shape != thirdCard.shape && 
            this.shape != thirdCard.shape))
            shapeEqual = true;
        
        if((this.shading == secondCard.shading && this.shading == thirdCard.shading) ||
           (this.shading != secondCard.shading && 
            secondCard.shading != thirdCard.shading && 
            this.shading != thirdCard.shading))
            shadingEqual = true;
        
        if((this.color == secondCard.color && this.color == thirdCard.color) ||
           (this.color != secondCard.color && 
            secondCard.color != thirdCard.color && 
            this.color != thirdCard.color))
            colorEqual = true;
        
        if((this.num == secondCard.num && this.num == thirdCard.num) ||
           (this.num != secondCard.num && 
            secondCard.num != thirdCard.num && 
            this.num != thirdCard.num))
            numEqual = true;
        
        return shapeEqual && shadingEqual && colorEqual && numEqual;
    }
    
    public int compareTo(Card otherCard){
        return 0;
    }
    
    public String toString(){
        return shape + " " + color + " " + shading + " " + num;
    }
    
    public void draw(int x, int y, int n, Graphics g){
        g.setColor(Color.WHITE);
        g.fillRoundRect(x,y,90,60,5,5);
        g.setColor(Color.BLACK);
        
        if(shape == SHAPE.OVAL)
            this.drawOval(x,y,g);
        if(shape == SHAPE.SQUIGGLE)
            this.drawSquiggle(x,y,g);
        if(shape == SHAPE.DIAMOND)
            this.drawDiamond(x,y,g);
        g.setColor(Color.BLACK);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //Abandon all hope, ye who enter here
    
    
    
    public void drawOval(int x, int y, Graphics g){
        g.setColor(color);
        if(shading == SHADING.DARK){
        if(num == 1 || num == 3)
            g.fillOval(x+35,y+15,20,30);
        if(num == 2){
            g.fillOval(x+20,y+15,20,30);
            g.fillOval(x+50,y+15,20,30);
        }
        if(num == 3){
            g.fillOval(x+7,y+15,20,30);
            g.fillOval(x+63,y+15,20,30);
        }}
        
        if(shading == SHADING.LIGHT){
        if(num == 1 || num == 3){
            g.setColor(lightColor);
            g.fillOval(x+35,y+15,20,30);
            g.setColor(color);
            g.drawOval(x+35,y+15,20,30);
        }
        if(num == 2){
            g.setColor(lightColor);
            g.fillOval(x+20,y+15,20,30);
            g.fillOval(x+50,y+15,20,30);
            g.setColor(color);
            g.drawOval(x+20,y+15,20,30);
            g.drawOval(x+50,y+15,20,30);
        }
        if(num == 3){
            g.setColor(lightColor);
            g.fillOval(x+7,y+15,20,30);
            g.fillOval(x+63,y+15,20,30);
            g.setColor(color);
            g.drawOval(x+7,y+15,20,30);
            g.drawOval(x+63,y+15,20,30);
        }}
        
        if(shading == SHADING.EMPTY){
        if(num == 1 || num == 3){
            g.drawOval(x+35,y+15,20,30);
            g.drawOval(x+36,y+16,18,28);
        }
        if(num == 2){
            g.drawOval(x+20,y+15,20,30);
            g.drawOval(x+50,y+15,20,30);
            g.drawOval(x+21,y+16,18,28);
            g.drawOval(x+51,y+16,18,28);
        }
        if(num == 3){
            g.drawOval(x+7,y+15,20,30);
            g.drawOval(x+63,y+15,20,30);
            g.drawOval(x+8,y+16,18,28);
            g.drawOval(x+64,y+16,18,28);
        }}
    }
    
    public void drawDiamond(int x, int y, Graphics g){
        g.setColor(color);
        if(shading == SHADING.DARK){
        if(num == 1 || num == 3){
            Polygon a = Diamond.diamond();
            a.translate(x+35,y+15);
            g.fillPolygon(a);
        }
        if(num == 2){
            Polygon a = Diamond.diamond();
            a.translate(x+20,y+15);
            g.fillPolygon(a);
            Polygon b = Diamond.diamond();
            b.translate(x+50,y+15);
            g.fillPolygon(b);
        }
        if(num == 3){
            Polygon a = Diamond.diamond();
            a.translate(x+7,y+15);
            g.fillPolygon(a);
            Polygon b = Diamond.diamond();
            b.translate(x+63,y+15);
            g.fillPolygon(b);
        }}
        if(shading == SHADING.LIGHT){
        if(num == 1 || num == 3){
            Polygon a = Diamond.diamond();
            a.translate(x+35,y+15);
            g.setColor(lightColor);
            g.fillPolygon(a);
            g.setColor(color);
            g.drawPolygon(a);
        }
        if(num == 2){
            Polygon a = Diamond.diamond();
            a.translate(x+20,y+15);
            g.setColor(lightColor);
            g.fillPolygon(a);
            Polygon b = Diamond.diamond();
            b.translate(x+50,y+15);
            g.fillPolygon(b);
            
            g.setColor(color);
            g.drawPolygon(a);
            g.drawPolygon(b);
            
        }
        if(num == 3){
            g.setColor(lightColor);
            Polygon a = Diamond.diamond();
            a.translate(x+7,y+15);
            g.fillPolygon(a);
            Polygon b = Diamond.diamond();
            b.translate(x+63,y+15);
            g.fillPolygon(b);
            
            g.setColor(color);
            g.drawPolygon(a);
            g.drawPolygon(b);
        }}
        if(shading == SHADING.EMPTY){
        if(num == 1 || num == 3){
            Polygon a = Diamond.diamond();
            a.translate(x+35,y+15);
            g.drawPolygon(a);
            Polygon b = Diamond.littleDiamond();
            b.translate(x+35,y+15);
            g.drawPolygon(b);
        }
        if(num == 2){
            Polygon a = Diamond.diamond();
            a.translate(x+20,y+15);
            g.drawPolygon(a);
            Polygon A = Diamond.diamond();
            A.translate(x+50,y+15);
            g.drawPolygon(A);
            
            Polygon b = Diamond.littleDiamond();
            b.translate(x+20,y+15);
            g.drawPolygon(b);
            Polygon B = Diamond.littleDiamond();
            B.translate(x+50,y+15);
            g.drawPolygon(B);
        }
        if(num == 3){
            Polygon a = Diamond.diamond();
            a.translate(x+7,y+15);
            g.drawPolygon(a);
            Polygon A = Diamond.diamond();
            A.translate(x+63,y+15);
            g.drawPolygon(A);
            
            Polygon b = Diamond.littleDiamond();
            b.translate(x+7,y+15);
            g.drawPolygon(b);
            Polygon B = Diamond.littleDiamond();
            B.translate(x+63,y+15);
            g.drawPolygon(B);
        }}
    }
    
    public void drawSquiggle(int x, int y, Graphics g){
        g.setColor(color);
        if(shading == SHADING.DARK){
        if(num == 1 || num == 3){
            Polygon a = Squiggle.squiggle();
            a.translate(x+35,y+15);
            g.fillPolygon(a);
        }
        if(num == 2){
            Polygon a = Squiggle.squiggle();
            a.translate(x+20,y+15);
            g.fillPolygon(a);
            Polygon b = Squiggle.squiggle();
            b.translate(x+50,y+15);
            g.fillPolygon(b);
        }
        if(num == 3){
            Polygon a = Squiggle.squiggle();
            a.translate(x+7,y+15);
            g.fillPolygon(a);
            Polygon b = Squiggle.squiggle();
            b.translate(x+63,y+15);
            g.fillPolygon(b);
        }}
        
        if(shading == SHADING.LIGHT){
        if(num == 1 || num == 3){
            g.setColor(lightColor);
            Polygon a = Squiggle.squiggle();
            a.translate(x+35,y+15);
            g.fillPolygon(a);
            g.setColor(color);
            g.drawPolygon(a);
        }
        if(num == 2){
            g.setColor(lightColor);
            Polygon a = Squiggle.squiggle();
            a.translate(x+20,y+15);
            g.fillPolygon(a);
            Polygon b = Squiggle.squiggle();
            b.translate(x+50,y+15);
            g.fillPolygon(b);
            g.setColor(color);
            g.drawPolygon(a);
            g.drawPolygon(b);
        }
        if(num == 3){
            g.setColor(lightColor);
            Polygon a = Squiggle.squiggle();
            a.translate(x+7,y+15);
            g.fillPolygon(a);
            Polygon b = Squiggle.squiggle();
            b.translate(x+63,y+15);
            g.fillPolygon(b);
            g.setColor(color);
            g.drawPolygon(a);
            g.drawPolygon(b);
        }}
        
        if(shading == SHADING.EMPTY){
        if(num == 1 || num == 3){
            Polygon a = Squiggle.squiggle();
            a.translate(x+35,y+15);
            g.drawPolygon(a);
            
            Polygon A = Squiggle.littleSquiggle();
            A.translate(x+35,y+15);
            g.drawPolygon(A);
        }
        if(num == 2){
            Polygon a = Squiggle.squiggle();
            a.translate(x+20,y+15);
            g.drawPolygon(a);
            Polygon b = Squiggle.squiggle();
            b.translate(x+50,y+15);
            g.drawPolygon(b);
            
            Polygon A = Squiggle.littleSquiggle();
            A.translate(x+20,y+15);
            g.drawPolygon(A);
            Polygon B = Squiggle.littleSquiggle();
            B.translate(x+50,y+15);
            g.drawPolygon(B);
        }
        if(num == 3){
            Polygon a = Squiggle.squiggle();
            a.translate(x+7,y+15);
            g.drawPolygon(a);
            Polygon b = Squiggle.squiggle();
            b.translate(x+63,y+15);
            g.drawPolygon(b);
            
            Polygon A = Squiggle.littleSquiggle();
            A.translate(x+7,y+15);
            g.drawPolygon(A);
            Polygon B = Squiggle.littleSquiggle();
            B.translate(x+63,y+15);
            g.drawPolygon(B);
        }}
    }
}
