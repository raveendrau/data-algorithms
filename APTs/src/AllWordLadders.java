import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class AllWordLadders {
	public String [] wordList;
	public String fromWord;
	public String toWord;
	public int length;
	public int numSolutions;
	public static HashMap<String, int[]> map = new  HashMap<String, int[]>();
	
	public int[] solve(String[] words, String from, String to) {
		fromWord = from;
		numSolutions =0;
		return solveHelper(words, from, to, new ArrayList<String>());
	}
	public int[] solveHelper(String[] words, String from, String to, ArrayList<String> usedList) {
		int[] results = {0, 0};
//		wordList = words;
//		fromWord = from;
//		toWord = to;
//		length = Integer.MAX_VALUE;
//		numSolutions = 0;
		
//		wordLadder(from, new ArrayList<String>());
		
//		Queue<String> q = new LinkedList<String>();
		
//		int size = 2;
		if (map.containsKey(from)) {
			return map.get(from);
		}
		if (changeable(from, to) && from != fromWord) {
			results[0] = 2;
			results[1] = 1;
			map.put(from, results);
			return results;
		}
		
		int lowestL = Integer.MAX_VALUE;
		for(String s: words){
			
			if(from != s && changeable(from, s) && !usedList.contains(s)){
				ArrayList<String> clone = (ArrayList<String>) usedList.clone();
				clone.add(s);
				int[] nextResult = solveHelper(words, s, to, clone);
				length = 1 + nextResult[0];
//				numSolutions ++;
				
//				System.out.println(newl +" " + lowestL);
				System.out.println(usedList.toString() + "\t" +s);

//				if (nextResult[1] > 0) {
//					results[1] = nextResult[1];
//					lowestL = newl;
//					System.out.println("\t" +usedList.toString() + s + newl + lowestL);
//				}
//				} else if (newl == lowestL) {
////					System.out.println("\t" +usedList.toString() + s);
//					results[1] += nextResult[1];
//					
//				}
				if (length == lowestL) {
					System.out.println("\t" + usedList.toString() + "\t" + s);
					numSolutions ++ ;
				}
			}
			
			if(length < lowestL) {
				lowestL = length;
				numSolutions = 1;
			}
//			if (length == lowestL) {
//				System.out.println("\t" + usedList.toString() + "\t" + s);
//				numSolutions ++ ;
//			}
		}
//		if(lowestL == Integer.MAX_VALUE) results[0] = 0;
//		else results[0] = lowestL;
		
		results[0] = length;
		results[1] = numSolutions;
		map.put(from, results);
		
//		
//		while (q.size() > 0) {
//			String s = q.remove();
//			size ++;
//			if (changeable(s, to)){
//				if(results[0] == size) results[1]++;
//				else{
//					results[0] = size;
//					results[1] = 1;
//					size = 0;
//				}
//				
//			}
//			for (String w : words) {
//				if (!used.contains(w) && changeable(w, s)) {
//					used.add(w);
//					q.add(w);
//				}
//			}
//		}
		return results;

	}
//	
//	public void wordLadder(String word, ArrayList<String> usedList){
//		if(changeable(word, toWord)){
//			int newLength = usedList.size()+2;
//			
//			
//			if(usedList.size() > 0 && usedList.get(0) == fromWord){
////				System.out.print("\n" +usedList.toString() + word);
//				if (newLength < length) {
//					numSolutions = 1;
//					length = newLength;
//					
//				} else if (newLength == length) {
//					numSolutions ++;
//				}
//				
//			}
//		}
//		for(int i = 0; i < wordList.length; i++){
//			if(word != wordList[i] && !usedList.contains(wordList[i])){
//				if(changeable(word, wordList[i])){
//					ArrayList<String> clone = (ArrayList<String>) usedList.clone();
//					clone.add(word);
//					wordLadder(wordList[i], clone);
//				} 
//			}
//		}
//	}
//	
	
	public boolean changeable(String a, String b) {
		if (a.length() != b.length()) return false;
		int differences = 0;
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) != b.charAt(i)) differences++;
		}
		return differences <= 1;
	}
	

		
	}
