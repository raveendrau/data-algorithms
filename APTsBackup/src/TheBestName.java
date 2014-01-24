import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes.Name;


public class TheBestName {

	public class Name implements Comparable<Name> {
		String myName;
		int myScore;
		
		public Name(String name) {
			myName = name;
			String score = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26";
			String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			String[] weightArray =  score.split(" ");
			
			Map<Character, Integer> weights = new HashMap<Character, Integer>();
			for (int i = 0; i < weightArray.length; i++) {
				char letter = alphabet.charAt(i);
				int weight = Integer.parseInt(weightArray[i]);
				weights.put(letter, weight);
			}
			
			int scoring = 0;
			for (int i = 0; i < name.length(); i++) {
				char c = name.charAt(i);
				scoring += weights.get(c);
			}
		}
		
		public int compareTo(Name name) {
			int count = 0;
			if (this.myName.equals("JOHN")) {
				count = -1;
			}
			else {
				count = -(this.myScore - name.myScore);
				if (count == 0) {
					count = (this.myName).compareTo(name.myName);
				}
			}
			return count;
		}
		
		@Override
		public String toString() {
			return myName;
		}
	}

	
	public String[] sort(String[] names) {
		ArrayList<Name> list = new ArrayList<Name>();
		for (String name : names) {
			Name element = new Name(name);
			list.add(element);
		}
		
		Collections.sort(list);
		
		String[] sorted = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			sorted[i] = (list.get(i)).toString();
		}
		return sorted;
	}

	public static void main(String[] args) {
		TheBestName run = new TheBestName();
		String[] names1 = {"JOHN", "PETR", "ACRUSH"};
		System.out.println(Arrays.toString(run.sort(names1)));
		// Returns: {"JOHN", "ACRUSH", "PETR" }
		String[] names2 = {"GLUK", "MARGARITKA"};
		System.out.println(Arrays.toString(run.sort(names2)));
		// Returns: {"MARGARITKA", "GLUK" }
		String[] names3 = {"JOHN", "A", "AA", "AAA", "JOHN", "B", "BB", "BBB", 
				"JOHN", "C", "CC", "CCC", "JOHN"};
		System.out.println(Arrays.toString(run.sort(names3)));
//		Returns: 
//		{"JOHN",
//		"JOHN",
//		"JOHN",
//		"JOHN",
//		"CCC",
//		"BBB",
//		"CC",
//		"BB",
//		"AAA",
//		"C",
//		"AA",
//		"B",
//		"A" }
		String[] names4 = {"BATMAN", "SUPERMAN", "SPIDERMAN", "TERMINATOR"};
		System.out.println(Arrays.toString(run.sort(names4)));
		// Returns: {"TERMINATOR", "SUPERMAN", "SPIDERMAN", "BATMAN" }
		}
	
}
