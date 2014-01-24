/**
 * 
 * @author Unknown.
 * This is not a product of my work. It is a solution I have come across in my 
 * many travails to solve this problem. As you can see, the work below is pretty
 * sick -- and it took a lot of work and thought that I did not do. 
 * 
 * What I did try, was to understand the approach taken, because I want to know
 * as much as I can learn. The code is only to provide a back-of-the-textbook 
 * answer to the problem. 
 * 
 * It is not my work and should not be considered.
 *
 */

public class DukeOnChessBoard {
	public String to(int x, int y) {
		return "" + (char) ('a' + x) + (char) ('1' + y);
	}

	public String dukePath(int n, String initPosition) {
		int x = initPosition.charAt(0) - 'a';
		int y = initPosition.charAt(1) - '1';
		String path = "";
		boolean[][] uses = new boolean[n][n];
		uses[x][y] = true;
		while (true) {
			path = path + to(x, y) + "-";
			if (x + 1 < n && !uses[x + 1][y]) {
				uses[x + 1][y] = true;
				x = x + 1;
				continue;
			}
			if (y + 1 < n && !uses[x][y + 1]) {
				uses[x][y + 1] = true;
				y = y + 1;
				continue;
			}
			if (y - 1 >= 0 && !uses[x][y - 1]) {
				uses[x][y - 1] = true;
				y = y - 1;
				continue;
			}
			if (x - 1 >= 0 && !uses[x - 1][y]) {
				uses[x - 1][y] = true;
				x = x - 1;
				continue;
			}
			break;
		}
		path = path.substring(0, path.length() - 1);
		if (path.length() > 40) {
			return path.substring(0, 20) + "..."
					+ path.substring(path.length() - 20, path.length());
		} else {
			return path;
		}
	}
}
