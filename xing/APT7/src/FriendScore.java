import java.util.HashSet;

public class FriendScore {

	class Friend {
		HashSet<Integer> immediate = new HashSet<Integer>();
		HashSet<Integer> second = new HashSet<Integer>();
	}

	public int highestScore(String[] friends) {
		Friend[] people = new Friend[friends.length];
		for (int i = 0; i < friends.length; i++) {
			people[i] = new Friend();
			for (int j = 0; j < friends.length; j++) {
				if (friends[i].charAt(j) == 'Y') {
					people[i].immediate.add(j);
				}
			}
		}

		for (int k = 0; k < people.length; k++) {
			for (Integer i : people[k].immediate) {
				people[k].second.addAll(people[i].immediate);
			}
		}

		int max = 0;

		for (Friend f : people) {
			f.immediate.addAll(f.second);
			if (f.immediate.size() > max)
				max = f.immediate.size();
		}

		return max == 0 ? 0 : max - 1;
	}

}