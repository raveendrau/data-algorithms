
public class PrefixCode {

	/** 
	 * @param words, a String[] words
	 * @return the String "Yes" if that set of words is a prefix code 
	 * or return the String "No, i" if it is not, 
	 * where i is replaced by the lowest 0-based index 
	 * of a String in words that is a prefix of another String in words. 
	 * (That index should have no extra leading zeros.)
	 */
	public String isOne(String[] words) {
		// TODO Auto-generated constructor stub
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
