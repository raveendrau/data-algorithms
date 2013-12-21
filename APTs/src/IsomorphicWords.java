import java.util.HashMap;


public class IsomorphicWords {

	   public  int countPairs(String[] words) {
		   int count = 0;
		   HashMap<Character, String> map = new HashMap<Character, String>();
		   HashMap<Character, String> map2 = new HashMap<Character, String>();

		   for (int i = 0; i < words.length; i++){
				for (int j = 0; j < words.length; j++){
					if (i!=j){
						int k = 0;
						String temp1 = "";
						String temp2 = "";
						int letter =97; 
						int letter2 = 97;
						while(k<words[j].length()){
							if(map.containsKey(words[j].charAt(k))){
								temp1 += map.get(words[j].charAt(k));
							}else{
								map.put(words[j].charAt(k), String.valueOf((char) (letter)));
								temp1 += map.get(words[j].charAt(k));
								letter ++;
							}		
							if(map2.containsKey(words[i].charAt(k))){
								temp2 += map2.get(words[i].charAt(k));
							}else{
								map2.put(words[i].charAt(k), String.valueOf((char) (letter2)));
								temp2 += map2.get(words[i].charAt(k));
								letter2 ++;
							}
							k++;
						}					
						if(temp1.equals(temp2)){
							count++;
						}
						map.clear();
						map2.clear();
					}
				}
			}
	       return count/2;
	   }
	   public static void main(String[] args){
		   IsomorphicWords iso = new IsomorphicWords(); 
 	  
		   String[] list1 = {"abca", "zbxz", "opqr"};
		   int test1 = iso.countPairs(list1);
		   System.out.println(test1); // 1
		  
		   String[] list2 = {"aa", "ab", "bb", "cc", "cd"};
		   System.out.println(iso.countPairs(list2)); // 4
		  
		   String[] list3 = {"aaa", "baa", "cdd", "ddd"};
		   System.out.println(iso.countPairs(list3)); // 2
		   
		   String[] list4 = {"cacccdaabc", "cdcccaddbc", "dcdddbccad", 
					"bdbbbaddcb", "bdbcadbbdc", "abaadcbbda", "babcdabbac", 
					"cacdbaccad", "dcddabccad", "cacccbaadb", "bbcdcbcbdd", 
					"bcbadcbbca"};
			System.out.println(iso.countPairs(list4)); // Returns 13
   }
}
