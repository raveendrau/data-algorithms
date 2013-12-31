import java.util.ArrayList;
import java.util.List;




public class WordLadderV2 {

	/**
	 * In this problem you'll write a method that has parameters representing 
	 * potential interior rungs: an array of strings (these may be nonsense or 
	 * English words), and the anchor rungs --- two strings. 
	 * Your code must determine whether there exists any ladder between the 
	 * exterior rungs that uses at least one interior rung. 
	 * @param words, an array of strings
	 * @param from, anchor rung
	 * @param to, anchor rung
	 * @return If there is any ladder the method returns "ladder", 
	 * otherwise it should return "none".
	 */
	public String ladderExists(String[] words, String from, String to) {
		if (words.length == 0 && linked(from, to)) {
			return "ladder";
		}
		List<String> list = new ArrayList<String>();
		for (String word : words) {
			if (linked(from, word)) {
				list.add(word);
			}
			List<String> newList = new ArrayList<String>();
			for (String newWord : words) {
				if (!newWord.equals(word)) {
					newList.add(newWord);
				}
			}
			String[] newWords = newList.toArray(new String[newList.size()]);
			String res = ladderExists(newWords, word, to);
			if (res.equals("ladder")) {
				return res;
			}
		}
		return "none";
	}	
	
	
	
	/**
	 * A path exists from one word to another if a single change in character
	 * can transform word1 to == word2.
	 * @param word1, word to change
	 * @param word2, word that we would want word1 to transform to
	 * @return true if a path exists
	 */
	public boolean linked(String w1, String w2) {
		int diff = 0;
		for (int i = 0; i < w1.length(); i++) {
			char c1 = w1.charAt(i);
			char c2 = w2.charAt(i);
			if (c1 == c2) {
				;
			}
			else {
				diff++;
			}
		}
		if (diff > 1) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		WordLadderV2 r = new WordLadderV2();
		String[] words1 = {"hot", "dot", "dog"};
		String from1 = "hit";
		String to1 = "cog";
		System.out.println(r.ladderExists(words1, from1, to1));
		// Returns "ladder"
		String[] words2 = {"hot", "dot", "dog", "lot", "log"};
		String from2 = "hit";
		String to2 = "cog";
		System.out.println(r.ladderExists(words2, from2, to2));
		// Returns "ladder"
		String[] words3 = {"rain", "ruin", "gain", "grin", "grit", 
				"main", "pain", "pair", "pail", "mail"};
		String from3 = "sail";
		String to3 = "ruip";
		System.out.println(r.ladderExists(words3, from3, to3));
		// Returns "ladder"
		String[] words4 = {"most", "mist", "fist", "fish"};
		String from4 = "lost";
		String to4 = "cost";
		System.out.println(r.ladderExists(words4, from4, to4));
		// Returns "ladder"
		String[] words5 = {"mist", "fist", "fish"};
		String from5 = "lost";
		String to5 = "cost";
		System.out.println(r.ladderExists(words5, from5, to5));
		// Returns "none"
	}

}
