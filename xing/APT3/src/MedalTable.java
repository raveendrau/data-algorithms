import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class MedalTable {
	public String[] generate(String[] results) {
		HashMap<String, int[]> map = new HashMap<String, int[]>();
		for (String s : results) {
			String[] medals = s.split(" ");
			for (int i = 0; i < medals.length; i++) {
				if (!map.containsKey(medals[i])) {
					int[] score = { 0, 0, 0 };
					score[i] = 1;
					map.put(medals[i], score);
				}else {
					int[] score = map.get(medals[i]);
					score[i] = score[i] + 1;
					map.put(medals[i], score);
				}
			}
		}
		
		String[] table = map.keySet().toArray(new String[map.keySet().size()]);

		for (int j = 0; j < table.length; j++) {
			String temp = Arrays.toString(map.get(table[j]));
			table[j] = table[j] + " "
					+ temp.substring(1, temp.length() - 1).replaceAll(",", "");
		}
		
		Arrays.sort(table, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				String[] country1 = s1.split(" ");
				String[] country2 = s2.split(" ");
				if(!country1[1].equals(country2[1]))
					return -1*Integer.valueOf(country1[1]).compareTo(Integer.valueOf(country2[1]));
				else if(!country1[2].equals(country2[2]))
					return -1*Integer.valueOf(country1[2]).compareTo(Integer.valueOf(country2[2]));
				else if(!country1[3].equals(country2[3]))
					return -1*Integer.valueOf(country1[3]).compareTo(Integer.valueOf(country2[3]));
				else
					return s1.substring(0, 4).compareTo(s2.substring(0, 4));
			}
		});
		
		return table;
	}
}