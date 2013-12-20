import java.util.*;

public class TheBestName {

	public String[] sort(String[] names) {
		Arrays.sort(names, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				int weight1 = 0;
				int weight2 = 0;
				for (int i = 0; i < s1.length(); i++) {
					weight1 += (int) s1.charAt(i) - 64;
				}
				for (int j = 0; j < s2.length(); j++) {
					weight2 += (int) s2.charAt(j) - 64;
				}

				if (s1.equals("JOHN")) {
					weight1 = 26 * 50 + 1;
				}
				if (s2.equals("JOHN")) {
					weight2 = 26 * 50 + 1;
				}

				if (weight1 < weight2) {
					return 1;
				} else if (weight2 < weight1) {
					return -1;
				} else
					return s1.compareTo(s2);
			}
		});
		
		return names;
	}
}