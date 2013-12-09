

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.math.BigInteger; 


@SuppressWarnings("unused")

public class IsomorphicWords {

	
	static Collection<Integer> numLetter(String word) {
		/* Make a container to store the letters of each word. Use a map.*/
		Map<Character, Integer> chars = new HashMap<Character, Integer>();
		char[] wordSplit = word.toCharArray(); 
		for (Character ch: wordSplit) {
			if (chars.containsKey(ch)) {
				int curNum = chars.get(ch);
				int newNum = curNum + 1;
				chars.put(ch, newNum);
			}
			else {
				chars.put(ch, 1);
			}
		}
		Collection<Integer> mixture = chars.values();
		System.out.println("Mixture is: "+mixture);
		return mixture;
	}
	
	static int fact(int i) {
		int res = 1;
		for (int j = i; j > 1; j--) {
			res = res * j;
		}
		return res;
	}
	
	static int nCr(int n) {
		int nf=fact(n); 
		int rf=fact(2); 
		int nrf=fact(n-2); 
		int npr=nf/nrf; 
		int ncr=npr/rf;
		return ncr;
	}
	
	@SuppressWarnings("rawtypes")
	static ArrayList<Collection> listGen(String[] words) {
		/**
		 * What does it mean to be isomorphic?
		 * It means the *set* of letters of one word should
		 * be equal to another word
		 * We take one word and compare with all other words 
		 */
		ArrayList<Collection> list = new ArrayList<Collection>();
		/* Get the size of all the words 
		 * and then table them and see 
		 * how many occurrences for each size of word */
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			Collection<Integer> mixture = numLetter(word);
			list.add(mixture);
//			System.out.println("Mixture added: "+mixture);
		}
		System.out.println("The list of mixtures: "+list);
		return list;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	static int mapGen(String[] words) {
		ArrayList<Collection> list = listGen(words);
		HashMap<HashSet, Integer>map = new HashMap<HashSet, Integer>();
		int match = 0;
		for (int j = 0; j < list.size(); j++) {
			Collection colx = list.get(j);
			Set<Integer> num = new HashSet<Integer>(colx);
			System.out.println("Looking at: "+colx);
			if (map.containsKey(num)) {
				int curVal = map.get(num);
				int newVal = curVal + 1;
				map.put((HashSet) num, newVal);
				System.out.println(num+"'s value updated to "+newVal+" for: "+map);
			}
			else {
				map.put((HashSet) num, 1);
			}
		}
		for (int val : map.values()) {
			if (val > 2) {
				match = match + nCr(val);
			}
			if (val == 2) {
				match = match + 1;
			}
			else {
			} 
		}
		return match;
	}
	
	public static int countPairs1(String[] words) {
		int result = mapGen(words);
		System.out.println("Final: "+result);
		return result;
	}

	/**
	 * Change in tactics 
	 */
	
	static boolean contrast(String w1, String w2) {
		if (w1.length() != w2.length()) return false;
		HashMap<Character, Character> map = new HashMap<Character, Character>();
		ArrayList<Character> changed = new ArrayList<Character>();
		for (int i = 0; i < w1.length(); i++) {
			char l1 = w1.charAt(i);
			char l2 = w2.charAt(i);
			if (map.containsKey(l1)) 
				if (map.get(l1) != l2) return false;
			else {
				if (changed.contains(l2)) return false;
				changed.add(l2);
				map.put(l1, l2);
			}
		}
		return true;
	}
	
	public static int countPairs(String[] words) {
		int ct = 0;
		for (int i = 0; i < words.length-1; i++) {
			String w1 = words[i];
			for (int j = i + 1; j < words.length; j++) {
				String w2 = words[j];
				if (contrast(w1, w2)) ct++;
			}
		}
		return ct;
	}
	
	public static void main(String[] args){
		String[] pairs1 = {"abca", "zbxz", "opqr"};
		System.out.println(countPairs(pairs1));
		String[] pairs2 = {"aa", "ab", "bb", "cc", "cd"};
		System.out.println(countPairs(pairs2));
		String[] pairs3 = {"cacccdaabc", "cdcccaddbc", "dcdddbccad", "bdbbbaddcb", "bdbcadbbdc", "abaadcbbda", "babcdabbac", "cacdbaccad", "dcddabccad", "cacccbaadb", "bbcdcbcbdd", "bcbadcbbca"};
		System.out.println(countPairs(pairs3));
	}
}
