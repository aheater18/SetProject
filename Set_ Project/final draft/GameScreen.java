    import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import javax.imageio.*;

public class GameScreen extends JPanel implements KeyListener, MouseListener, ActionListener{
    public Player P1, P2;
    public static JFrame theBigWindow;
    public static JPanel myPanel;

    public static boolean MainMenu;
    public static boolean SinglePlayer = false;
    public static boolean multiPlayerPlayed = false;
    public static boolean Multiplayer = false;
    public static boolean Options = false;
    public static boolean HowToPlay = false;
    public static boolean GameOver = false;
    public static boolean Secret = false;

    public static int secretState = 0;
    public static int scoreLimit = 30;
    public static int page = 1;
    public static int scoreP1 = 0;
    public static int scoreP2 = 0;

    public static int cursorP1 = 0;
    public static int cursorP2 = 2;

    public static Timer t;
    public static int time = 120;

    public static BoardGame theGame;
    public final Color backgroundColor = new Color(160,90,240);

    public static void main(String[] args){
        theBigWindow = new JFrame("Set");
        myPanel = new GameScreen(); //game() goes to class name
        theBigWindow.add(myPanel); //Allows access to panels within JFrame
        theBigWindow.setSize(800,620); //Set window size like resolution
        theBigWindow.setVisible(true); //Makes window visible
    }

    public GameScreen(){
        P1 = new Player("Player 1");
        P2 = new Player("Player 2");
        MainMenu = true;
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        t = new Timer(1000,this);
    }

