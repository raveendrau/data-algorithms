
public class FloodRelief {
	char[][] list;
	int numRows;
	int numCols;
	int minRows = -1;
	int minCols = -1;
	char min;
	
	public int minimumPumps(String[] heights) {
		numRows = heights.length;
		numCols = heights[0].length();
		list = new char[numRows][numCols];
		int pumps = 0;
		// make grid
		for (int r = 0; r < numRows; r++) {
			for (int c = 0; c < numCols; c++) {
				list[r][c] = heights[r].charAt(c);
			}
		}
		// calculate pumps
		while(getLow()) {
			check(min, minRows, minCols);
			pumps++;
		}
		return pumps;
	}
	
	
	public void check(char d, int r, int c) {
		if (r >= 0 && r < numRows && c >= 0 && c < numCols) {
			if (list[r][c] != '*' && list[r][c] >= d) {
				char t = list[r][c];
				list[r][c] = '*';
				check(t, r+1, c);
				check(t,r-1,c);
				check(t,r,c+1);
				check(t,r,c-1);
			}
		}
	}
	
	public boolean getLow() {
		int i = numRows * numCols;
		min = '{';
		for (int r = 0; r < numRows; r++) {
			for (int c = 0; c < numCols; c++) {
				if (list[r][c] == '*') {
					i--;
				}
				else if (list[r][c] < min) {
					min = list[r][c];
					minRows = r;
					minCols = c;
				}
			}
		}
		if (i == 0) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		FloodRelief fr = new FloodRelief();
		String[] heights1 = {"ccccc",
				 "cbbbc",
				 "cbabc",
				 "cbbbc",
				 "ccccc"};
		System.out.println(fr.minimumPumps(heights1));
		// Returns 1
		String[] heights2 = {"cbabcbabc",
				 "cbabcbabc",
				 "cbabcbabc",
				 "cbabcbabc"};
		System.out.println(fr.minimumPumps(heights2));
		// Returns 2
		String[] heights3 = {"ccccccccccc",
				 "caaaaaaaaac",
				 "caaaaaaaaac",
				 "caazpppzaac",
				 "caapdddpaac",
				 "caapdddpaac",
				 "caapdddpaac",
				 "caazpppzaac",
				 "caaaaaaaaac",
				 "caaaaaaaaac",
				 "ccccccccccc"};
		System.out.println(fr.minimumPumps(heights3));
		// Returns 2
		String[] heights4 = {"ab",
		 "ba"};
		System.out.println(fr.minimumPumps(heights4));
		// Returns 2
	}

}
