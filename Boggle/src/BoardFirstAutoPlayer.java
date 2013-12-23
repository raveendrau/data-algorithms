mport java.util.ArrayList;



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
				makeWordFrom(r, c, new StringBuilder());
				myList.clear();
			}
		}
    }
    

}
