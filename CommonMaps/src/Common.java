import java.util.ArrayList;
import java.util.List;


public class Common {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		count("horse", "mirth");
	}
	
	public static int count (String a, String b) {
		@SuppressWarnings("unused")
		int count = 0;
		List<Character> chars = new ArrayList<Character>();
		for (int i = 0; i < a.length(); i++) {
			char aChar = a.charAt(i); 
			for (int j = 0; j < b.length(); j++) {
				char bChar = b.charAt(j);
				if (chars.isEmpty()) {
					if (aChar == bChar) {
						count += 1;
						chars.add(aChar);
					}					
				}
			}
		}
		return 0;
	}
}