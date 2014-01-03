import java.util.Arrays;


public class Tourney {

	/**
	 * We have the tournament bracket (including any byes) from last year's 
	 * tournament. We also have a list of the results of the games in the order 
	 * in which they were played. We want to figure out who won last year's 
	 * tournament. Create a class Tourney that contains a method winner that is 
	 * @param String[] bracket, the bracket is given in order from top to 
	 * bottom, and the results are given as a string of 'H' or 'L' indicating 
	 * for each game (in the order in which the games were played) whether the 
	 * Higher or Lower team from the bracket won that game. 
	 * @param String results contains a character for each real game, 
	 * but not for byes.
	 * @return the name of the winning team as a String
	 */
	public String winner(String[] bracket, String results) {
		String[] newBracket = new String[bracket.length/2];
//		String[] resultsArray = results.split("(?!^)");
		if (bracket.length == 1) {
			return bracket[0];
		}
		int bracketIndex = 0;
		int resultsIndex = 0;
		for (int i = 0; i < bracket.length; i = i + 2) {
			String high = bracket[i];
			String low = bracket[i+1];
			if (high.equals("bye")) {
				newBracket[bracketIndex] = low;
				bracketIndex++;
			}
			else if (low.equals("bye")) {
				newBracket[bracketIndex] = high;
				bracketIndex++;
			} 
			else if (results.charAt(resultsIndex) == 'H'){;
				newBracket[bracketIndex] = high;
				bracketIndex++;
				resultsIndex++;
			}
			else if (results.charAt(resultsIndex) == 'L'){
				newBracket[bracketIndex] = low;
				bracketIndex++;
				resultsIndex++;
			}
		}
		String newResults = results.substring(resultsIndex);
//		System.out.println(Arrays.toString(newBracket));
		return winner(newBracket, newResults);
	}

	public static void main(String[] args) {
		Tourney t = new Tourney();
		String[] bracket1 = {"DUKE","UCLA","bye","MIT"};
		String results1 = "HL";
		System.out.println(t.winner(bracket1, results1));
		// Returns "MIT"
		String[] bracket2 = {"A","B","C","bye","D","E","F","bye"};
		String results2 = "LHHLH";
		System.out.println(t.winner(bracket2, results2));
		// Returns B
		String[] bracket3 = {"MIT","bye"};
		String results3 = "";
		System.out.println(t.winner(bracket3, results3));
		// Returns "MIT"
		String[] bracket4 = {"STANFORD","bye","STANFORD","bye"};
		String results4 = "L";
		System.out.println(t.winner(bracket4, results4));
		// Returns "STANFORD"
	}

}
