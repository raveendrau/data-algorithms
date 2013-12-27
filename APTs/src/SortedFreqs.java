import java.util.*;


public class SortedFreqs {

	public int[] freqs(String[] data) {
		Map<String, Integer> map = new TreeMap<String, Integer>();
		for (int i = 0; i < data.length; i++) {
			String datum = data[i];
			if (!map.containsKey(datum)) {
				map.put(datum, 1);
			}
			else {
				map.put(datum, map.get(datum)+1);
			}
		}
		int[] sorted = new int[map.size()];
		
		for (int i = 0; i < map.values().size(); i++) {
			sorted[i] = (Integer) map.values().toArray()[i];
		}
		
		return sorted;
	}

	public static void main(String[] args) {
		SortedFreqs run = new SortedFreqs();
		String[] data1 = {"apple", "pear", "cherry", "apple", "cherry", "pear", 
				"apple", "banana"};
		System.out.println(Arrays.toString(run.freqs(data1)));
		// Returns: {3,1,2,2}
		String[] data2 = {"a","b","c","d"};
		System.out.println(Arrays.toString(run.freqs(data2)));
		// Returns {1,1,1,1}
		String[] data3 = {"a","a","a"};
		System.out.println(Arrays.toString(run.freqs(data3)));
		// Returns {3}
	}

}
