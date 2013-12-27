import java.util.*;

public class TheBestName {

	public String[] sort(String[] names) {
		Arrays.sort(names, new Comparator<String>() {
			public int compare(String name, String otherName) {
				int nameWeight = 0;
				int otherNameWeight = 0;
				for (int i = 0; i < name.length(); i++) {
					nameWeight += (int) name.charAt(i) - 64;
				}
				for (int j = 0; j < otherName.length(); j++) {
					otherNameWeight += (int) otherName.charAt(j) - 64;
				}

				if (name.equals("JOHN")) {
					nameWeight = 26 * 50 + 1;
				}
				if (otherName.equals("JOHN")) {
					otherNameWeight = 26 * 50 + 1;
				}

				if (nameWeight < otherNameWeight) {
					return 1;
				} else if (otherNameWeight < nameWeight) {
					return -1;
				} else
					return name.compareTo(otherName);
			}
		});
		
		return names;
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