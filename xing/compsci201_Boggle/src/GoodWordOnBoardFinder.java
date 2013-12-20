import java.util.ArrayList;
import java.util.List;

public class GoodWordOnBoardFinder implements IWordOnBoardFinder {

	// private ArrayList<BoardCell> myEmptyList;
	private String givenWord;

	@Override
	public List<BoardCell> cellsForWord(BoggleBoard board, String word) {
		List<BoardCell> list = new ArrayList<BoardCell>();
		givenWord = word;
		for (int r = 0; r < board.size(); r++) {
			for (int c = 0; c < board.size(); c++) {
				if (wordHelper(board, r, c, list, 0)) {
					return list;
				}
			}
		}

		return new ArrayList<BoardCell>();
	}

	public boolean wordHelper(BoggleBoard board, int row, int col,
			List<BoardCell> list, int index) {

		int[] rdelta = { -1,-1,-1, 0, 0, 1, 1, 1 };
		int[] cdelta = { -1, 0, 1,-1, 1,-1, 0, 1 };
		
		if (index >= givenWord.length()) {
			return true;
		}
		if ((row >= board.size()) || (col >= board.size()) || (row < 0) || (col < 0)) {
			return false;
		}
		BoardCell current = new BoardCell(row, col);
		String content = board.getFace(row, col);
		if(content.equals("qu") && index+2 <= givenWord.length() && (givenWord.substring(index, index+2).equals(content)) && !list.contains(current)){
			list.add(current);
			for (int k = 0; k < rdelta.length; k++) {
				if (wordHelper(board, row + rdelta[k], col + cdelta[k], list,
						index + 2))
					return true;
			}
			list.remove(current);
		}
		if (("" + givenWord.charAt(index)).equals(content) && !list.contains(current)) {
			list.add(current);
			
			for (int k = 0; k < rdelta.length; k++) {
				if (wordHelper(board, row + rdelta[k], col + cdelta[k], list,
						index + 1))
					return true;
			}
			list.remove(current);
		}
		return false;

	}
	

}
