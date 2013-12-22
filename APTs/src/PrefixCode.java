
public class PrefixCode {

	/** 
	 * @param words, a String[] words
	 * @return the String "Yes" if that set of words is a prefix code 
	 * or return the String "No, i" if it is not, 
	 * where i is replaced by the lowest 0-based index 
	 * of a String in words that is a prefix of another String in words. 
	 * (That index should have no extra leading zeros.)
	 */
	public boolean isPrefix(String word1, String word2) {
		if (word1.length() >= word2.length()) {
			return false;
		}
		char[] charArray1 = word1.toCharArray();
		char[] charArray2 = word2.toCharArray();
		for (int i = 0; i < charArray1.length; i++) {
			if (charArray1[i] != charArray2[i]) {
				return false;
			}
		}
		return true;
	}
	
	public String isOne(String[] words) {
		for (int i = 0; i < words.length; i++) {
			String word1 = words[i];
			for (int j = 0; j < words.length; j++) {
				String word2 = words[j];
				if (i != j) {
					if (isPrefix(word1, word2)) {
						return "No, "+i;
					}
				}
			}
		}
		return "Yes";
	}

	public static void main(String[] args) {
		PrefixCode run = new PrefixCode();
		
		String[] words1 = {"trivial"};
		System.out.println(run.isOne(words1)); // Returns "Yes"
		
		String[] words2 = {"10001", "011", "100", "001", "10"};
		System.out.println(run.isOne(words2)); // Returns "No, 2"
		
		String[] words3 = {"no", "nosy", "neighbors", "needed"};
		System.out.println(run.isOne(words3)); // Returns "No, 0"
		
		String[] words4 = {"1010", "11", "100", "0", "1011"};
		System.out.println(run.isOne(words4)); // Returns "Yes"
		
		String[] words5 = {"No", "not"};
		System.out.println(run.isOne(words5)); // Returns "Yes"
	}

}
