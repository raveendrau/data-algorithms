public class LinkedIntListSolved {
  private IntListNode head;
  private int size;
  
  LinkedIntListSolved(){
    this.head = null;
    size = 0;
  }
  
  // returns the node at the head
  public IntListNode head(){
    return this.head;
  }
  
  // returns the size of the list
  public int size() {
	  return this.size;
  }
  
  // gets first value *without* removing it
  public int getFirst() {
	  if(this.head == null ) {
		  // lets just assume we never get into this case.
		  System.out.println("This method should not be called when the list is empty");
		  return -1;
	  }
	  return head.getValue();
  }
  
  // removes the first item from the list
  public int removeFirst() {
	  if(this.head == null) {
		  // lets just assume we never get into this case.
		  System.out.println("This method should not be called when the list is empty");
		  return -1;
	  }
	  size--;
	  
	  // get the value in head, and then move pointer
	  int value = head.getValue();
	  this.head = head.getNext();
	  return value;
  }
  
  // removes the last item from the list.
  public void removeLast() {
	  if(this.head == null) {
		  // nothing to do here
		  return;
	  } 
	  
	  // check if we should remove the node after the head.
	  boolean removeNode = removeLast(head);
	  
	  if(removeNode) {
		  // I guess we should remove it.
		  this.head = null;
	  }
	  
	  // decrement the size
	  size--;
  }
  
  // Helper method. Returns true if this is the node that should be removed.
  // Returns false in all other cases.
  private boolean removeLast(IntListNode n) {
	  if(n.getNext() == null) {
		  // nothing after me, return to true to indicate I should be removed
		  return true;
	  }
	  
	  // see if we should remove the next.
	  boolean removeNext = removeLast(n.getNext());
	  if(removeNext) {
		  // we should... so remove it
		  n.setNext(null);
	  }
	  
	  // don't remove us though.
	  return false;
  }
  
  // non-recursive method for adding to the head of a list
  public void addToHead(int value) {
	  if(head == null) {
		  head = new IntListNode(value);
	  } else {
		  head = new IntListNode(value, head);
	  }
	  
	  size++;
  }
  
  
  // adds a node with value == value at the tail of the list
  public void addToTail(int value){
    if(head == null) {
    	// base case: head is null
    	head = new IntListNode(value);
    } else {
    	// head isn't null, need to find the end
    	addToTail(value, head);
    }
    // increment the size
    size++;
  }
  
  private void addToTail(int value, IntListNode n) {
	  if(n.getNext() == null) {
		  // base case, nothing after us
		  n.setNext(new IntListNode(value));
	  } else {
		  // not the last node, keep recursing
		  addToTail(value, n.getNext());
	  }
  }
  
  //returns true if a node in the list has has a value of value
  // otherwise returns false
  public boolean contains(int value){
	  // make the recursive call
	  return contains(value, head);
  }
  
  private boolean contains(int value, IntListNode n) {
	  if(n == null) {
		  // base case, didn't find the value
		  return false;
	  } else if(value == n.getValue()) {
		  // base case, we found the value
		  return true;
	  }
	  
	  // keep looking
	  return contains(value, n.getNext());
  }
  
  // want to count the number of times value is in the list
  public int countOccurences(int value) {
	  return countOccurences(value, head); // call the helper
  }
  
  private int countOccurences(int value, IntListNode n) {
	  if(n == null) {
		  return 0; // base case, cant contain anything
	  }
	  
	  int thisNodeCount = 0;
	  if(n.getValue() == value) {
		  thisNodeCount = 1; // we matched at this node!
	  }
	  
	  // add our sum to whatever came from below
	  return thisNodeCount + countOccurences(value, n.getNext());
  }
  
  // returns the sum of the even entries in the list
  public int sumEven() {
	  return sumEven(head);
  }
  
  // private sumEven helper
  private int sumEven(IntListNode n) {
	  if(n == null) {
		  return 0;
	  }
	  
	  int val = n.getValue();
	  
	  if(val % 2 == 1) {
		  val = 0;
	  }
	  
	  return val + sumEven(n.getNext());
	  
  }
  
  // returns a reversed version of this list.
  public LinkedIntListSolved reverseList() {
	  LinkedIntListSolved newList = new LinkedIntListSolved();
	  reverseList(newList, head);
	  
	  return newList;
  }
  
  // private helper function for reversing.
  //
  // Reverse into newList one recursive call at a time and return it
  private void reverseList(LinkedIntListSolved newList, IntListNode n) {
	  if(n == null) {
		  // base case: nothing left to do, return
		  return; 
	  }
	  
	  newList.addToHead(n.getValue()); // add the current node to the new list
	  reverseList(newList, n.getNext()); // move to the next node
  }
  
  // This method assumes that both this list, and other list are sorted.
  // ... it will not work if they are not sorted.
  //
  // It should return a new list, which is the sorted combination of 
  // both this list, and the given argument.
  public LinkedIntListSolved mergeLists(LinkedIntListSolved other) {
	  LinkedIntListSolved newList = new LinkedIntListSolved();
	  mergeLists(this, other, newList);
	  
	  return newList;
  }
  
  // private helper for merging lists
  //
  // Think about your base cases... there should (or could) be three.
  private void mergeLists(LinkedIntListSolved one, LinkedIntListSolved two, 
		  LinkedIntListSolved result) {
	  if(one.size() == 0 && two.size() == 0) {
		  return; // both are empty, nothing to do.
	  } else if(one.size() == 0) {
		  // one is empty, two is not
		  result.addToTail(two.removeFirst());
	  } else if(two.size() == 0) {
		  // two is empty, one is not
		  result.addToTail(one.removeFirst());
	  } else {
		  // neither is empty.. have to choose one
		  int valOne = one.getFirst();
		  int valTwo = two.getFirst();
		  if(valOne < valTwo) {
			  result.addToTail(valOne); // add valOne, it comes first
			  one.removeFirst(); // still have to get rid of it.
		  } else {
			  result.addToTail(valTwo); // add valOne, it comes first
			  two.removeFirst(); // still have to get rid of it.
		  }
	  }
	  
	  // keep recursing until we hit the base case
	  mergeLists(one, two, result);
  }
  
  // returns the sum of the list
  public int sum() {
	  return sum(head);
  }
  
  // private sum helper
  private int sum(IntListNode n) {
	  if(n == null) {
		  return 0;
	  }
	  return n.getValue() + sum(n.getNext());
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    if(head != null) {
    	addToString(head, sb);
    }
    sb.append("]");
    
    return sb.toString();
  }
  
  private void addToString(IntListNode n, StringBuilder sb) {
	  // n isn't null, so we can keep adding stuff
	  if(n != null) {
		  sb.append(n.toString());
		  if(n.getNext() != null) {
			  sb.append(",");
		  }
		  addToString(n.getNext(), sb); // keep going deeper
	  }
  }

}