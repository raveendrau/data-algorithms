
public class TournamentRanker {

	public String[] rankTeams(String[] names, String[] lostTo) {
		
		return names;
	}

	public static void main(String[] args) {
		TournamentRanker r = new TournamentRanker();
		String[] names1 = {"RODDICK", "SCHUETTLER", "FERREIRA", "AGASSI"};
		String[] lostTo1 = {"SCHUETTLER", "AGASSI", "AGASSI", ""};
		System.out.println(r.rankTeams(names1, lostTo1));
		// Returns: { "AGASSI",  "SCHUETTLER",  "FERREIRA",  "RODDICK" }
		String[] names2 = {"DUKE", "SETON HALL", "ILLINOIS", "CINCINNATI",
				 "NORTH CAROLINA", "TEXAS", "XAVIER", "MISSISSIPPI STATE"};
		String[] lostTo2 = {"", "DUKE", "DUKE", "ILLINOIS",
				 "TEXAS", "XAVIER", "DUKE", "XAVIER"};
		System.out.println(r.rankTeams(names2, lostTo2));
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
		System.out.println(r.rankTeams(names3, lostTo3));
		// Returns: { "VISUAL BASIC",  "JAVA" }
	}

}
