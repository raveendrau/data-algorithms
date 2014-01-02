
public class QBalance {

	public int bindex(String symbols) {
		String left = "_([{";
		String right = "_)]}";
		int leftIndex = 0;
		int leftSymbol = '\0';
		int leftCount = 0;
		int rightCount = 0;
		char[] chars = symbols.toCharArray();
		for (char c : chars) {
			// Count the number of symbols on the left hand side
			if (left.indexOf(c) >= 0) {
				leftIndex = symbols.indexOf(c);
				leftSymbol = left.indexOf(c);
				leftCount++;
			}
			// Count the number of symbols on the right hand side
			if (right.indexOf(c) >= 0) {
				rightCount++;
			}
		}
		// If there are an equal number of characters on both sides
		if (leftCount == 0 && rightCount == 0) {
			return -1;
		}
		else {
			// Iterate from the first occurrence of a symbol on the left
			for (int i = leftIndex; i < symbols.length(); i++) {
				char c = symbols.charAt(i);
				int rightIndex = right.indexOf(c);
				// Get the corresponding right counterpart to the left symbol
				char rightSymbol = right.charAt(leftSymbol);
				/**
				 * If we find a matching symbol on the right,	
				 * we call bindex recursively with a new String symbols.
				 * It removes the left symbol and inserts a space in place,
				 * to ensure that the length of String symbols is not changed.
				 * bindex will find the next symbol on the left that has a 
				 * counterpart on the right. 
				 */
				if (rightIndex >= 0 && c == rightSymbol) {
					return bindex(symbols.substring(0, leftIndex) + " " +
							symbols.substring(leftIndex+1, i) + " " +
							symbols.substring(i+1));
							
				}	
				/**
				 * If there exists a right symbol and it does not match
				 * the it supposed left counterpart that we are iterating over, 
				 * return the index of the character. 
				 */
				else if (rightIndex >= 0 && 
						c != rightSymbol) {
					return i;
				}
			}
		}
		return symbols.length();
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
