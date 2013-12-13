
public class LinkStrand implements IDnaStrand {
	private class Node {
		String myValue;
		Node myNext;
		
		Node(String value, Node next) {
			myValue = value;
			myNext = next;
		}
	}
	
	private String myInfo;
	private int myAppends; // track number of appends
	private Node myHead, myTail; // track first and last nodes
	private long size; // store the size of DNA strand 
	
	/**
	 * Create a strand representing an empty DNA strand.
	 */
	public LinkStrand() {
		this("");
	}
	
	/**
	 * Create a strand representing s.
	 */
	public LinkStrand(String s) {
		Node current = myHead;
		while(current.myNext != null) {
			current = current.myNext;
		}
		current.myNext = new Node(s, null);
		size = size + s.length();
	}
	
	public IDnaStrand cutAndSplice(String enzyme, String splicee) {
		// TODO Auto-generated method stub
		return null;
	}

    /**
     * Returns the number of nucleotides/base-pairs in this strand.
     */
	public long size() {
		return size;
	}

	public void initializeFrom(String source) {
		myHead = new Node(source, myHead);
		size = source.length();
	}

	public String strandInfo() {
		return this.getClass().getName();
	}

	public IDnaStrand append(IDnaStrand dna) {
		if (dna instanceof LinkStrand) {
			LinkStrand ls = (LinkStrand) dna;
		
		}
		return null;
	}

	@Override
	public IDnaStrand append(String dna) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDnaStrand reverse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStats() {
		// TODO Auto-generated method stub
		return null;
	}

}
