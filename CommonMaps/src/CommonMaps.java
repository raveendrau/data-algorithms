import java.util.*;

public class CommonMaps {

	public int count(String a, String b) {
		@SuppressWarnings("unused")
		int count = 0;
		HashMap<Character, Integer> aLetters = new HashMap<Character, Integer>();
		@SuppressWarnings("unused")
		HashMap<Character, Integer> bLetters = new HashMap<Character, Integer>();
		
		char[] aCharArray = a.toCharArray();
		@SuppressWarnings("unused")
		char[] bCharArray = b.toCharArray();
		
		for (char c : aCharArray) {
			if (aLetters.get(c) == null) {
				aLetters.put(c,0);
			}
			aLetters.put(c, aLetters.get(c) + 1);
		}
	return 0;
	}
}	