    public void paint(Graphics g){
        g.setFont(new Font("Times New Roman",Font.PLAIN,30));
        //Draws background
        g.setColor(backgroundColor);
        g.fillRect(0,0,800,600);
        g.setColor(Color.BLACK);
        if(MainMenu){
            //Draws Logo
            g.setColor(Color.WHITE);
            g.fillOval(200,50,400,150);
            g.fillRoundRect(315,320,180,200,10,10);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Times New Roman",Font.PLAIN,125));
            g.drawString("Set",310,160);
            g.setFont(new Font("Times New Roman",Font.PLAIN,30));
            //Draws Player names
            //g.drawString(P1.getName(), 70, 100);
            //g.drawString(P2.getName(), 630, 100);

            //Draws menu options
            //Hitbox for Single Player
            //g.drawRect(325,325,160,30);
            g.drawString("Single Player", 325, 350);
            //Hitbox for Multiplayer
            //g.drawRect(325,375,145,30);
            g.drawString("Multiplayer", 325, 400);
            //Hitbox for Options
            //g.drawRect(325,425,100,30);
            g.drawString("Options", 325, 450);
            //Hitbox for How to Play
            //g.drawRect(325,475,150,30);
            g.drawString("How to play", 325, 500);
        }else if(SinglePlayer){
            int counter = 0;
            while(counter < theGame.numCards()){
                int x = counter % 3;
                int y = counter / 3;
                if(GameScreen.theGame.P1selected().get(counter) == 1)
                    g.drawRoundRect(x*130+210-1,y*100+40-1,92,62,5,5);
                theGame.get(counter).draw(x*130+210,y*100+40,counter,g);
                counter++;
            }
            if(Secret){
                try{
                    BufferedImage img = ImageIO.read(new File("sweeney.jpg"));
                    g.drawImage(img,10,200,null);
                    g.drawString(getRandomInsult(),10,465);
                }catch(Exception e){}
            }
            g.setColor(Color.WHITE);
            g.fillRoundRect(15,20,110,40,10,10);
            g.setColor(Color.BLACK);
            g.drawString("Call set",20,50);
            g.drawString("Score: " + scoreP1,20,100);
            g.drawString("Time: " + time,20,150);
        }else if(Multiplayer){
            int counter = 0;
            while(counter < theGame.numCards()){
                int x = counter % 3;
                int y = counter / 3;
                g.setColor(Color.BLUE);
                if(counter == cursorP1){
                    g.drawRect(x*130+205,y*100+35,100,70);
                }
                if(GameScreen.theGame.P1selected().get(counter) == 1)
                    g.fillRoundRect(x*130+210-3,y*100+40-3,96,33,5,5);
                g.setColor(Color.RED);
                if(counter == cursorP2){
                    g.drawRect(x*130+205,y*100+35,100,70);
                }
                if(GameScreen.theGame.P2selected().get(counter) == 1)
                    g.fillRoundRect(x*130+210-3,y*100+40+30,96,33,5,5);
                g.setColor(Color.BLACK);
                theGame.get(counter).draw(x*130+210,y*100+40,counter,g);
                counter++;
            }
            g.setColor(Color.BLACK);
            g.drawString(P1.getName() + ": " + scoreP1,30,100);
            g.drawString(P2.getName() + ": " + scoreP2,610,100);
        }else if(Options){
            g.setColor(Color.WHITE);
            g.fillRoundRect(315,245,170,80,15,15);
            g.setColor(Color.BLACK);
            g.drawString("Score Limit:",325,275);

            //g.drawRect(328,290,33,30);
            g.drawString("<<",328,315);
            g.drawString(scoreLimit+"",375,315);
            //g.drawRect(415,290,33,30);
            g.drawString(">>",415,315);

            //g.drawRect(10,565,185,30);
            g.drawString("<< Main Menu",10,590);
        }else if(HowToPlay){
            if(page==1){
                g.setColor(Color.WHITE);
                g.fillRoundRect(3,3,794,187,10,10);
                g.setColor(Color.BLACK);
                g.drawString("What is Set?",5,30);
                g.drawString("~ Set is a card game created and currently owned by Set",5,80);
                g.drawString("Enterprises, Inc.",5,110);
                g.drawString("~ Never played Set before? Find the official rulebook and game",5,150);
                g.drawString("at setgame.com/set",5,180);
                g.drawString("<< Main Menu",10,590);
                g.drawString("Next >>",690,590);
            }else if(page==2){
                g.setColor(Color.WHITE);
                g.fillRoundRect(3,3,794,176,10,10);
                g.setColor(Color.BLACK);
                g.drawString("Single Player",5,30);
                g.drawString("Single Player is a time trial mode. You have 120 seconds to find",5,80);
                g.drawString("as many sets as you can. Click on cards to select them and click",5,110);
                g.drawString("'Call Set' to submit. You get 3 points for every set you find",5,140);
                g.drawString("and you lose 1 point for every set you call incorrectly.",5,170);
                g.drawString("<< Previous",10,590);
                g.drawString("Next >>",690,590);
            }else if(page==3){
                g.setColor(Color.WHITE);
                g.fillRoundRect(3,3,794,386,10,10);
                g.setColor(Color.BLACK);
                g.drawString("Multiplayer",5,30);
                g.drawString("Local multiplayer. Play Set against your friends! The same",5,80);
                g.drawString("scoring as single player is applied here; 3 points for every set",5,110);
                g.drawString("correctly found, 1 point lost for every incorrect call.",5,140);
                g.drawString("Player 1 uses the WASD keys to move their cursor, the E key",5,170);
                g.drawString("to select cards, and the spacebar to call a set.",5,200);
                g.drawString("Player 2 uses the arrow keys to move their cursor, SHIFT",5,230);
                g.drawString("to select cards, and ENTER to call a set.",5,260);
                g.drawString("By default, the score limit is 30. You can change it to",5,320);
                g.drawString("any number under 30 in the Options menu. If the score limit",5,350);
                g.drawString("is 0, then there will be no score limit.",5,380);
                g.drawString("<< Previous",10,590);
            }else{
                g.drawString("Congratulations! You broke the game somehow!",5,30);
            }
        }else if(GameOver){
            g.setColor(Color.WHITE);
            g.fillRoundRect(320,250,160,100,10,10);
            g.setColor(Color.BLACK);
            g.drawString("Game Over!",325,275);
            g.drawString("P1 Score: " + scoreP1,325,310);
            if(multiPlayerPlayed)
                g.drawString("P2 Score: " + scoreP2,325,340);
            multiPlayerPlayed = false;
        }
    }

