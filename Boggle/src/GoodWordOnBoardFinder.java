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
		return new ArrayList<BoardCell>();
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
		
		// Be sure not to use a previously used board cell.
		if (list.contains(cell)) {
			return false;
		}
		
		// Get the cube face at the specified location
		String myFace = board.getFace(r, c);
	
		// Initialize the subsquent index
		int nextIndex = 0;
		
		
		/** 
		 * "Qu" is worth two characters.
		 * It can occur anywhere except at the end of the word.
		 * squid would score two points (for a five-letter word) despite 
		 * being formed from a chain of only four cubes.
		 */
		if (myFace.equals("qu")) {
			// If (the reduced word length is shorter
			if (index + 2 > word.length() - 1) {
				nextIndex = word.length() - 1;
			}
			// If the two-character bump is shorter
			else if (index + 2 < word.length() - 1) {
				nextIndex = index + 2;
			}
		}
		else {
			nextIndex = index + 1;
		}
		
		/**
		 * If the specified character in the string matches the character
		 * in the (row, column) board cell then up to eight recursive calls 
		 * will be made to find the next letter in the string 
		 * (changed parameter in recursive call) in a neighboring cell
		 * (changed parameter in recursive call).
		 */
		String characters = word.substring(index, nextIndex);
		
		if (myFace.equals(characters)) {
			list.add(cell);
			int[] rdelta = {-1,-1,-1, 0, 0, 1, 1, 1}; 
			int[] cdelta = {-1, 0, 1,-1, 1,-1, 0, 1}; 
			for(int k = 0; k < rdelta.length; k++){ 
			  if (helper(board, r + rdelta[k], c + cdelta[k],
					  list, index, word)) {
				  return true;  
			  }
			  // If the cell is useless
			  list.remove(cell);
			}
		}
		return false;
	}
}
