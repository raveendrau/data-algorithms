import java.util.HashMap;
import java.util.Map;


public class IsomorphicWordsV3 {
		
	public static int countPairs(String[] words) {
		int pairs = 0;
		for (int i = 0; i < words.length; i++) {
			String word1 = words[i];
			for (int j = i+1; j < words.length; j++) {
				String word2 = words[j];
				Map<Character, Character> map = new HashMap<Character, Character>();
				for (int k = 0; k < word1.length(); k++) {
					if (!map.containsKey(word1.charAt(k))) {
						map.put(word1.charAt(k), ' ');
					}
					else if (map.containsKey(word1.charAt(k)) 
							&& !map.containsValue(word2.charAt(k))){
						map.put(word1.charAt(k), word2.charAt(k));
					}
				}
				char[] list = new char[word1.length()];
				for (int l = 0; l < word1.length(); l++) {
					list[l] = map.get(word1.charAt(l));
				}
				String str = new String(list);
				if (word2.equals(str)) {
					pairs++;
				}
			}
		}
		return pairs;
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
