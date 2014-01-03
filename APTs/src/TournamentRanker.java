import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TournamentRanker {
	
	public String[] rankTeams(String[] names, String[] lostTo) {
		final String[] winners = lostTo;
		final String[] losers = names.clone();
		
		Arrays.sort(names, new Comparator<String>() {
			public int compare(String s1, String s2) {
				int wins1 = getWins(s1, winners);
				int wins2 = getWins(s2, winners);
				if (wins1 != wins2) {
					return -(wins1 - wins2);
				}
				return compare(winners[Arrays.asList(losers).indexOf(s1)], 
						winners[Arrays.asList(losers).indexOf(s2)]);
			}
		});
		return names;
	}
	
	public int getWins(String s, String[] winners) {
		int count = 0;
		for (int i = 0; i < winners.length; i++) {
			if (winners[i].equals(s)) {
				count++;
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		TournamentRanker r = new TournamentRanker();
		String[] names1 = {"RODDICK", "SCHUETTLER", "FERREIRA", "AGASSI"};
		String[] lostTo1 = {"SCHUETTLER", "AGASSI", "AGASSI", ""};
		System.out.println(Arrays.toString(r.rankTeams(names1, lostTo1)));
		// Returns: { "AGASSI",  "SCHUETTLER",  "FERREIRA",  "RODDICK" }
		String[] names2 = {"DUKE", "SETON HALL", "ILLINOIS", "CINCINNATI",
				 "NORTH CAROLINA", "TEXAS", "XAVIER", "MISSISSIPPI STATE"};
		String[] lostTo2 = {"", "DUKE", "DUKE", "ILLINOIS",
				 "TEXAS", "XAVIER", "DUKE", "XAVIER"};
		System.out.println(Arrays.toString(r.rankTeams(names2, lostTo2)));
		// Returns: 
//		{ "DUKE",
//			 "XAVIER",
//			 "ILLINOIS",
//			 "TEXAS",
//			 "SETON HALL",
//			 "MISSISSIPPI STATE",
//			 "CINCINNATI",
//			 "NORTH CAROLINA" }
		String[] names3 = {"JAVA", "VISUAL BASIC"};
		String[] lostTo3 = {"VISUAL BASIC", ""};
		System.out.println(Arrays.toString(r.rankTeams(names3, lostTo3)));
		// Returns: { "VISUAL BASIC",  "JAVA" }
	}

}
