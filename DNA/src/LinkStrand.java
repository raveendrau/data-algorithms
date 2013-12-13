
public class LinkStrand implements IDnaStrand {
	
	// Node class for list
	public class Node {
		String myValue;
		Node myNext;
		Node(String value) {
			myValue = value;
			myNext = null;
		}
	}
	 
	// declaring instance variables
	private StringBuilder myInfo; 
	private int myAppends; // track number of appends
	private Node myHead, myTail; // track first and last nodes
	private long size; // store the size of DNA strand 
	
	/**
	 * Create a strand representing an empty DNA strand, length of 0.
	 */
	public LinkStrand() {
    	// Syntactic trick: calls the other constructor (the one that
    	// takes a String) with an empty String.
		this("");
	}
	
	/**
     * Create a strand representing s. No error checking is done to 
     * see if s represents valid genomic/DNA data.
     * @param s is the source of cgat data for this strand
     */
	public LinkStrand(String s) {
		myHead = new Node(s);
		myTail = myHead;
		size = s.length();
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

	public String toString() {
		myInfo = new StringBuilder();
		Node myNode = myHead;
		myInfo.append(myNode.myValue);
		while (myNode.myNext != null) {
			myNode = myNode.myNext;
			myInfo.append(myNode.myValue);
		}
		String foo = myInfo.toString();
		return foo;
	}
	
    /**
     * Initialize this strand so that it represents the value of source.
     * No error checking is performed.
     * @param source is the source of this enzyme
     */
	public void initializeFrom(String source) {
		myHead = new Node(source);
		myTail = myHead;
		size = source.length();
	}

	public String strandInfo() {
		return this.getClass().getName();
	}

    /**
     * Append a strand of DNA to this strand. If the strand being appended
     * is represented by a SimpleStrand object then an efficient append is
     * performed, otherwise the append is inefficient (even though it 
     * doesn't have to be).
     * @param dna is the strand being appended
     */
	public IDnaStrand append(IDnaStrand dna) {
		if (dna instanceof LinkStrand) {
			LinkStrand myStrand = (LinkStrand) dna;
			myTail.myNext = myStrand.myHead;
			myTail = myStrand.myTail;
			size = size + myStrand.size();
			myAppends++;
			return this;
		}
		else {
			return append(dna.toString());
		}
	}

    /**
     * Simply append a strand of dna data to this strand. No error 
     * checking is done. 
     * @param dna is the String appended to this strand
     */
	public IDnaStrand append(String dna) {
		Node myNode = new Node(dna);
		myTail.myNext = myNode;
		myTail = myNode;
		size += dna.length();
		myAppends++;
		return this;
	}

	
	public IDnaStrand reverse() {
		LinkStrand clone = this;
		StringBuilder sb = new StringBuilder();
		sb.append(myHead.myValue);
		sb.reverse();
		LinkStrand ls = new LinkStrand(sb.toString());
		clone.myHead = clone.myHead.myNext;
		while (ls.size() < size) {
			Node foo = clone.myHead;
			clone.myHead = clone.myHead.myNext;
			foo.myNext = null;
			sb = new StringBuilder();
			sb.append(foo.myValue);
			sb.reverse();
			ls.append(sb.toString());
		}
		return ls;
	}

	public String getStats() {
		return String.format("# append calls = %d",myAppends);
	}

}
