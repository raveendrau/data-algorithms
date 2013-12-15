/**
 * @author Owen Astrachan
 * @version Sep 10, 2004
 * 
 * Starts up a Jotto program. You don't need to modify this file in any 
 * way.
 */

public class Jotto {
    @SuppressWarnings("unused")
	public static void main(String[] args){
        JottoModel model = new JottoModel();
        JottoViewer viewer = new JottoViewer("Duke Jotto", model);
    }
}
