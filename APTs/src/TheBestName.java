
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
	
	public 
	
	/**
	 * @param String[] names, each element of which contains a single name. 
	 * Sort the names from best to worst 
	 * @return the sorted String[].
	 */
	public String[] sort(String[] names) {
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
