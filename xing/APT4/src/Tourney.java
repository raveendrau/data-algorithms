import java.util.LinkedList;
import java.util.Queue;

public class Tourney {
	public String winner(String[] bracket, String results) {
		Queue<String> q = new LinkedList<String>();

		for (String s : bracket) {
			q.add(s);
		}
		int i = 0;
		while (q.size() > 1) {
			String team1 = q.remove();
			String team2 = q.remove();
			if (team1.equals("bye") || team2.equals("bye"))
				q.add(roundWinner(team1, team2, '\0'));
			else {
				q.add(roundWinner(team1, team2, results.charAt(i)));
				i++;
			}
		}
		return q.remove();
	}

	public String roundWinner(String a, String b, char result) {
		if (b.equals("bye"))
			return a;
		if (a.equals("bye"))
			return b;
		if (result == 'H')
			return a;
		else
			return b;
	}
}