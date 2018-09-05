public class Board{
    public Deck onTheBoard;
    public Deck drawPile;
    public Board(){
        onTheBoard = new Deck(12);
        drawPile = new Deck(81);
        drawPile.makeSetDeck();
    }
    public void reshuffle(){
        onTheBoard.moveAllCards(drawPile);
    }
    public void draw(int numCards){
        drawPile.giveRandomCards(onTheBoard, numCards);
    }
    public void discard(Card theCard){
        onTheBoard.remove(onTheBoard.indexOf(theCard));
    }
    public String readBoard(){
        return onTheBoard.toString();
    }
    
    public Card get(int n){
        return onTheBoard.get(n);
    }
}
