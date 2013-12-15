import java.security.KeyStore.Entry;
import java.util.*;

@SuppressWarnings("unused")
public class FriendScore {

	public int highestScore(String[] friends) {
		// We probably need a double loop to iterate over
		// the person and compare with other people.
		
		// We want to do this in a way without double-counting. 
		
		// Could we take another route by assuming person A is 
		// friends with everyone and we try and disprove that?
		
		// First, let's make a list to store the scores of each person
		ArrayList<Integer> scores = new ArrayList<Integer>();
		// Now, let's populate the table with the index in friends
		for (int i = 0; i < friends.length; i++) {

			//			System.out.println("# Iterating over each person");
			
			// Initialize a counter for person
			int count = 0;
			
			// Initialize a map to record who are friends with the person
			Map<Integer, Boolean> table = new HashMap<Integer, Boolean>();
			String friend = friends[i];
			
			// Let's break up the String into ties to another person
			char[] ties = friend.toCharArray();
			
			// Let us iterate over each relationship for this individual
			for (int j = 0; j < ties.length; j++) {
//				System.out.println("## Iterating over the person's relationships");
				// Resolve the tie at index
				char tie = ties[j];	
				// Remember, we ignore the j-th character of the i-th element
				// since that is a relationship with themselves
				if (i!=j) {
					// Okay, accommodate case for 'Y'
					if (tie == 'Y') {
						table.put(j, true);
	//					System.out.println("count increased to "+count);
					}	
					// If it is a 'N', we need to see if there is common friend
					else if (tie == 'N') {
						table.put(j, false);
					}
				}	
			}
			System.out.println(table);
			
			// So, three people A, B, C. If A and C are not friends, 
			// we check if B is a friend with A and C.
			for (Map.Entry<Integer, Boolean> entry: table.entrySet()) {
//				System.out.println("## Now, we are finding negatives from the first run");
				Integer key = entry.getKey();
				Boolean value = entry.getValue();
				boolean mutualFriend = false;
//				System.out.println("key "+key+" value "+value);
				if (value == true) {
					count++;
					System.out.println(key+" is a friend, count is "+count);
				}
				// If the i-th element and the j-th character are not friends,
				// Escape once we find a connection for one person to another person
				// I.e. There might be more be both B and a D that are friends with C.
				if (value == false) {
					System.out.print(key+" is not a friend of "+i);
					// What are we looking for? Using Example 3,
					// Looking at Person 2, who is friends with Person 1 and 3
					// We check if Person 1 and 3 are friends with 4 and 5.
					for (Map.Entry<Integer, Boolean> entry1: table.entrySet()) {
//						System.out.println("## Finding friends of friends");
							Integer key1 = entry1.getKey();
							Boolean value1 = entry1.getValue();
//							System.out.println("key1 "+key1+" value1 "+value1);
							
							// Get the people who are friends with person
							if (value1 == true) {
								System.out.println(", but "+key1+" is a friend");
								String friend1 = friends[key1];
//									System.out.println("friend1 "+friend1);
								// Go through ties of the person
								char[] ties1 = friend1.toCharArray();
								
								if (ties1[key] == 'Y') {
									mutualFriend = true;
									count++;
									}
								
								}
							
							if (mutualFriend == true) {
//								count++;
								System.out.println("A mutual friend of "+key+" is found for "+i+" count now "+count);
								break;
								}
							
							}
					
						}
				}
			System.out.println(scores);
			scores.add(count);
			}
		int top = Collections.max(scores);
		System.out.println(top);
		return top;
		}
//	public static void main(String[] args) {
//		String[] friends1 = {"NNN",
//				 "NNN",
//		 "NNN"};  
////		highestScore(friends1);
//		// Returns 0
//		// Here, there are 3 people and none of them are friends, 
//		// so everybody has zero 2-friends.
//		String[] friends2 = {"NYY",
//				 "YNY",
//		 "YYN"};
////		highestScore(friends2);
//		// Returns 2
//		// Each person has two 2-friends.
//		String[] friends3 = {"NYNNN",
//				 "YNYNN", 
//				 "NYNYN", 
//				 "NNYNY", 
//				 "NNNYN"};
////		highestScore(friends3);
//		// Returns 4
//		// Persons 0 and 4 have two 2-friends, 
//		// persons 1 and 3 have three 2-friends. 
//		// Person 2 is the most popular one - four 2-friends.
//		String[] friends4 = {"NNNNYNNNNN",
//				 "NNNNYNYYNN",
//				 "NNNYYYNNNN",
//				 "NNYNNNNNNN",
//				 "YYYNNNNNNY",
//				 "NNYNNNNNYN",
//				 "NYNNNNNYNN",
//				 "NYNNNNYNNN",
//				 "NNNNNYNNNN",
//				 "NNNNYNNNNN"};
////		highestScore(friends4);
//		// Returns 8
//		String[] friends5 = {"NNNNNNNNNNNNNNY",
//				 "NNNNNNNNNNNNNNN",
//				 "NNNNNNNYNNNNNNN",
//				 "NNNNNNNYNNNNNNY",
//				 "NNNNNNNNNNNNNNY",
//				 "NNNNNNNNYNNNNNN",
//				 "NNNNNNNNNNNNNNN",
//				 "NNYYNNNNNNNNNNN",
//				 "NNNNNYNNNNNYNNN",
//				 "NNNNNNNNNNNNNNY",
//				 "NNNNNNNNNNNNNNN",
//				 "NNNNNNNNYNNNNNN",
//				 "NNNNNNNNNNNNNNN",
//				 "NNNNNNNNNNNNNNN",
//				 "YNNYYNNNNYNNNNN"};
////		highestScore(friends5);
//		// Returns 6
//	}

}
 