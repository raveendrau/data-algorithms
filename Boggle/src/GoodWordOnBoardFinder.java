import java.util.ArrayList;
import java.util.List;


public class GoodWordOnBoardFinder implements IWordOnBoardFinder{

	
	public List<BoardCell> cellsForWord(BoggleBoard board, String word) {
		List<BoardCell> list = new ArrayList<BoardCell>();
		for (int r = 0; r < board.size(); r++) {
			for (int c = 0; c < board.size(); c++) {
				if (helper(board, r, c, list, ...)) {
					
				}
			}
		}
	}

}
