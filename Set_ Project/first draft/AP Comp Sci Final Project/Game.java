import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game{
    private Board myBoard;
    public Game(){
        myBoard = new Board();
    }
    
    public void drawCards(){
        myBoard.draw(12);
    }
    
    public void drawCards(int n){
        myBoard.draw(n);
    }
    
    public void resetBoard(){
        myBoard.reshuffle();
    }
    
    public String readCards(){
        return myBoard.readBoard();
    }
    
    
    
    public boolean matches(int x, int y, int z){
        return matches(myBoard.get(x),myBoard.get(y),myBoard.get(z));
    }
    
    public boolean matches(Card c1, Card c2, Card c3){
        return matches(c1, c2) && matches(c2, c3);
    }
    public boolean matches(Card c1, Card c2){
        if(c1.equals(c2))
            return true;
        return false;
    }
}