import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class IsomorphicWords {

	public int countPairs(String[] words) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Set<Character> unique = new HashSet<Character>();
		for (String word : words) {
			for (char c : word.toCharArray()) {
				unique.add(c);
			}
			
		}
	}

	public static void main(String[] args){
		String[] pairs1 = {"abca", "zbxz", "opqr"};
		System.out.println(countPairs(pairs1)); // Returns 1
		String[] pairs2 = {"aa", "ab", "bb", "cc", "cd"};
		System.out.println(countPairs(pairs2)); // Returns 4
		String[] pairs3 = {"cacccdaabc", "cdcccaddbc", "dcdddbccad", 
				"bdbbbaddcb", "bdbcadbbdc", "abaadcbbda", "babcdabbac", 
				"cacdbaccad", "dcddabccad", "cacccbaadb", "bbcdcbcbdd", 
				"bcbadcbbca"};
		System.out.println(countPairs(pairs3)); // Returns 13
	}
	

}
