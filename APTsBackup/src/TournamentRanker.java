import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TournamentRanker {
	class RankComp implements Comparator<Team> {
		public int compare(Team t1, Team t2) {
			if (t1.getRank() < t2.getRank()) {
				return 1;
			}
			else {
				return -1;
			}
		}
	}

	class Team{
		private String name;
		private int rank;
		
		public Team(String s, int i) {
			this.name = s;
			this.rank = i;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getRank() {
			return rank;
		}
		public void setRank(int r) {
			this.rank = r;
		}
		public String toString() {
			return "Name: "+this.name+"-- Rank: "+this.rank;
		}
	}
	
	public String[] rankTeams(String[] names, String[] lostTo) {
		TreeMap<String, Integer> map = getWins(names, lostTo);	
		TreeMap<String, Integer> moreMap = getLoss(map, names, lostTo);
		String[] array = getArray(moreMap);
		return array;
	}
	
	public String[] getArray(TreeMap<String, Integer> map) {
		List<Team> list = new ArrayList<Team>();
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			String key = entry.getKey();
			Integer value = entry.getValue();
			list.add(new Team(key, value));
		}
		Collections.sort(list, new RankComp());
		String[] ret = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Team t = list.get(i);
			String name = t.getName();
			ret[i] = name;
		}
		return ret;
	}
	
	public TreeMap<String, Integer> getLoss(TreeMap<String, Integer> map,
			String[] names, String[] lostTo) {
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			String key = entry.getKey();
			Integer value = entry.getValue();
			value = value * 10;
			map.put(key, value);
		}
		for (int i = 0; i < names.length; i++) {
			String name = names[i];
			String lostToWho = lostTo[i];
			if (lostToWho != "") {
				int lostToWhoRank = map.get(lostToWho);
				int loserRankScore = lostToWhoRank/5;
				if (!map.containsKey(name)) {
					map.put(name, loserRankScore);
				}
				else {
					map.put(name, map.get(name)+loserRankScore);
				}
			}
		}
		return map;
	}
	
	public TreeMap<String, Integer> getWins(String[] names, String[] lostTo) {
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		for (int i = 0; i < lostTo.length; i++) {
			String winner = lostTo[i];
			if (winner != "") {
				if (!map.containsKey(winner)) {
					map.put(winner, 1);
				}
				else {
					map.put(winner, map.get(winner)+1);
				}
			}			
		}
		for (int i = 0; i < names.length; i++) {
			String name = names[i];
			if (!map.containsKey(name)) {
				map.put(name, 0);
			}
		}
		return map;
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
