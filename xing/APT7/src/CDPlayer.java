import java.util.Arrays;
import java.util.HashSet;

public class CDPlayer {
	public int isRandom(String[] songlist, int n) {
		HashSet<String> songs = new HashSet<String>();
		String completeList = Arrays.toString(songlist)
				.substring(1, Arrays.toString(songlist).length() - 1)
				.replaceAll(", ", "");

		for (int i = 0; i <= completeList.length(); i++) {
			boolean isRand = true;
			for (int k = i; k < completeList.length(); k += n) {
				String temp = "";
				if ((k + n) > completeList.length()) {
					temp = completeList.substring(k);
				} else {
					temp = completeList.substring(k, k + n);
				}
				for (int j = 0; j < temp.length(); j++) {
					if (songs.add("" + temp.charAt(j)) == false) {
						isRand = false;
					}
				}
				songs.clear();

			}
			if (isRand) {
				String temp2 = completeList.substring(0, i);
				for (int l = 0; l < temp2.length(); l++) {
					if (songs.add("" + temp2.charAt(l)) == false) {
						isRand = false;
					}
				}
			}

			if (isRand)
				return i;
		}
		return -1;
	}
}