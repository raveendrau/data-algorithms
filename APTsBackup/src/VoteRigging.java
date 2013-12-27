import java.util.Arrays;


public class VoteRigging {

	public static int maxVotes(int[] votes) {
		// let's find the maximum number of votes 
		Arrays.sort(votes);
		int max = votes[votes.length - 1];
//		System.out.println(max);
		return max;
	}
	
	public static boolean oneMaxVotes(int max, int[] votes) {
		// we need to check if how many candidates 
		// have this number of votes
		int count = 0;
		for (int i = 0; i < votes.length; i++) {
			if (votes[i] == max) {
				count++;
			}
		}
		if (count > 1) return false;
		else return true;
	}
	
	public static boolean checkMax(int[] votes) {
		int max = maxVotes(votes);
		int candidate = votes[0];
		if (candidate == maxVotes(votes)) {
			if (oneMaxVotes(max, votes)) return true;
			else return false;
		}
		else return false;
	}
	
	public static int[] reduce(int[] votes) {
		// Obviously, we do not iterate over our candidate
		int[] otherVotes = new int[votes.length - 1]; 
		for (int i = 1; i < votes.length; i++) {
			otherVotes[i-1] = votes[i];
		}
		int max = maxVotes(otherVotes);
		for (int j = 1; j < votes.length; j++) {
			if (votes[j] == max) {
				votes[j] = max - 1;
				return votes;
			}
		}
		return votes;
	}
	
	public static int[] increment(int[] votes) {
		int oldVote = votes[0];
		votes[0] = oldVote + 1;
		return votes;
	}
	
	public int minimumVotes(int[] votes) {
		int counter = 0;
		while (!checkMax(votes)) {
			// let's try incrementing by one vote
			increment(votes);
			// and take that vote from somewhere else
			reduce(votes);
			counter++;
		}
		return counter;
	}
//		for (int i = 0; i < votes.length; i++) {
//			System.out.print(votes[i]);
//		}

//	public static void main(String[] args) {
//		int[] list0 = {5, 7, 7};
//		minimumVotes(list0);
//		int[] list1 = {10, 10, 10, 10};
//		minimumVotes(list1);
//		int[] list2 = {1};
//		minimumVotes(list2);
//		int[] list3 = {5, 10, 7, 3, 8};
//		minimumVotes(list3);
//	}

}
