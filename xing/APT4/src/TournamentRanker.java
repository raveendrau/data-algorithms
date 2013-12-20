import java.util.Arrays;
import java.util.Comparator;
public class TournamentRanker {
	public String[] rankTeams(String[] names, String[] lostTo) {

		final String[] winners = lostTo;
		final String[] losers = names.clone();

		Arrays.sort(names, new Comparator<String>(){

			@Override
			public int compare(String s1, String s2) {
				int count1 = countWin(s1, winners);
				int count2 = countWin(s2, winners);
				if(count1 != count2){
					return -(count1 - count2);
				}
				return compare(winners[Arrays.asList(losers).indexOf(s1)], winners[Arrays.asList(losers).indexOf(s2)]);
			}
			
		});
		return names;
	}
	
	public static int countWin(String s, String[] win){
		int count = 0;
		for (int i = 0; i < win.length; i++){
			if(win[i].equals(s)){
				count ++;
			}
		}
		return count;
	}
}