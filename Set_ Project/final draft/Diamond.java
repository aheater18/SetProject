import java.awt.*;

public class Diamond{
    private static int[] Xs = {10,20,10,0};
    private static int[] Ys = {0,15,30,15};
    private static int[] X1s = {10,19,10,1};
    private static int[] Y1s = {1,15,29,15};
    
    public static Polygon diamond(){
        return new Polygon(Xs,Ys,4);
    }
    
    public static Polygon littleDiamond(){
        return new Polygon(X1s,Y1s,4);
    }
}
