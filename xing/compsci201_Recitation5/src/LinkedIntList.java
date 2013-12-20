
public class LinkedIntList {
  private IntListNode head;
  private IntListNode tail;
  private int size;
  
  LinkedIntList(){
    this.head = null;
    this.tail = null;
    this.size = 0;
  }
  
  // returns the node at the head
  public IntListNode head(){
    return this.head;
  }
  
  // returns the node at the tail
  public IntListNode tail(){
    return this.tail;
  }
  
  // returns the size of the list
  public int size(){
    return this.size;
  }
  
  
  // adds a node with value == value at the tail of the list
  public void addToTail(int value){
    
  }
  
  
  // adds a node with value == value at the head of the list
  public void addToHead(int value){
    
  }
  
  
  // adds a node with value == value at the tail of the list
  public void addToPosition(int value, int index) {

  }
  
  // returns true if a node in the list has has a value of value
  // otherwise returns false
  public boolean contains(int value){
    return false;
  }
  
  // Should return first index that value appears at
  // if value is not in the list, returns -1
  public int indexOf(int value){
    return 0;
  }
  
  // removes the first appearance of this value from the list
  public void delete(int value) {
    
  }
  
  // swap the nodes at these indices
  // return true when done
  // return false if they cannot be swapped for any reason
  public boolean swap(int indexOne, int indexTwo) {
    
    return false;
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder();
    IntListNode trace = head;
    sb.append("[");
    while(trace != null) {
      sb.append(trace.toString());
      sb.append(",");
      trace = trace.getNext();
    }
    if(this.size() > 0){
      sb.deleteCharAt(sb.length() - 1);
    }
    sb.append("]");
    
    return sb.toString();
  }
  
  public static void main(String[] args) {
    LinkedIntList ll = new LinkedIntList();
    System.out.println(ll); //  should be empty
    ll.addToHead(1);
    System.out.println(ll); //  should be [1]
    ll.addToHead(0);
    System.out.println(ll); //  should be [0,1]
    ll.addToTail(3);
    System.out.println(ll); //  should be [0,1,3]
    ll.addToPosition(2,2);
    System.out.println(ll); //  should be [0,1,2,3]
    System.out.println("Checking some methods:");
    System.out.println(ll.contains(1)); // should be true
    System.out.println(ll.contains(4)); // should be false
    System.out.println(ll.indexOf(1)); // should be 1
    System.out.println(ll.indexOf(4)); // should be -1
    System.out.println("Checking delete:");
    System.out.println(ll); // should be [0,1,2,3]
    ll.delete(1); 
    System.out.println(ll); // should be [0,2,3]
  }

}