    public void mouseClicked(MouseEvent e){
        if(MainMenu){
            //Click on Single Player
            if(e.getX() >= 325 && e.getX() <= 485 &&
            e.getY() >= 325 && e.getY() <= 355){
                SinglePlayer = true;
                MainMenu = false;
                theGame = new BoardGame();
                theGame.draw(12);
                t.start();
            }
            //Click on Multiplayer
            if(e.getX() >= 325 && e.getX() <= 465 &&
            e.getY() >= 375 && e.getY() <= 405){
                Multiplayer = true;
                multiPlayerPlayed = true;
                MainMenu = false;
                theGame = new BoardGame();
                theGame.draw(12);
                cursorP1 = 0;
                cursorP2 = 2;
            }
            //Click on Options
            if(e.getX() >= 325 && e.getX() <= 425 &&
            e.getY() >= 425 && e.getY() <= 455){
                Options = true;
                MainMenu = false;
            }
            //Click on How to Play
            if(e.getX() >= 325 && e.getX() <= 475 &&
            e.getY() >= 475 && e.getY() <= 505){
                HowToPlay = true;
                MainMenu = false;
            }
        }else if(SinglePlayer){
            //15 Cards that could be on the screen
            if(e.getX() >= 210 && e.getX() <=300
            && e.getY() >= 40 && e.getY() <= 100)
                theGame.toggleSelectP1(0);

            if(e.getX() >= 340 && e.getX() <=430
            && e.getY() >= 40 && e.getY() <= 100)
                theGame.toggleSelectP1(1);

            if(e.getX() >= 470 && e.getX() <=560
            && e.getY() >= 40 && e.getY() <= 100)
                theGame.toggleSelectP1(2);

            if(e.getX() >= 210 && e.getX() <=300
            && e.getY() >= 140 && e.getY() <= 200)
                theGame.toggleSelectP1(3);

            if(e.getX() >= 340 && e.getX() <=430
            && e.getY() >= 140 && e.getY() <= 200)
                theGame.toggleSelectP1(4);

            if(e.getX() >= 470 && e.getX() <=560
            && e.getY() >= 140 && e.getY() <= 200)
                theGame.toggleSelectP1(5);

            if(e.getX() >= 210 && e.getX() <=300
            && e.getY() >= 240 && e.getY() <= 300)
                theGame.toggleSelectP1(6);

            if(e.getX() >= 340 && e.getX() <=430
            && e.getY() >= 240 && e.getY() <= 300)
                theGame.toggleSelectP1(7);

            if(e.getX() >= 470 && e.getX() <=560
            && e.getY() >= 240 && e.getY() <= 300)
                theGame.toggleSelectP1(8);

            if(e.getX() >= 210 && e.getX() <=300
            && e.getY() >= 340 && e.getY() <= 400)
                theGame.toggleSelectP1(9);

            if(e.getX() >= 340 && e.getX() <=430
            && e.getY() >= 340 && e.getY() <= 400)
                theGame.toggleSelectP1(10);

            if(e.getX() >= 470 && e.getX() <=560
            && e.getY() >= 340 && e.getY() <= 400)
                theGame.toggleSelectP1(11);

            if(e.getX() >= 210 && e.getX() <=300
            && e.getY() >= 440 && e.getY() <= 500
            && theGame.size() > 12)
                theGame.toggleSelectP1(9);

            if(e.getX() >= 340 && e.getX() <=430
            && e.getY() >= 440 && e.getY() <= 500
            && theGame.size() > 13)
                theGame.toggleSelectP1(10);

            if(e.getX() >= 470 && e.getX() <=560
            && e.getY() >= 440 && e.getY() <= 500
            && theGame.size() > 14)
                theGame.toggleSelectP1(11);
            //Insert button for testing a set here
            if(e.getX() >= 15 && e.getX() <= 125
            && e.getY() >= 20 && e.getY() <= 60
            && theGame.numCardsSelectedP1() == 3){
                if(theGame.matchesP1()){
                    scoreP1+=3;
                    theGame.discardP1();
                    theGame.draw(3);
                    theGame.deselectP1();
                }else{
                    scoreP1--;
                }
            }
            while((!theGame.testForMatches() || theGame.size() < 12)&& theGame.deckSize() > 0)
                theGame.draw(1);
        }else if(Options){
            //Hitbox for increasing scoreLimit
            if(e.getX() >= 415 && e.getX() <= 448 &&
            e.getY() >= 290 && e.getY() <= 320){
                if(scoreLimit < 30)
                    scoreLimit++;
                else
                    scoreLimit=0;
            }
            //Hitbox for decreasing scoreLimit
            if(e.getX() >= 328 && e.getX() <= 361 &&
            e.getY() >= 290 && e.getY() <= 320){
                if(scoreLimit > 0)
                    scoreLimit--;
                else
                    scoreLimit=30;
            }
            //Hitbox for Main Menu button
            if(e.getX() >= 10 && e.getX() <=190 &&
            e.getY() >= 565 && e.getY() <= 595){
                Options = false;
                MainMenu = true;
            }
        }else if(HowToPlay){
            if(page==1){
                if(e.getX() >= 10 && e.getX() <=160 &&
                e.getY() >= 565 && e.getY() <= 595){
                    HowToPlay = false;
                    MainMenu = true;
                }
                if(e.getX() >= 590 && e.getX() <= 800
                && e.getY() >= 565 && e.getY() <= 595)
                    page++;
            }
            else if(page==2){
                if(e.getX() >= 590 && e.getX() <= 800
                && e.getY() >= 565 && e.getY() <= 595)
                    page++;
                if(e.getX() >= 0 && e.getX() <= 200
                && e.getY() >= 565 && e.getY() <= 595)
                    page--;
            }
            else if(page==3){
                if(e.getX() >= 0 && e.getX() <= 200
                && e.getY() >= 565 && e.getY() <= 595)
                    page--;
            }
        }else if(GameOver){
            GameOver = false;
            MainMenu = true;
            scoreP1 = 0;
            scoreP2 = 0;
        }
        repaint();
    }

