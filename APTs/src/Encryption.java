import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Encryption {
	static String encrypt(String message) {
		
		/* Generate alphabet index */
		Map<Integer, Character> alphabet = new HashMap<Integer, Character>();
		char letter;
		int i = 0;
		for(letter ='a'; letter <='z'; letter++) {
			if (letter != 'a') {
				i++;
			}
			alphabet.put(i,letter);
		}
		System.out.println(alphabet);
		
		/* Convert String to char Array */
		char[] charArray = message.toCharArray();
		System.out.println(charArray);
		
		/* Create set to store appearance of alphabets */
		Set<Character> charSet = new HashSet<Character>();
		
		/* Create an array to assign a value to letter */
		Map<Character, Character> charMap = new HashMap<Character, Character>();
		
		/* Store characters of string of the word generated */
		int wordLength = charArray.length;
		char[] code = new char[wordLength];
		
		/* Generate the appearance index of each character in message */
		int alpha = 0;
		int pos = 0;
		for (char c: charArray) {
			if (charSet.contains(c)) {
				char ch2 = charMap.get(c);
				code[pos] = ch2;
				pos++;
			} 
			else {
				charSet.add(c);
				char ch1 = alphabet.get(alpha);
				code[pos] = ch1;				
				charMap.put(c, ch1);
				alpha++;
				pos++;
			}
		}
		
		/* Make String from Character string */
		String ans = new String(code);
		System.out.println(ans);
		return ans;
	}
	
	public static void main(String [] args) {
		encrypt("hello");
	}
}
		


	