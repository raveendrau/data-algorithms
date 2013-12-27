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
					int[] score = {0, 0, 0};
					score[i] = 1;
					map.put(medals[i], score);
				}
				else {
					// get the existing scoreboard for the country
					int[] score = map.get(medals[i]);
					score[i] = score[i] + 1;
					map.put(medals[i], value)
				}
			}
		}
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
