import java.util.*;

public class BoardFirstAutoPlayer extends AbstractAutoPlayer {

	private BoggleBoard givenBoard;
	private ILexicon givenlex;
	private List<BoardCell> list;
	private int givenMinLength;

	@Override
	public void findAllValidWords(BoggleBoard board, ILexicon lex, int minLength) {
		clear();
		givenBoard = board;
		givenlex = lex;
		givenMinLength = minLength;
		list = new ArrayList<BoardCell>();
		for (int r = 0; r < board.size(); r++) {
			for (int c = 0; c < board.size(); c++) {
				wordsStartWith(r, c, new StringBuilder());
				list.clear();
				
			}
		}

	}

	public boolean wordsStartWith(int row, int col, StringBuilder s) {

//		if (givenlex.wordStatus(s)==LexStatus.WORD && s.length()>=givenMinLength) {
//			add(s.toString());
//			return true;
//		}
//		if ((row >= givenBoard.size()) || (col >= givenBoard.size())
//				|| (row < 0) || (col < 0)) {
//			return false;
//		}
//		BoardCell current = new BoardCell(row, col);
//		String cellContent = givenBoard.getFace(row, col);
//
//		if (!list.contains(current)) {
//			list.add(current);
//			s.append(cellContent);
//			if (givenlex.wordStatus(s)==LexStatus.WORD && s.length()>=givenMinLength) {
//				if(add(s.toString())) System.out.println(s.toString());
////				add(s.toString());
//			}
//
//			if (givenlex.wordStatus(s)==LexStatus.PREFIX) {
////				System.out.println(givenlex.wordStatus(s) + "\t" + s.toString());
//
//				int[] rdelta = { -1, -1, -1, 0, 0, 1, 1, 1 };
//				int[] cdelta = { -1, 0, 1, -1, 1, -1, 0, 1 };
//				for (int k = 0; k < rdelta.length; k++) {
//					if (wordsStartWith(row + rdelta[k], col + cdelta[k], s)){
//						return true;
//					}
//				}
//			}
//			list.remove(current);
//			s.deleteCharAt(s.length() - 1);
//			
//			return false;
//		}
//		return false;
		
		if ((row >= givenBoard.size()) || (col >= givenBoard.size())
				|| (row < 0) || (col < 0)) {
			return false;
		}
		
		if (givenlex.wordStatus(s)==LexStatus.WORD && s.length()>=givenMinLength){
			add(s.toString());
//			return true;
		}
		BoardCell current = new BoardCell(row, col);
		String content = givenBoard.getFace(row, col);
		if(!list.contains(current)){
			s.append(content);
			list.add(current);
//			System.out.println(s.toString());

			if(givenlex.wordStatus(s)==LexStatus.PREFIX || givenlex.wordStatus(s)==LexStatus.WORD){
				int[] rdelta = { -1, -1, -1, 0, 0, 1, 1, 1 };
				int[] cdelta = { -1, 0, 1, -1, 1, -1, 0, 1 };
				
				for (int k = 0; k < rdelta.length; k++) {
					if (wordsStartWith(row + rdelta[k], col + cdelta[k], s)){
						return true;
					}
					
					
				}
				s.deleteCharAt(s.length() - 1);
				list.remove(current);
			}else{
				s.deleteCharAt(s.length() - 1);
				list.remove(current);
			}
				
			
			

		}else{
			return false;
		}
			
		
		
		return false;

	}
}
