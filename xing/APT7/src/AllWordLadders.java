import java.util.*;

public class AllWordLadders {
	public Map<String, int[]> myMap;
	public Set<String> used;
	public String toWord;
	public String[] givenWords;

	public int[] solve(String[] words, String from, String to) {
		myMap = new HashMap<String, int[]>();
		used = new TreeSet<String>();
		toWord = to;
		givenWords = words;

		Queue<String> currentRungs = new LinkedList<String>();
		for (String s : words) {
			if (changeable(from, s)) {
				currentRungs.add(s);
				used.add(s);
				int[] temp = new int[2];
				temp[0] = 1;
				temp[1] = 1;
				myMap.put(s, temp);
			}
		}

		String lastRung = findRoute(currentRungs);
		if (lastRung.equals("")) {
			int[] temp = new int[2]; 
			temp[0] = 0;
			temp[1] = 0;
			return temp;
		}
		return myMap.get(lastRung);
	}

	public String findRoute(Queue<String> q) {
		Iterator<String> it = q.iterator();
		if (!it.hasNext())
			return "";
		while (it.hasNext()) {
			used.add(it.next());
		}

		Queue<String> nextRungs = new LinkedList<String>();
		while (q.size() != 0) {
			String current = q.remove();

			if (changeable(current, toWord)) {
				myMap.get(current)[0] += 2;
				while (q.size() != 0) {
					String s = q.remove();
					if (changeable(s, toWord)) {
						myMap.get(current)[1] += myMap.get(s)[1];
					}
				}
				return current;
			}

			for (String s : givenWords) {
				if (!used.contains(s) && changeable(current, s)) {
					if (!myMap.containsKey(s)) {
						nextRungs.add(s);
						int[] temp = new int[2];
						temp[0] = myMap.get(current)[0] + 1;
						temp[1] = myMap.get(current)[1];
						myMap.put(s, temp);
					} else {
						myMap.get(s)[1] += myMap.get(current)[1];
					}
				}
			}
		}
		return findRoute(nextRungs);
	}

	public boolean changeable(String a, String b) {
		if (a.length() != b.length())
			return false;
		int differences = 0;
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) != b.charAt(i))
				differences++;
		}
		return differences <= 1;
	}
}