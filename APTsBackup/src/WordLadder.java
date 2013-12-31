import java.util.ArrayList;



public class WordLadder {

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
		String[] newFrom = new String[1];
		newFrom[0] = from;
		if (helper(words, newFrom, to, 1)) {
			return "ladder";
		}
		else return "none";
	}
	
	/**
	 * Recursive function that increases the pool of words to search from
	 * @param words
	 * @param from
	 * @param to
	 * @param i, the number of words to try from String[] words
	 * @return
	 */
	public boolean helper(String[] words, String[] from, String to, int i) {
		
		// make a subset of the list of words that we can use at this stage
		String[] list = new String[i];
		for (int j = 0; j < i; j++) {
			list[j] = words[j];
		}
		
		// if there is at least one inner rung in "from" ,which is required 
		if (from.length > 1) {
			// can we make a connection in the last word of "from" to "to"?
			if (linked(from[from.length-1], to)) {
				return true;
			}
		}
		
		// going through each word in the list of possible words
		for (int j = 0; j < list.length; j++) {
			String word = words[j];
			
			// if our from word is linked to word
			if (linked(from[from.length-1], word)) {
				// insert this inner rung into our list
				String[] newFrom = new String[from.length];
				for (int k = 0; k < from.length; k++) {
					from[k] = newFrom[k];
				}
				newFrom[newFrom.length-1] = word;
				
				// let's see if we can link this inner rung to our outer rung
				if (helper(words, newFrom, to, i)) {
					return true;
				}
			}
		}
		// if there are more words that can be used from String[] words
		if (i < words.length - 1) {
			return helper(words, from, to, i+1);
		}
		return false;
	}
		
	
	/**
	 * A path exists from one word to another if a single change in character
	 * can transform word1 to == word2.
	 * @param word1, word to change
	 * @param word2, word that we would want word1 to transform to
	 * @return true if a path exists
	 */
	public boolean linked(String word1, String word2) {
		int diff = 0; // the number of letters that are differerent
		for(int i = 0; i < word1.length(); i++) {
			char char1 = word1.charAt(i);
			char char2 = word2.charAt(i);
			if (char1 != char2) {
				diff++;
			}
		}
		if (diff == 1) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		WordLadder r = new WordLadder();
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
