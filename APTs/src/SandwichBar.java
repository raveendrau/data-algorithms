import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class SandwichBar {

	/**
	 * The sandwich bar has certain ingredients available. 
	 * I will list the types of sandwiches I like 
	 * in order of preference and buy the first sandwich 
	 * the bar can make for me. In order for the bar 
	 * to make a sandwich for me, it must include 
	 * all of the ingredients I desire.
	 */
	
	/**
	 * @param String[] available, a list of ingredients 
	 * the sandwich bar can use 
	 * @param String[] orders, the types of sandwiches I like, 
	 * in order of preference (most preferred first), 
	 * @return 0-based index of the sandwich I will buy 
	 * Each element of orders represents one type of sandwich 
	 * I like as a space-separated list of ingredients in the sandwich. 
	 * If the bar can make no sandwiches I like, return -1.
	 */
	public int whichOrder(String[] available, String[] orders) {
		for (int i = 0; i < orders.length; i++) {
			String order = orders[i];
			String[] ingredientsWanted = order.split("\\s+");
			Set<String> ingredientsWantedSet = new HashSet<String>();
			for (String ingredientWanted : ingredientsWanted) {
				ingredientsWantedSet.add(ingredientWanted);
			}
			Set<String> ingredientsAvailableSet = new HashSet<String>();
			for (String ingredientAvailable : available) {
				ingredientsAvailableSet.add(ingredientAvailable);
			}
//			System.out.println(Arrays.toString(ingredientsWanted));
			int numIngredientsWanted = ingredientsWantedSet.size();
			int numIngredientsFound = 0;
			for (String ingredientWanted : ingredientsWantedSet) {
//				System.out.println("Looking for "+ingredientWanted);
				for (String ingredientAvailable : ingredientsAvailableSet) {
//					System.out.println(ingredientAvailable+" is available");
					if (ingredientWanted.equals(ingredientAvailable)) {
						numIngredientsFound++;
					}
				}
			}
			if (numIngredientsFound == numIngredientsWanted) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		SandwichBar run = new SandwichBar();
		
		String[] available1 = { "ham", "cheese", "mustard" };
		String[] order1 = { "ham cheese" };
		System.out.println(run.whichOrder(available1, order1)); // Returns 0
		
		String[] available2 = { "cheese", "mustard", "lettuce" };
		String[] order2 = { "cheese ham", "cheese mustard lettuce", 
				"ketchup", "beer" };
		System.out.println(run.whichOrder(available2, order2)); // Returns 1
		
		String[] available3 = { "cheese", "cheese", "cheese", "tomato" };
		String[] order3 = { "ham ham ham", "water", "pork", "bread", 
				"cheese tomato cheese", "beef" };
		System.out.println(run.whichOrder(available3, order3)); // Returns 4
		
		String[] available4 = { "foo", "bar", "baz", "gazonk", "quux", "bat", 
				"xyzzy", "shme", "hukarz", "grault", "waldo", "bleh" };
		String[] order4 = { "kalatehas", "spam eggs", "needle haystack", 
				"bleh blarg", "plugh", 
		  "the best sandwich in the universe" };
		System.out.println(run.whichOrder(available4, order4)); // Returns -1
	}

}
