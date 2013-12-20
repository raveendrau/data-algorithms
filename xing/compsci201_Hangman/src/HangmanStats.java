import java.util.*;

/**
 * This is a skeleton program. You'll need to modify it, either by adding code
 * in main or in methods called from main.
 * 
 * @author Xing Su (xs17)
 * 
 */

public class HangmanStats {
	public static void main(String[] args) {
		HangmanFileLoader loader = new HangmanFileLoader();
		loader.readFile("lowerwords.txt");
		HashSet<String> set = new HashSet<String>();
		
		// Part 1a
		for (int i = 4; i <= 20; i++) {
			for (int k = 0; k < 10000; k += 1) {
				set.add(loader.getRandomWord(i));
			}
			System.out
					.printf("number of %d letter words = %d\n", i, set.size());
			set.clear();
		}
		
		// Part 1b
		// On average, how many calls are needed before the same word is returned twice in a row for a given length?
		
		String benchmark = "";
		String current = "";
		int count = 1;
		int index = 0;
		for (int j = 4; j <= 20; j++) {
			benchmark = loader.getRandomWord(j);
			while(index < 50){
				current = loader.getRandomWord(j);
				if(current.equals(benchmark)){
					index++;
				}
				benchmark = current;
				count ++;
			}
			System.out.printf("\n%d-letter word average: %f", j, count/50.0);
			count = 1;
			index = 0;
		}
			
	}
}
