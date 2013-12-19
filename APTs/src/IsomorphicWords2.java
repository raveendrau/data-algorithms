import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class IsomorphicWords2 {
	
	static int countPairs(String[] words) {
		
	}
	
	static boolean match(String word1, String word2) {
		
		return false;
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
