public class FloodRelief {
	public char[][] myGrid;
	public int myRows, myCols;
	public int myMinRow, myMinCol;
	public int myMin;

	public int minimumPumps(String[] heights) {
		myRows = heights.length;
		myCols = heights[0].length();
		myGrid = new char[myRows][myCols]; 
		myMin = 1000;
		for (int r = 0; r < myRows; r++) {
			for (int c = 0; c < myCols; c++) {
				myGrid[r][c] = heights[r].charAt(c);
				if ((int)myGrid[r][c]<myMin){
					myMinRow = r;
					myMinCol = c;
					myMin = (int)myGrid[r][c];
				}
			}
		}
		return countRegion(myMinRow, myMinCol);
	}
	public void findMin(){
		int count = 0;
		for (int r = 0; r < myRows; r++) {
			for (int c = 0; c < myCols; c++) {
				if (myGrid[r][c]!=' '&& (int)myGrid[r][c]<myMin ){
					myMinRow = r;
					myMinCol = c;
					myMin = (int)myGrid[r][c];
					count ++;
				}
			}
		}
		if (count ==0) myMin = (int)' ';
	}

	
	public int countRegion(int row, int col) {
		if(myMin == 32) return 0;
		myGrid = erase(row, col, myGrid, myMin);
		myMin = 1000;
		findMin();
		return 1 + countRegion(myMinRow, myMinCol);
	}
	
	public char[][] erase(int row, int col, char[][] grid, int lastHeight){
		
		if (row < 0 || col < 0 || row >= myRows || col >= myCols){
			return myGrid;
		}
		else if((int)myGrid[row][col] >= lastHeight){
			int value = (int)myGrid[row][col];
			myGrid[row][col] = ' ';	
			grid=erase(row-1, col, grid, value);
			grid=erase(row+1, col, grid, value);
			grid=erase(row, col-1, grid, value);
			grid=erase(row, col+1, grid, value);
			
			
		}
		return grid;
	}
}