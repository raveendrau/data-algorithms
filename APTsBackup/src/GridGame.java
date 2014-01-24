
public class GridGame {

	/**
	 * In a simple game, two players take turns placing 'X's in a 4x4 grid. 
	 * Players may place 'X's in any available location ('.' in the input) 
	 * that is not horizontally or vertically adjacent to another 'X'. 
	 * The player who places the last 'X' wins the game. 
	 * It is your turn and you want to know how many of the moves 
	 * you could make guarantee you will win the game, 
	 * assuming you play perfectly.
	 * 
	 * Grid will contain exactly 4 elements.
	 * Each element of grid will contain 4 characters ('X's or '.'s), inclusive.
	 * There will be no two horizontally or vertically adjacent 'X's in grid.
	 */
	
	
	static char[][] myGrid = new char[4][4];

	/**
	 * Check if there horizontally or vertically adjacent 'X's in the grid
	 * @param r, rth row of cell
	 * @param c, cth column of cell
	 * @return boolean, if 'X' can be placed in the cell 
	 */
	private static boolean canPlace(int r, int c) {
		if (myGrid[r][c] == 'X') return false;
		if (r != 0) {
			 if (myGrid[r-1][c] == 'X') return false; 
		}
		if (c != 0) {
			if (myGrid[r][c-1] == 'X') return false;
		}
		if (r != 3) {
			if (myGrid[r+1][c] == 'X') return false;
		}
		if (c != 3) {
			if (myGrid[r][c+1] == 'X') return false;
		}
		return true;
	}
	
	/**
	 * @param grid, a two-dimensional array of characters
	 * @return ret, winning number of moves
	 */
	public static int winningMoves(String[] grid) {
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				myGrid[r][c] = grid[r].charAt(c);
				}
			}
		int ret = findWins();
		return ret;
		}
	
	/**
	 * The method findWins returns the number of wins,
	 * for the player whose turn it is.
	 * * The board myGrid represents the state of the game.
	 * @return number of wins
	 */
	public static int findWins() {
		int total = 0;
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				if (canPlace(r,c)) { // open for a move
					myGrid[r][c] = 'X';
					int oppwins = findWins();
					// update total here
					if (oppwins == 0) total += 1;
					// reset myGrid here
					myGrid[r][c] = '.';
				}
			}
		}
		return total;
	}
	

	
	public static void main(String[] args) {
		String[] gridFoo = {"....",
				 "....",
				 "....",
				 "...."};
		System.out.println(winningMoves(gridFoo)); // Returns 0
		String[] gridBar = {"....",
				 "....",
				 ".X..",
				 "...."};
		System.out.println(winningMoves(gridBar)); // Returns 11
		String[] gridBaz = {".X.X",
				 "..X.",
				 ".X..",
				 "...."};
		System.out.println(winningMoves(gridBaz)); // Returns 1
		String[] gridQux = {".X.X",
				 "..X.",
				 "X..X",
				 "..X."};
		System.out.println(winningMoves(gridQux)); // Returns 0
		String[] gridQuux = {"X..X",
				 "..X.",
				 ".X..",
				 "X..X"};
		System.out.println(winningMoves(gridQuux)); // Returns 0
	}
}
