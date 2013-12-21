import java.util.HashMap;


public class IsomorphicWordsFinal {

	   public int countPairs(String[] words) {
		   int count = 0;
		   HashMap<Character, String> map1 = new HashMap<Character, String>();
		   HashMap<Character, String> map2 = new HashMap<Character, String>();
		   for (int i = 0; i < words.length; i++){
				for (int j = 0; j < words.length; j++){
					
					if (i != j) { // this part is important
						String word1 = ""; String word2 = "";
						int k = 0; int char1 = 0; int char2 = 0;
						while (k < words[j].length()) {
							if (!map1.containsKey(words[j].charAt(k))) {
								map1.put(words[j].charAt(k), String.valueOf((char) (char1)));
								word1 += map1.get(words[j].charAt(k));
								char1++;
							}
							else word1 += map1.get(words[j].charAt(k));	
							if(!map2.containsKey(words[i].charAt(k))){
								map2.put(words[i].charAt(k), String.valueOf((char) (char2)));
								word2 += map2.get(words[i].charAt(k));
								char2++;
							}
							else word2 += map2.get(words[i].charAt(k));
							k++;
						}					
						if (word1.equals(word2)) count++;
						map1.clear(); map2.clear();
					}
				}
			}
	       return count/2;
	   }
	   public static void main(String[] args){
		   IsomorphicWordsFinal run = new IsomorphicWordsFinal(); 
		   String[] words1 = {"abca", "zbxz", "opqr"};
		   System.out.println(run.countPairs(words1)); // Returns 1
		   String[] words2 = {"aa", "ab", "bb", "cc", "cd"};
		   System.out.println(run.countPairs(words2)); // Returns 4
		   String[] words3 = {"aaa", "baa", "cdd", "ddd"};
		   System.out.println(run.countPairs(words3)); // Returns 2
		   String[] words4 = {"cacccdaabc", "cdcccaddbc", "dcdddbccad", "bdbbbaddcb", "bdbcadbbdc", "abaadcbbda", "babcdabbac", "cacdbaccad", "dcddabccad", "cacccbaadb", "bbcdcbcbdd", "bcbadcbbca"};
		   System.out.println(run.countPairs(words4)); // Returns 13
   }
}
