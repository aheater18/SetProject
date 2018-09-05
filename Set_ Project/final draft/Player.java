public class Player{
    private int points;
    private String name;
    private int wins;
    private int losses;
    
    public Player(String n){
        name = n;
    }
    
    public void playedGame(Player other, boolean won, int myPoints, int otherPoints){
        this.addPoints(myPoints);
        other.addPoints(otherPoints);
        if(won){
            this.addWin();
            other.addLoss();
        }else{
            this.addLoss();
            other.addWin();
        }
    }
    
    public void addPoints(int n){
        points += n;
    }
    
    public void addWin(){
        wins++;
    }
    
    public void addLoss(){
        losses++;
    }
    
    public String toString(){
        return name + "/" + points + "/" + wins + "/" + losses;
    }
    
    public String getName(){
        return name;
    }
    
    public void savePlayer(String fileName){
        
    }
    
    public void loadPlayer(String fileName){
        
    }
}
