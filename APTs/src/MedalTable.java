import java.util.*;

public class MedalTable {

	public String[] generate(String[] results) {
		Map<String, int[]> map = new HashMap<String, int[]>();
		// looking at one group at a time
		for (String event: results) {
			String[] countries = event.split(" ");
			// looking at one country at a time
			for (int i = 0; i < countries.length; i++) {
				String country = countries[i];
				if (!map.containsKey(country)) {
					// start a blank scoreboard for the country
					int[] tally = {0, 0, 0};
					tally[i] = 1;
					map.put(country, tally);
				}
				else {
					// get the existing scoreboard for the country
					int[] tally = map.get(country);
					tally[i] = tally[i] + 1;
					map.put(country, tally);
				}
			}
		}
		
		String[] table = map.keySet().toArray(new String[map.keySet().size()]);
		
		for (int i = 0; i < table.length; i++) {
			String nation = Arrays.toString(map.get(table[i]));
			table[i] = table[i] + " "
					+ nation.substring(1, nation.length() - 1).replaceAll(",", "");
		}
		
		Arrays.sort(table, new Comparator<String>() {
			public int compare(String s1, String s2) {
				String[] nation1 = s1.split(" ");
				String[] nation2 = s2.split(" ");
				if (!nation1[1].equals(nation2[1])) {
					return -1*Integer.valueOf(nation1[1]).compareTo(Integer.valueOf(nation2[1]));
				}
				else if (!nation1[2].equals(nation2[2])) {
					return -1*Integer.valueOf(nation1[2]).compareTo(Integer.valueOf(nation2[2]));
				}
				else if (!nation1[3].equals(nation2[3])) {
					return -1*Integer.valueOf(nation1[3]).compareTo(Integer.valueOf(nation2[3]));
				}
				else {
					return s1.substring(0, 4).compareTo(s2.substring(0, 4));
				}
			}
		});
		return table;
	}

	public static void main(String[] args) {
		MedalTable run = new MedalTable();
		String[] list1 = {"ITA JPN AUS", "KOR TPE UKR", "KOR KOR GBR", 
				"KOR CHN TPE"};
		System.out.println(Arrays.toString(run.generate(list1)));
		// Returns: { "KOR 3 1 0",  "ITA 1 0 0",  "TPE 0 1 1",  "CHN 0 1 0",  
		// "JPN 0 1 0", "AUS 0 0 1",  "GBR 0 0 1",  "UKR 0 0 1" }
		String[] list2 = {"USA AUT ROM"};
		System.out.println(Arrays.toString(run.generate(list2)));
		// Returns: { "USA 1 0 0",  "AUT 0 1 0",  "ROM 0 0 1" }
		String[] list3 = {"GER AUT SUI", "AUT SUI GER", "SUI GER AUT"};
		System.out.println(Arrays.toString(run.generate(list3)));
		// Returns: { "AUT 1 1 1",  "GER 1 1 1",  "SUI 1 1 1" }
	}

}
