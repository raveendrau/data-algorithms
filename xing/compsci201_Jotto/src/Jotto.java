/**
 * @author Owen Astrachan
 * @version Sep 10, 2004
 * 
 * Starts up a Jotto program. You don't need to modify this file in any 
 * way.
 */
import java.util.Random;
import java.util.Scanner;
public class Jotto {
    public static void main(String[] args){
//    	Random x = new Random();
//    	System.out.print("" + x.nextInt(5757) + "\t" + x.nextInt(5757));
        JottoModel model = new JottoModel();
        JottoViewer viewer = new JottoViewer("Duke Jotto", model);
    }
}
