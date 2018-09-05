import java.awt.*;

public class Squiggle{
    private static int[] Xs = {10,20,13,20,10,0,7,0};
    private static int[] Ys = {0,8,12,22,30,22,18,8};
    private static int[] X1s = {10,19,12,19,10,1,8,1};
    private static int[] Y1s = {1,8,12,22,29,22,18,8};
    public static Polygon squiggle(){
        return new Polygon(Xs,Ys,8);
    }
    public static Polygon littleSquiggle(){
        return new Polygon(X1s,Y1s,8);
    }
}
