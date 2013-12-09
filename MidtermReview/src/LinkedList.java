
public class LinkedList {

	private class Node {
		private int value;
		private Node next;
		
		private Node(int v, Node n) {
			value = v;
			next = n;
		}
	}
	
	private Node head;
	
	/**
	 * Constructs an empty linked list.
	 */
	public LinkedList() {
		head = null;
	}
	
	/**
	 * Adds an element to the end of the list.
	 */
	public void add(int v) {

	}
	
	/**
	 * Removes the first instance of the given value from the list.
	 */
	public void remove(int v) {
		
	}
	
	/**
	 * Removes every other element from the list.
	 * 
	 * So if our list is [1, 2, 3, 4] it becomes [1, 3]
	 * 
	 * If the list is empty do nothing.
	 */
	public void removeEveryOther() {
		
	}
	
	/**
	 * Moves the first k elements to the end of the list. If k exceeds the length
	 * of the list, the list should not change.
	 * 
	 * So if our list is [1, 2, 3, 4] and k is 3, the list becomes [4, 1, 2, 3]
	 * Note that every element has shifted back 3 positions.
	 */
	public void moveToEnd(int k) {
    	
	}
	
	/**
	 * Reverses the list.
	 */
	public void reverse() {
        
	}	
	
	/**
	 * Represents the list as a string.
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		Node current = head;
		while (current != null) {
			builder.append(current.value);
			if (current.next != null) {
				builder.append(" ");
			}
			current = current.next;
		}
		return builder.toString();
	}
	
}
