import java.util.HashMap;
import java.util.Map;


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
	
	/**
	 * The code below finds all occurrences of a restriction enzyme 
	 * like ÒgaattcÓ and splices in a new strand of DNA.
	 * The characters representing the enzyme are replaced by splicee.  
	 * @param splicee spliced-in replaces the enzyme. 
	 * @param enzyme is removed each time it occurs. 
	 * @return the new recombinant strand.  
	 */
	public IDnaStrand cutAndSplice(String enzyme, String splicee) {
		if (myHead.myNext != null){
	        throw new RuntimeException("link strand has more than one node"); 
	    }
		int pos = 0;
		int start = 0;
		String search = this.toString();
		boolean first = true;
		LinkStrand ret = null;
        /*
         * The next line is very syntax-dense. .indexOf looks for the 
         * first index at which enzyme occurs, starting at pos. Saying 
         * pos = ... assigns the result of that operation to the pos
         * variable; the value of pos is then compared against zero.
         * 
         * .indexOf returns -1 if enzyme can't be found. Therefore, this
         * line is:
         * 
         * "While I can find enzyme, assign the location where it occurs
         * to pos, and then execute the body of the loop."
         */
        while ((pos = search.indexOf(enzyme, pos)) >= 0) {
            if (first){
                ret = new LinkStrand(search.substring(start, pos));
                first = false;
            }
            else {
                 ret.append(search.substring(start, pos));
            }
            start = pos + enzyme.length();
            ret.append(splicee);
            pos++;
        }
        
        if (start < search.length()) {
        	// NOTE: This is an important special case! If the enzyme
        	// is never found, return an empty String.
        	if (ret == null){
        		ret = new LinkStrand("");
        	}
        	else {
        		ret.append(search.substring(start));
        	}
        }
        return ret;
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
		Map<String, String> map = new HashMap<String,String>();
		LinkStrand c = this;
		StringBuilder sb = new StringBuilder();
		String s = myHead.myValue;
		sb.append(s);
		sb.reverse();
		String ss = sb.toString();
		map.put(s, ss);
		LinkStrand ls = new LinkStrand(ss);
		c.myHead = c.myHead.myNext;
		while (ls.size() < size) {
			c.myHead = c.myHead.myNext;
			sb = new StringBuilder();
			String foo = c.myHead.myValue;
			
			if (map.containsKey(foo)) {
				String bar = map.get(foo);
				ls.append(bar);
			}
			else {
				sb.append(foo);
				sb.reverse();
				String bar = sb.toString();
				map.put(foo,bar);
				ls.append(bar);
			}
		}
		return ls;
	}

	public String getStats() {
		return String.format("# append calls = %d",myAppends);
	}

}
