import java.util.HashMap;

public class LinkStrand implements IDnaStrand {

	public class Node {
		String info;
		Node next;

		Node(String s) {
			info = s;
			next = null;
		}

		@Override
		public String toString() {
			if (next != null) {
				return info + next.toString();
			} else
				return info;
		}

	}

	private Node myFirst, myLast; // first and last nodes of list
	private long mySize; // # nucleotides in DNA
	private int appends; // # appends called

	public LinkStrand() {
		this("");
	}

	public LinkStrand(String s) {
		myFirst = new Node(s);
		myLast = myFirst;
		mySize = s.length();
		appends = 0;
	}

	public LinkStrand(Node n) {
		myFirst = n;
		while (n.next != null) {
			mySize += n.info.length();
			n = n.next;
		}
		myLast = n;
	}

	@Override
	public IDnaStrand cutAndSplice(String enzyme, String splicee) {
		int pos = 0;
		int start = 0;
		if (myFirst.next != null) {
			throw new RuntimeException("link strand has more than one node");
		}
		String search = myFirst.info;
		boolean first = true;
		LinkStrand ret = null;

		while ((pos = search.indexOf(enzyme, pos)) >= 0) {
			if (first) {
				ret = new LinkStrand(search.substring(start, pos));
				first = false;
			} else {
				ret.append(search.substring(start, pos));

			}
			start = pos + enzyme.length();
			ret.append(splicee);
			pos++;
		}

		if (start < search.length()) {

			if (ret == null) {
				ret = new LinkStrand("");
			} else {
				ret.append(search.substring(start));
			}
		}
		return ret;

	}

	@Override
	public long size() {
		return mySize;
	}

	@Override
	public void initializeFrom(String source) {
		myFirst = new Node(source);
		myLast = myFirst;
		mySize = source.length();
		appends = 0;
	}

	@Override
	public String strandInfo() {
		return this.getClass().getName();
	}

	@Override
	public IDnaStrand append(IDnaStrand dna) {
		if (dna instanceof LinkStrand) {
			LinkStrand ls = (LinkStrand) dna;
			myLast.next = ls.myFirst;
			myLast = ls.myLast;
			mySize += ls.size();
			appends++;
			return this;
		} else {
			return append(dna.toString());
		}
	}

	@Override
	public IDnaStrand append(String dna) {
		Node myNext = new Node(dna);
		myLast.next = myNext;
		myLast = myNext;
		mySize += dna.length();
		appends++;
		return this;
	}

	@Override
	public IDnaStrand reverse() {

		// StringBuilder copy = new StringBuilder(this.toString());
		// return new LinkStrand(copy.reverse().toString());

		LinkStrand ls = (LinkStrand) this.clone();
		Node current = ls.myFirst;
		Node next = current.next;
		String reverse;

		if (current.next == null) {
			return new LinkStrand(new StringBuilder(this.toString()).reverse()
					.toString());
		}
		HashMap<String, String> map = new HashMap<String, String>();

		while (current != null && next != null) {
			String info = current.info;
			if (map.containsKey(info))
				current.info = map.get(info);
			else {
				reverse = new StringBuilder(current.info).reverse().toString();
				map.put(info, reverse);
				current.info = reverse;
			}
			Node temp = next.next;
			next.next = current;
			current = next;
			next = temp;
		}

		ls.myFirst.next = null;

		Node temp = ls.myLast;
		ls.myLast = ls.myFirst;
		ls.myFirst = temp;

		return ls;
	}

	@Override
	public String getStats() {
		return String.format("# of breaks = %d", appends / 2); // # breaks = #
																// appends/2
	}

	@Override
	public String toString() {
		return myFirst.toString();
	}

	public IDnaStrand clone() {
		Node current = myFirst;
		LinkStrand ls = new LinkStrand(myFirst.info);
		while ((current = current.next) != null) {
			ls.append(current.info);
		}

		return ls;
	}

}
