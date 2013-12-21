import java.util.ArrayList;


public class Anonymous {

	public static ArrayList<Character> letters(String[] words) {
		/* Collect all the letters */
		ArrayList<Character> chars = new ArrayList<Character>();
		for (String str : words) {
			for (char b: str.toCharArray()) {
				Character c = Character.toUpperCase(b);
				chars.add(c);
			}
		}
//		System.out.println(chars);
		return chars;
	}
	
	public int howMany(String[] headlines, String[] messages) {
		int hits = 0;
		/* Iterate through each message */
		for (String message : messages) {
			ArrayList<Character> headlineLetters = letters(headlines);
			int chk = 0;
			int iter = 0;
			char[] charArray = message.toCharArray(); 
			for (Character b : charArray) {
				Character c = Character.toUpperCase(b);
				iter ++;
				/* Considering spaces */
				if (headlineLetters.contains(c)) {
					int pos= headlineLetters.indexOf(c);
					headlineLetters.remove(pos);
					chk ++;
				}
				else if (c == ' ') {
					chk ++;
				}
			}
//			System.out.println("The value of chk is "+chk);
//			System.out.println("The value of iter is "+iter);
			if (chk == iter) {
				hits ++;
			}
		}
//		System.out.println(hits);
		return hits;
	}

//	public static void main(String[] args){
//		String[] headlines01 = {"Earthquake in San Francisco", "Burglary at musuem in Sweden", "Poverty"};
//		String[] messages01 = {"Give me my money back", "I am the best coder", "TOPCODER"};
//		howMany(headlines01, messages01);
//		// Returns: 2
//		String[] headlines02 = {"Programming is fun"};
//		String[] messages02 = {"program","programmer","gaming","sing","NO FUN"};
//		howMany(headlines02, messages02);
//		// Returns: 4
//		String[] headlines03 = {"abcdef","abcdef"};
//		String[] messages03 = {"AaBbCc","aabbbcc","   ","FADE"};
//		howMany(headlines03, messages03);
//		// Returns: 3
//		
//	}
}
