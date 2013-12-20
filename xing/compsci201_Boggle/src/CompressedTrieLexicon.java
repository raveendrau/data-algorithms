import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CompressedTrieLexicon extends TrieLexicon {

	@Override
	public void load(ArrayList<String> list) {
		super.load(list);
		compress();
	}

	public void compress() {
		compressHelper(myRoot);
	}

	private void compressHelper(Node n) {

		Node p = n.parent;
		for (Node child : n.children.values()) {
			compressHelper(child);
		}
		if (n.children.size() == 0) {
			if (p != myRoot && !p.isWord && p.children.size() == 1) {


				p.info += n.info;
				p.isWord = n.isWord;
				n.parent = null;
				p.children = new HashMap<Character, Node>();
			}
		} 

	}
	
	@Override
	public LexStatus wordStatus(StringBuilder s) {
		Node t = myRoot;
		for (int k = 0; k < s.length(); k++) {
			char ch = s.charAt(k);
			if (t.info.length() > 1) {
				String rest = s.substring(k-1);
				if (t.info.length() == rest.length()) {
					return rest.equals(t.info) ? LexStatus.WORD : LexStatus.NOT_WORD;
				} else if (t.info.length() < rest.length()) {
					return LexStatus.NOT_WORD;
				} else {
					String chunk = t.info.substring(0, rest.length());
					return rest.equals(chunk) ? LexStatus.PREFIX : LexStatus.NOT_WORD;
				}
				
			} else {
				t = t.children.get(ch);
				if (t == null)
					return LexStatus.NOT_WORD; // no path below? done
			}

		}
		return t.isWord ? LexStatus.WORD : LexStatus.PREFIX;
	}

}
