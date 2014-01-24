import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@SuppressWarnings("unused")
public class IsomorphicWordsV2 {
	
	public static int countPairs(String[] words) {
		Map<String, String> map = new HashMap<String, String>();
		int pairs = 0;
		for (int i = 0; i < words.length; i++) {
			for (int j = i + 1; j < words.length; j++) {
				if(match(words[i],words[j])) pairs++;
				}
			}
		return pairs;
	}
	
	static String[] analyze(String word) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (Character c : word.toCharArray()) {
			if (map.containsKey(c)) {
				map.put(c, map.get(c)+1);
			}
			else {
				map.put(c, 1);
			}
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (Character key : map.keySet()) {
			list.add(map.get(key));
		}
		Collections.sort(list);
		String[] results = new String[list.size()];
		int loop = 0;
		for (Integer i : list) {
			results[loop] = Integer.toString(i);
			loop++;
		}
		System.out.println(Arrays.toString(results));
		return results;
	}
	
	static boolean match(String word1, String word2) {
		if(Arrays.equals(analyze(word1), analyze(word2))) {
			System.out.println(word1+" "+word2);
			return true;
		}
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
