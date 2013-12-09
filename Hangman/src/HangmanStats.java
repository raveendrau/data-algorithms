import java.util.*;

/**
 * This is a skeleton program. You'll
 * need to modify it, either by adding code in main
 * or in methods called from main.
 * @author YOUR NAME HERE
 *
 */

public class HangmanStats {
	public static void main(String[] args) {
		HangmanFileLoader loader = new HangmanFileLoader();
		loader.readFile("lowerwords.txt");
		
		HashSet<String> set = new HashSet<String>();
		
//		for(int k=0; k < 1000; k += 1) {
//			set.add(loader.getRandomWord(20));
//		}
		for (int i = 0; i < 10000; i++) {
			set.add(loader.getRandomWord(4));
		}
		System.out.printf("number of 4 letter words = %d\n", set.size());
		
	}
}
