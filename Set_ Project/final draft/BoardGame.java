import java.util.*;

public class BoardGame{
    private Deck onTheBoard;
    private Deck drawPile;
    private ArrayList<Integer> P1selected = new ArrayList<Integer>(15);
    private ArrayList<Integer> P2selected = new ArrayList<Integer>(15);
    public BoardGame(){
        onTheBoard = new Deck(12);
        drawPile = new Deck(81);
        drawPile.makeSetDeck();
        for(int i = 0; i < 15; i++)
            P1selected.add(0);
        for(int i = 0; i < 15; i++)
            P2selected.add(0);
    }
    
    public int size(){
        return onTheBoard.length();
    }
    
    public boolean matches(Card c1, Card c2, Card c3){
        return c1.equals(c2, c3);
    }
    public boolean matches(int c1, int c2, int c3){
        return onTheBoard.get(c1).equals(onTheBoard.get(c2), onTheBoard.get(c3));
    }
    
    public boolean matchesP1(){
        ArrayList<Integer> b = new ArrayList<Integer>(3);
        for(int i = 0; i < P1selected.size();i++){
            if(P1selected.get(i)==1)
                b.add(i);
        }
        return matches(b.get(0),b.get(1),b.get(2));
    }
    
    public boolean matchesP2(){
        ArrayList<Integer> b = new ArrayList<Integer>(3);
        for(int i = 0; i < P2selected.size();i++){
            if(P2selected.get(i)==1)
                b.add(i);
        }
        return matches(b.get(0),b.get(1),b.get(2));
    }
    
    public boolean testForMatches(){
        boolean match = false;
        
        for(int a = 0; a < onTheBoard.length() - 3; a++)
            for(int b = a; b < onTheBoard.length() - 2; b++)
                for(int c = b; c < onTheBoard.length() - 1; c++)
                    if(this.matches(onTheBoard.get(a),onTheBoard.get(b),onTheBoard.get(c)))
                        match = true;
        return match;
    }
    
    public int numCards(){
        return onTheBoard.length();
    }
    
    public ArrayList<Integer> P1selected(){
        return P1selected;
    }
    
    public ArrayList<Integer> P2selected(){
        return P2selected;
    }
    
    public void toggleSelectP1(int x){
        if(P1selected.get(x) == 0)
            P1selected.set(x,1);
        else
            P1selected.set(x,0);
    }
    
    public void toggleSelectP2(int x){
        if(P2selected.get(x) == 0)
            P2selected.set(x,1);
        else
            P2selected.set(x,0);
    }
    
    public int numCardsSelectedP1(){
        int sum = 0;
        for(int a : P1selected){
            sum+=a;
        }
        return sum;
    }
    
    public int numCardsSelectedP2(){
        int sum = 0;
        for(int a : P2selected){
            sum+=a;
        }
        return sum;
    }
    
    public void reshuffle(){
        onTheBoard.useCards();
        onTheBoard.moveAllCards(drawPile);
        drawPile.giveRandomCards(onTheBoard);
    }
    public void draw(int numCards){
        if(numCards<=drawPile.length())
            drawPile.giveRandomCards(onTheBoard, numCards);
        else
            drawPile.giveRandomCards(onTheBoard,drawPile.length());
    }
    public void discard(Card theCard){
        onTheBoard.remove(onTheBoard.indexOf(theCard));
    }
    
    public int deckSize(){
        return drawPile.length();
    }
    
    public void discardP1(){
        ArrayList<Integer> b = new ArrayList<Integer>(3);
        for(int i = 0; i < P1selected.size();i++){
            if(P1selected.get(i)==1)
                b.add(i);
        }
        discard(b.get(2));
        discard(b.get(1));
        discard(b.get(0));
    }
    public void discardP2(){
        ArrayList<Integer> b = new ArrayList<Integer>(3);
        for(int i = 0; i < P2selected.size();i++){
            if(P2selected.get(i)==1)
                b.add(i);
        }
        discard(b.get(2));
        discard(b.get(1));
        discard(b.get(0));
    }
    public void discard(int theCard){
        onTheBoard.remove(theCard);
    }
    public String readBoard(){
        return onTheBoard.toString();
    }
    public Card get(int n){
        return onTheBoard.get(n);
    }
    
    public void deselectP1(){
        for(int i = 0; i < P1selected.size(); i++)
            P1selected.set(i,0);
    }
    public void deselectP2(){
        for(int i = 0; i < P2selected.size(); i++)
            P2selected.set(i,0);
    }
}
