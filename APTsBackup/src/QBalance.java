
public class QBalance {

	/**
	 * Write the method bindex that returns the zero-based index of the first 
	 * "right" symbol -- one of ) } ], that does not match a corresponding left 
	 * symbol ( { [ in the string symbols. If the expression fails to close 
	 * properly, e.g., a right-most matching symbol is missing as in 
	 * (3 + [4*5] where the right ')' is missing, return the length of the 
	 * string. If the string represents a balanced expression return -1.
	 * 
	 * There are two scenarios: 
	 * 1. A missing symbol, which means an odd number of characters
	 * 2. A wrong symbol, which means an even number of characters
	 * @param symbols
	 * @return index for missing/wrong symbol or -1 if all okay
	 */
	public int bindex(String symbols) {
		// Sanitize symbols
		String clean = symbols.replaceAll(" -+/*0123456789", "");
		// Types of brackets
		int curly;
		int square;
		int paranthesis;
		// Missing symbol
		if (clean.length() % 2 == 1) {
			if (unbalanced("{", "}", symbols)) {
				
			}
			else if (unbalanced("[", "]", symbols)) {
				
			}
			else if (unbalanced("(", ")", symbols)) {
				
			}
		}
		// Wrong symbol
		else {
			
		}
		return symbols.length();
	}
	
	public int add(String right, String symbols) {
		return 0;
	}
	
	public boolean unbalanced(String left, String right, String symbols) {
		if (count(left, symbols) != count(right, symbols)) {
			return true;
		}
		return false;
	}
	
	public int count(String symbol, String symbols) {
		char s = symbol.charAt(0);
		char[] chars = symbols.toCharArray();
		int count = 0;
		for (char c : chars) {
			if (c == s) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		QBalance q = new QBalance();
		String symbols1 = "([3+5] * 4)";
		System.out.println(q.bindex(symbols1));
		// Returns -1
		String symbols2 = "((3+5)*(4+8)+(5*9)";
		System.out.println(q.bindex(symbols2));
		// Returns 18
		String symbols3 = "(3 + 5]";
		System.out.println(q.bindex(symbols3));
		// Returns 6
		String symbols4 = "(3 + 5 (8 - 4) - [2 *] - 5}";
		System.out.println(q.bindex(symbols4));
		// Returns 26
		String symbols5 = "((((())))))[[[{{{((()))}}}]]]";
		System.out.println(q.bindex(symbols5));
		// Returns 10
		String symbols6 = "3 + 5";
		System.out.println(q.bindex(symbols6));
		// Returns -1
	}
}