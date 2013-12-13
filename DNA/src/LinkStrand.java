
public class LinkStrand implements IDnaStrand {
		 
	// declaring instance variables
	private String myInfo; 
	private int myAppends; // track number of appends
	private Node myHead, myTail; // track first and last nodes
	private long size; // store the size of DNA strand 
	
	/**
	 * Create a strand representing an empty DNA strand, length of 0.
	 * @param none
	 * @return none
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
     * @return none
     */
	public LinkStrand(String s) {
		myHead = new Node(s);
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
