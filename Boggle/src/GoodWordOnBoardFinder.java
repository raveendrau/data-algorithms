import java.util.ArrayList;
import java.util.List;


public class GoodWordOnBoardFinder implements IWordOnBoardFinder{

	
	public List<BoardCell> cellsForWord(BoggleBoard board, String word) {
		List<BoardCell> list = new ArrayList<BoardCell>();
		for (int r = 0; r < board.size(); r++) {
			for (int c = 0; c < board.size(); c++) {
				int index = 0;
				if (helper(board, r, c, list, index, word)) {
					return list;
				}
			}
		}
	}
	
	public boolean helper(BoggleBoard board, int r, int c, 
			List<BoardCell> list, int index, String word) {
		/**
		 * if the index of the string is too large,
		 * the word has ben found since no more letters
		 * are left to find on the board
		 */
		if (index > word.length() - 1) {
			return true;
		}
		
		/**
		 * if the row and column are out of bounds,
		 * don't continue the search 
		 */
		if (r < 0 || r >= board.size() ||
				c < 0 || c > board.size()) {
			return false;
		}
		
		BoardCell cell = new BoardCell(r, c);
		
		/**
		 * The helper method also maintains some record of the board cells
		 * that have been matched so far Ñ this record is maintained in a 
		 * paremeter that is passed in each recursive calls. 
		 * 
		 * This record, which is likely a list or a set, serves two purposes:
		 * 
		 * 1. It will ultimately be be used to return something by the method
		 * cellsForWord if the word is found on the board by the helper method.
		 * 
		 * 2. The record will also help you write code to ensure the same board
		 * cell isn't used more than once in finding the word being searched
		 * for (e.g., you can use the method .contains to see if a BoardCell
		 * has been used before). 
		 */
		if (list.contains(cell)) {
			return false;
		}
		
		String myCurrent = board.getFace(r, c);
	}
}
