import java.util.*;
public class SimpleWordGame {

	public int points (String[] player, String[] dictionary) {
		Set<String> words = new HashSet<String>(); 
		for (String word: player) {
			words.add(word);
		}
		Set<String> answers = new HashSet<String>();
		for (String answer: dictionary) {
			answers.add(answer);
		}
		int total = 0;
		for (String guess : words) {
			if(answers.contains(guess)) {
				int wordLength = guess.length();
				int score = wordLength * wordLength;
				System.out.println("Score is "+score);
				total = score + total;
				System.out.println("Total is "+total);
			}
		}
		return total;
	}

//	public static void main(String[] args) {
//		String[] p1 = { "apple", "orange", "strawberry" };
//		String[] p2 = { "strawberry", "orange", "grapefruit", "watermelon" };
//		System.out.println(points(p1,p2));
//	}

}
