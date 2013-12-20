import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordLadder {
	public String ladderExists(String[] words, String from, String to) {
		return helper(words, from, to, true);
	}
	
	public String helper(String[] words, String from, String to, boolean first) {
		if (from.equals(to) || (!first && changeable(from, to))) return "ladder";
		if (words.length == 0) return "none";
		List<String> tempwords = new ArrayList<String>(Arrays.asList(words));
		for (String w : words){
			if (changeable(from, w)) {
				tempwords.remove(w);
				String[] blah = new String[tempwords.size()];
				for (int i = 0; i < tempwords.size(); i++) blah[i] = tempwords.get(i);
				if (helper(blah, w, to, false).equals("ladder")) return "ladder";
			}
		}
		return "none";
	}
	
	public boolean changeable(String a, String b) {
		if (a.length() != b.length()) return false;
		int differences = 0;
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) != b.charAt(i)) differences++;
		}
		return differences <= 1;
	}
	
}