import java.util.*;

public class BinarySearchLexicon implements ILexicon {

	private ArrayList<String> myWords;

	public BinarySearchLexicon() {
		myWords = new ArrayList<String>();
	}

	public void load(Scanner s) {
		myWords.clear();
		while (s.hasNext()) {
			myWords.add(s.next().toLowerCase());
		}
		Collections.sort(myWords);
	}

	public void load(ArrayList<String> list) {
		myWords.clear();
		myWords.addAll(list);
		Collections.sort(myWords);
	}

	public LexStatus wordStatus(StringBuilder s) {
		return wordStatus(s.toString());
	}

	public LexStatus wordStatus(String s) {

		int index = Collections.binarySearch(myWords, s);
		int insertionIndex = -1 - index;
		if (index >= 0) {
			return LexStatus.WORD;
		} else if (insertionIndex < myWords.size()
				&& myWords.get(insertionIndex).startsWith(s)) {
			return LexStatus.PREFIX;
		}
		return LexStatus.NOT_WORD;
	}

	public Iterator<String> iterator() {
		return myWords.iterator();
	}

	public int size() {
		return myWords.size();
	}

}
