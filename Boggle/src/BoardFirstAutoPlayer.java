import java.util.List;
import java.util.ArrayList;



public class BoardFirstAutoPlayer extends AbstractAutoPlayer {
    
	private BoggleBoard myBoard;
	private ILexicon myLex;
	private List<BoardCell> myList;
	private int myMinLength;
	
    @Override
    public void findAllValidWords(BoggleBoard board, ILexicon lex, int minLength) {
	    clear();
	    myBoard = board;
	    myLex = lex;
	    myMinLength = minLength;
	    myList = new ArrayList<BoardCell>();
	    /**
	     * You’ll write a recursive helper method for this class to 
	     * find all the words starting at a specified [row,column]. 
	     * The basic idea is to pass to this helper the following: 
	     * 1. The current row, column
	     * 2. The string built from the search so far 
	     * 		(you may want to use a StringBuilder)
	     */
	    for (int r = 0; r < board.size(); r++) {
			for (int c = 0; c < board.size(); c++) {
				/**
				 * When first called, the string built 
				 * from the search so far is the empty string: “”.
				 */
				makeWordFrom(r, c, new StringBuilder());
				myList.clear();
			}
		}
    }
    
    /**
     * As with all flood-fill/backtracking code you must make sure 
     * your code doesn’t re-use a board-cell/cube once it has been 
     * used in the current search. This means that each board-cell/cube 
     * that contributed to the string built from the search so far 
     * can’t be re-used in extending the string. But the cell/cube can be 
     * re-used when searching for different strings/starting from or 
     * continuing from different cubes. 
     */
    public boolean makeWordFrom(int r, int c, StringBuilder s) {
    	// Check that the bounds of the board are not exceeded
    	if (r < 0 || r >= myBoard.size()|| 
    			c < 0 || c >= myBoard.size()) {
				return false;
		}
    	
    	/**
    	 * If the string is a word, the word is added to the collection 
    	 * of found words by calling the inherited add(..) method. 
    	 * (See the code in AbstractPlayer 
    	 * for how the words found are stored via this method.)
    	 */
    	if (myLex.wordStatus(s) == LexStatus.WORD 
    			&& s.length() >= myMinLength) {
			add(s.toString());
		}
    	
    	
    	/**
    	 * You can use in instance variable/field to store the 
    	 * BoardCell objects used in the current word being formed, 
    	 * but other approaches work as well (e.g., using a parameter) — 
    	 * note that BoardCell implements Comparable.
    	 */
    	BoardCell cell = new BoardCell(r, c);
    	String myFace = myBoard.getFace(r, c);
    	
    	/**
    	 * The current cube/cell on the board, 
    	 * if legal and not used in the search so far, 
    	 * is added to the end of the string built so far. 
    	 */
    	if (!myList.contains(cell)) {
			s.append(myFace);
			myList.add(cell);
			
			/**
			 * If the string is either a word or the prefix of a word 
			 * in the lexicon then the search is continued by calling 
			 * the helper method for each adjacent cube 
			 * with the string built so far. 
			 */
			if (myLex.wordStatus(s) == LexStatus.PREFIX ||
					myLex.wordStatus(s) == LexStatus.WORD) {
					int[] rdelta = { -1, -1, -1, 0, 0, 1, 1, 1 };
					int[] cdelta = { -1, 0, 1, -1, 1, -1, 0, 1 };
					
					for (int k = 0; k < cdelta.length; k++) {
						if (makeWordFrom(r + rdelta[k], 
								c + cdelta[k], s)) {
							return true;
						}
					}
				s.deleteCharAt(s.length() - 1);
				myList.remove(cell);
			}
			
			/**
			 * If the string is not a prefix (or a word) 
			 * then the search is cut-off at this point 
			 * and the recursion will unwind/backtrack 
			 * (essentially to the point where 
			 * the last possible word/prefix was formed).
			 */
			else {
				s.deleteCharAt(s.length() - 1);
				myList.remove(cell);
			}
		}
    	return false;
    }
}
