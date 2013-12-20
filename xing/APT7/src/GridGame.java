import java.util.Arrays;

public class GridGame {

	private IQueenState myBoard;
	private int mySize;
	private int myCount;

	public Queens(int n) {
		mySize = n;
		myBoard = new QBoard(n);
		if (solve(0)) {
			myBoard.print();
		}
	}

	public boolean solve(int col) {

		if (col == mySize) {
			myCount++;
			return true;
		}

		// try each row until all are tried

		for (int r = 0; r < mySize; r++) {
			if (myBoard.safeToPlace(r, col)) {
				myBoard.setQueen(r, col, true);

				if (solve(col + 1)) {
					// return true;
				}
				myBoard.setQueen(r, col, false);
			}
		}
		return false;
	}

	public int getCount() {
		return myCount;
	}

	public int winningMoves(String[] grid) {
	    public QBoard(int n){
	        myBoardRows = new int[n];
	        Arrays.fill(myBoardRows,NONE);
	    }
	    
	    public boolean safeToPlace(int row, int col){
	        
	        // check each column to see if conflict exists
	        
	        for(int c=0; c < myBoardRows.length; c++){
	            if (myBoardRows[c] == row) return false;
	            
	            int diagDistance = col - c;
	            if (myBoardRows[c] == row - diagDistance) return false;
	            if (myBoardRows[c] == row + diagDistance) return false;
	        }
	        return true;
	    }
	    
	    public void setQueen(int row, int col, boolean value){
	        myBoardRows[col] = value ? row : NONE;
	    }
	}
}