import java.util.Arrays;

/* Keng Low @kl78 */

public class SoccerLeagues {
//	public static void main(String[] args) {		
//		String[] games = { "-WW" , "W-W" , "WW-" };
//		System.out.println(Arrays.toString(games));
//		points(games);
//		String[] games1 = {"-DD", "L-L", "WD-"};
//		System.out.println(Arrays.toString(games1));
//		points(games1);
//		
//	}
	
	public int[] points(String[] matches) {
		/* Create empty array of team scores */
		int teams = matches.length;
//		System.out.println("There are a total of " + teams + " teams");
		int[] scores = new int[teams];
//		System.out.println("This is the board where scores are to be added " +
//				"\n" + Arrays.toString(scores));
		int team = 0;
		for (String match : matches ) {
//			System.out.println("At team " + team + "'s home ground, these are the matches played");
			for (int awayTeam = 0; awayTeam < match.length(); awayTeam++) {
				char result = match.charAt(awayTeam);
//				System.out.println("This is the game's outcome " + result + " for team " + team);
				if (result == 'W') {
					scores[team] += 3;
					scores[awayTeam] += 0;
				}
				if (result == 'D') {
					scores[team] += 1;
					scores[awayTeam] += 1;
				}
				if (result == 'L') {
					scores[team] += 0;
					scores[awayTeam] += 3;
				}
				if (result == '-') {
					scores[team] += 0;
					scores[awayTeam] += 0;
				}
			}
				
			System.out.println(match.toString());
			team ++;	
			}
			
		System.out.println(Arrays.toString(scores));
		return scores;	
		}
		
		
	}