    public void keyPressed(KeyEvent e){
        int x = e.getKeyCode();
        if(MainMenu){
            switch(secretState){
                case 1:
                if(e.getKeyCode() == KeyEvent.VK_UP)
                    secretState++;
                else
                    secretState=0;
                break;
                case 2:
                if(e.getKeyCode() == KeyEvent.VK_DOWN)
                    secretState++;
                else
                    secretState=0;
                break;
                case 3:
                if(e.getKeyCode() == KeyEvent.VK_DOWN)
                    secretState++;
                else
                    secretState=0;
                break;
                case 4:
                if(e.getKeyCode() == KeyEvent.VK_LEFT)
                    secretState++;
                else
                    secretState=0;
                break;
                case 5:
                if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                    secretState++;
                else
                    secretState=0;
                break;
                case 6:
                if(e.getKeyCode() == KeyEvent.VK_LEFT)
                    secretState++;
                else
                    secretState=0;
                break;
                case 7:
                if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                    secretState++;
                else
                    secretState=0;
                break;
                case 8:
                if(e.getKeyCode() == KeyEvent.VK_B)
                    secretState++;
                else
                    secretState=0;
                break;
                case 9:
                if(e.getKeyCode() == KeyEvent.VK_A)
                    secretState++;
                else
                    secretState=0;
                break;
                case 10:
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    Secret = true;
                else
                    secretState=0;
                break;
                default:
                if(e.getKeyCode() == KeyEvent.VK_UP)
                    secretState++;
            }
        }
        else if(SinglePlayer){
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                stop();
                t.stop();
            }
        }
        else if(Multiplayer){
            //Player 1 Inputs
            if(e.getKeyCode() == KeyEvent.VK_W)
                if(cursorP1 > 2)
                    cursorP1-=3;
                else if(cursorP1%3==(theGame.size()-1)%3)
                    cursorP1 = theGame.size()-1;
                else if(cursorP1%3==(theGame.size()-2)%3)
                    cursorP1 = theGame.size()-2;
                else
                    cursorP1 += theGame.size() - 3 - theGame.size()%3;
            if(e.getKeyCode() == KeyEvent.VK_S)
                if(cursorP1 < theGame.size()-3)
                    cursorP1+=3;
                else
                    cursorP1 = cursorP1%3;
            if(e.getKeyCode() == KeyEvent.VK_A)
                if(cursorP1 % 3 != 0)
                    cursorP1--;
                else
                if(cursorP1+2 <= theGame.size()-1)
                    cursorP1+=2;
                else
                    cursorP1+=(theGame.size()-1)%3;
            if(e.getKeyCode() == KeyEvent.VK_D)
                if(cursorP1 % 3 != 2 && cursorP1 + 1 <= theGame.size() - 1)
                    cursorP1++;
                else
                if(cursorP1 % 3 == 2)
                    cursorP1-=2;
                else if(cursorP1 % 3 == 1)
                    cursorP1-=1;
            if(e.getKeyCode() == KeyEvent.VK_E)
                theGame.toggleSelectP1(cursorP1);
            if(e.getKeyCode() == KeyEvent.VK_SPACE && theGame.numCardsSelectedP1() == 3)
                if(theGame.matchesP1()){
                    scoreP1+=3;
                    theGame.discardP1();
                    theGame.draw(3);
                    theGame.deselectP1();
                    theGame.deselectP2();
                }else{
                    scoreP1--;
                }
            //Player 2 Inputs
            if(e.getKeyCode() == KeyEvent.VK_UP)
                if(cursorP2 > 2)
                    cursorP2-=3;
                else if(cursorP2%3==(theGame.size()-1)%3)
                    cursorP2 = theGame.size()-1;
                else if(cursorP2%3==(theGame.size()-2)%3)
                    cursorP2 = theGame.size()-2;
                else
                    cursorP2 += theGame.size() - 3 - theGame.size()%3;
            if(e.getKeyCode() == KeyEvent.VK_DOWN)
                if(cursorP2 < theGame.size()-3)
                    cursorP2+=3;
                else
                    cursorP2 = cursorP2%3;
            if(e.getKeyCode() == KeyEvent.VK_LEFT)
                if(cursorP2 % 3 != 0)
                    cursorP2--;
                else
                if(cursorP2+2 <= theGame.size()-1)
                    cursorP2+=2;
                else
                    cursorP2+=(theGame.size()-1)%3;
            if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                if(cursorP2 % 3 != 2 && cursorP2 + 1 <= theGame.size() - 1)
                    cursorP2++;
                else
                if(cursorP2 % 3 == 2)
                    cursorP2-=2;
                else if(cursorP2 % 3 == 1)
                    cursorP2-=1;
            if(e.getKeyCode() == KeyEvent.VK_SHIFT)
                theGame.toggleSelectP2(cursorP2);
            if(e.getKeyCode() == KeyEvent.VK_ENTER && theGame.numCardsSelectedP2() == 3)
                if(theGame.matchesP2()){
                    scoreP2+=3;
                    theGame.discardP2();
                    theGame.draw(3);
                    theGame.deselectP1();
                    theGame.deselectP2();
                }else{
                    scoreP2--;
                }
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
                stop();
            while((!theGame.testForMatches() || theGame.size() < 12)&& theGame.deckSize() > 0)
                theGame.draw(1);
            if((scoreP1 >= scoreLimit || scoreP2 >= scoreLimit)&&scoreLimit!=0)
                stop();
        }else if(GameOver){
            GameOver = false;
            MainMenu = true;
            scoreP1 = 0;
            scoreP2 = 0;
        }
        repaint();
    }

    public void actionPerformed(ActionEvent e){
        time--;
        if(time==0){
            t.stop();
            stop();
        }
        repaint();
    }

    public void stop(){
        SinglePlayer = false;
        Multiplayer = false;
        GameOver = true;
        time = 120;
    }

    public String getRandomInsult(){
        int x = (int)(Math.random()*4);
        switch(x){
            case 0:
            return "You're bad";
            case 1:
            return "You're not even that good";
            case 2:
            return "Uninstall, nerd";
            default:
            return "Quit the game";
        }
    }

    public void mousePressed(MouseEvent e){}

    public void mouseEntered(MouseEvent e){}

    public void mouseReleased(MouseEvent e){}

    public void mouseExited(MouseEvent e){}

    public void keyTyped(KeyEvent e){}

    public void keyReleased(KeyEvent e){}
}