import java.util.*;


public class TheBestName {

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
	
	public static TreeMap<Integer, ArrayList<String>> getWeights(String[] names) {
		
		TreeMap<Integer, ArrayList<String>> weights = new TreeMap<Integer, ArrayList<String>>();
		
		for(String name: names) {
			
			Integer weight = weigh(name);
			
			if (weights.containsKey(weight)) {
				ArrayList<String> value = weights.get(weight);
				value.add(name);
				weights.put(weight, value);
			}
			
			else {
				ArrayList<String> value = new ArrayList<String>();
				value.add(name);
				weights.put(weight, value);
			}
			
		}
		
		return weights;
	}
	
	public static ArrayList<String> lexico(ArrayList<String> value) {
		Collections.sort(value);
		return value;
	} 
	
	public String[] sort(String[] names) {
		
		TreeMap<Integer, ArrayList<String>> weights = getWeights(names);
		
		for(int weight: weights.keySet()) {
			ArrayList<String> value = weights.get(weight);
			value = lexico(value);
			weights.put(weight, value);
		}
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		ListIterator<Integer> iter = new ArrayList(weights.keySet()).listIterator(weights.size());
		
		ArrayList<String> named = new ArrayList<String>();
		
//		System.out.println(weights);
		
		while (iter.hasPrevious()) {
			Integer key = iter.previous();
//			System.out.println(key);
			ArrayList<String> value = weights.get(key);
			ArrayList<String> rev = new ArrayList<String>();
			for(String s: value) rev.add(s);
//			System.out.println(rev);
			for(String s: rev) named.add(s);
		}
		
		for(int i = 0; i < named.size(); i++) {
			String n = named.get(i);
			if (n == "JOHN") {
				named.remove(i);
				named.add(0, n);
			}
		}
		
		String[] last = new String[named.size()];
		last = named.toArray(last);
//		System.out.println(Arrays.toString(last));
		return last;
	}
	
//	public static void main(String[] args) {
//		String[] input0 = {"JOHN", "PETR", "ACRUSH"};  
//		sort(input0);
//		// Returns: {"JOHN", "ACRUSH", "PETR" }
//		
//		String[] input1 = {"GLUK", "MARGARITKA"};  
//		sort(input1);
//		// Returns: {"MARGARITKA", "GLUK" }
//		// MARGARITKA is definitely better than GLUK.
//		
//		String[] input2 = {"JOHN", "A", "AA", "AAA", "JOHN", "B", "BB", "BBB", "JOHN", "C", "CC", "CCC", "JOHN"};
//		sort(input2);
//		// Returns: 
////		{"JOHN",
////			"JOHN",
////			"JOHN",
////			"JOHN",
////			"CCC",
////			"BBB",
////			"CC",
////			"BB",
////			"AAA",
////			"C",
////			"AA",
////			"B",
////			"A" }
//		// AA and B both have the same weight, but AA is better as it comes earlier lexicographically. 
//		// For the same reason, AAA is better than C and BBB is better than CC.
//		
//		String[] input3 = {"BATMAN", "SUPERMAN", "SPIDERMAN", "TERMINATOR"};
//		sort(input3);
//		// Returns: {"TERMINATOR", "SUPERMAN", "SPIDERMAN", "BATMAN" }
//
//	}
}
