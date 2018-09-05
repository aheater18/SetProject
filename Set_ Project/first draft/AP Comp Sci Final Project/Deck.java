import java.util.ArrayList;
import java.awt.Color;

public class Deck{
    private ArrayList<Card> myDeck;
    
    
    public Deck(int x){
        myDeck = new ArrayList<Card>(x);
    }
    
    
    //Generates every card in Set
    public void makeSetDeck(){
        for(int w = 0; w < 3; w++){
            for(int x = 0; x < 3; x++){
                for(int y = 0; y < 3; y++){
                    for(int z = 1; z <= 3; z++){
                        SHAPE shape;
                        Color color;
                        SHADING shade;
                        switch(w){
                            case 0:
                            shape = SHAPE.SQUIGGLE;
                            break;
                            case 1:
                            shape = SHAPE.OVAL;
                            break;
                            default:
                            shape = SHAPE.DIAMOND;
                        }
                        switch(x){
                            case 0:
                            color = Color.RED;
                            break;
                            case 1:
                            color = Color.BLUE;
                            break;
                            default:
                            color = Color.GREEN;
                        }
                        switch(y){
                            case 0:
                            shade = SHADING.DARK;
                            break;
                            case 1:
                            shade = SHADING.LIGHT;
                            break;
                            default:
                            shade = SHADING.EMPTY;
                        }
                        myDeck.add(new Card(shape,shade,color,z));
                    }
                }
            }
        }
    }
    
    
    
    public void add(Card card){
        myDeck.add(card);
    }
    public void remove(int num){
        myDeck.remove(num);
    }
    public void give(Deck hand, int n){
        hand.add(myDeck.remove(n));
    }
    
    
    
    public void giveRandomCards(Deck otherDeck, int numCards){
        for(int x = 0; x < numCards; x++){
            give(otherDeck, (int)(Math.random() * myDeck.size()));
        }
    }
    public void giveRandomCards(Deck hand){
        giveRandomCards(hand, 12);
    }
    
    
    
    public Card get(int n){
        return myDeck.get(n);
    }
    
    
    
    public int indexOf(Card card){
        return myDeck.indexOf(card);
    }
    
    
    
    public String toString(){
        String theReturn = "";
        int n = 0;
        for(Card a : myDeck){
            theReturn += n + ": " + a.toString() + "\n";
            n++;
        }
        return theReturn;
    }
    
    
    
    public void moveAllCards(Deck otherDeck){
        for(Card a : myDeck){
            give(otherDeck, 0);
        }
    }
}
