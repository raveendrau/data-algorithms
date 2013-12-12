
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
	private int myAppends;
	private Node myHead;
	private long size;
	
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
		myInfo = new String(s);
		Node current = myHead;
		while(current.myNext != null) {
			current = current.myNext;
		}
		current.myNext = new Node(myInfo, null);
	}
	
	public IDnaStrand cutAndSplice(String enzyme, String splicee) {
		// TODO Auto-generated method stub
		return null;
	}

	public long size() {
		return size;
	}

	public void initializeFrom(String source) {
		myHead = new Node(source, myHead);
		size = source.length();
	}

	public String strandInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDnaStrand append(IDnaStrand dna) {
		// TODO Auto-generated method stub
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
