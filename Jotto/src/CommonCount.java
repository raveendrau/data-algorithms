import java.util.HashMap;
import java.util.Map;


public class CommonCount {

    private static int commonCount(String a, String b) {
	    // TODO: Implement this method!
		
		/*
		 * Some thoughts:
		 * On the assignment webpage, we suggest an algorithm kind of 
		 * like this:
		 * for each character in String a:
		 *   if that character occurs in String b:
		 *     remove that character from both a and b.
		 *     increment a counter of matches.
		 *     
		 * Implementing that algorithm verbatim is tricky, for a 
		 * couple of reasons, mostly having to do with your variables 
		 * changing length while you iterate over them.
		 * 
		 * Here's a variation that will make things easier:
		 * Construct two HashMap<Character, Integer> objects, one for 
		 * each String, that store how many times each character appears.
		 * Recall that Character is the "key type", and Integer is the 
		 * "value type": that is, the .get method on the map takes in a 
		 * character, and tells you the associated Integer. Recall also 
		 * that Strings have a .charAt method that works like [] works on 
		 * arrays, or .get works on ArrayLists. 
		 * 
		 * Suppose your maps are called map1 and map2. Then, your
		 * algorithm will look something like this:
		 * for (Character c : map1.keySet()) {
		 *   if (map2.containsKey(c)) { 
		 *     // An overlap! Maybe even a multiple overlap...
		 *   } 
		 * } 
		 * 
		 * Done this way, you won't even need to remove anything from 
		 * your maps.
		 */
		// Initialize map1 and map2 
		Map<Character,Integer> map1 = new HashMap<Character, Integer>();
		Map<Character,Integer> map2 = new HashMap<Character, Integer>();
		// Converting String a and String b to character arrays 
		char[] aArray = a.toCharArray();
		char[] bArray = b.toCharArray();
		// Iterating through character arrays and populate maps
		// Starting with String a (map1)
		for (int i = 0; i < aArray.length; i++) {
			char ch = aArray[i];
			if (map1.containsKey(ch)) {
				map1.put(ch, map1.get(ch)+1);
			}
			else {
				map1.put(ch, 1);
			}	
		}
		// Starting with String b (map2)
		for (int i = 0; i < bArray.length; i++) {
			char ch = bArray[i];
			if (map2.containsKey(ch)) {
				map2.put(ch, map2.get(ch)+1);
			}
			else {
				map2.put(ch, 1);
			}	
		}
		// Iterating through maps as we compare two strings
		int count = 0; 
		for (char c : map1.keySet()) {
			if (map2.containsKey(c)) {
				count++;
				map2.remove(c);
			}
		}
		// 0 is left in just so the code will compile. It's not the right
	    // answer most of the time...
		return count;
}


	public static void main(String[] args) {
		int result = commonCount("bagel","label");
		System.out.println(result);
	}

}
