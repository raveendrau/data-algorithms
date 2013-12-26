import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class TheBestName {

	/**
	 * The weight of a name is the sum of the weights of all its letters. 
	 * For example, the name MARK has weight 13 + 1 + 18 + 11 = 43.
	 * 
	 * When comparing two names, the one with the larger weight is 
	 * considered better. In case of a tie, the one that comes earlier 
	 * lexicographically is better. But there is one exception - 
	 * the name JOHN is the best name of all.
	 */
	
	public static int weigh(String name) {
		Map<Character, Integer> alphabet = new HashMap<Character, Integer>();
		alphabet.put('A', 1);
		alphabet.put('B', 2);
		alphabet.put('C', 3);
		alphabet.put('D', 4);
		alphabet.put('E', 5);
		alphabet.put('F', 6);
		alphabet.put('G', 7);
		alphabet.put('H', 8);
		alphabet.put('I', 9);
		alphabet.put('J', 10);
		alphabet.put('K', 11);
		alphabet.put('L', 12);
		alphabet.put('M', 13);
		alphabet.put('N', 14);
		alphabet.put('O', 15);
		alphabet.put('P', 16);
		alphabet.put('Q', 17);
		alphabet.put('R', 18);
		alphabet.put('S', 19);
		alphabet.put('T', 20);
		alphabet.put('U', 21);
		alphabet.put('V', 22);
		alphabet.put('W', 23);
		alphabet.put('X', 24);
		alphabet.put('Y', 25);
		alphabet.put('Z', 26);
		
		int weight = 0;
		for(char c: name.toCharArray())
			weight = weight + alphabet.get(c);
		return weight;
	}
	
	/**
	 * 
	 * @param name1, which is the name already in the list
	 * @param name2, which is the name to be added to the list
	 * @return 0, if name1 is heavier
	 * @return 1, if name2 is heavier
	 * @return 2, if the names are equal in weight
	 */
	public int compareWeight(String name1, String name2) {
		if (weigh(name1) > weigh(name2)) {
			return 0;
		}
		else if (weigh(name1) < weigh(name2)) {
			return 1;
		}
		else return 2;
	}
	
	/**
	 * @param String[] names, each element of which contains a single name. 
	 * Sort the names from best to worst 
	 * @return the sorted String[].
	 */
	public String[] sort(String[] names) {
		ArrayList<String> sorted = new ArrayList<String>();
		for (int i = 0; i < names.length; i++) {
			String name = names[i];
			if (i == 0) {
				sorted.add(name);
			}
			for (int j = 0; j < sorted.size(); j++) {
				String sortedName = sorted.get(j);
				// If name is "JOHN", put at the front of the list
				if (name.equals("JOHN")) {
					sorted.add(0, name);
				}
				// if sortedName is heavier than name
				if (compareWeight(sortedName, name) == 0) {
					sorted.add(j, name);
				}
				
			}
		}
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		TheBestName run = new TheBestName();
		String[] names1 = {"JOHN", "PETR", "ACRUSH"};
		System.out.println(run.sort(names1));
		// Returns: {"JOHN", "ACRUSH", "PETR" }
		String[] names2 = {"GLUK", "MARGARITKA"};
		System.out.println(run.sort(names2));
		// Returns: {"MARGARITKA", "GLUK" }
		String[] names3 = {"JOHN", "A", "AA", "AAA", "JOHN", "B", "BB", "BBB", 
				"JOHN", "C", "CC", "CCC", "JOHN"};
		System.out.println(run.sort(names3));
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
		System.out.println(run.sort(names4));
		// Returns: {"TERMINATOR", "SUPERMAN", "SPIDERMAN", "BATMAN" }
		}
	}
}
