/*
 * Code for a single Node in an LinkedIntList
 * which is a Linked List of ints.
 * 
 * All methods should be completed, and no alterations should be
 * necessary
 * 
 * NOTE: value and next are private, that means to access or 
 * change them you will need to use the getter/setter methods
 * provided here
 * 
 * @author Ben Stoddard
 */

public class IntListNode {
  private int value;
  private IntListNode next;
  
  IntListNode(int value){
    this.value = value;
  }
  
  IntListNode(int value, IntListNode next) {
    this.value = value;
    this.next = next;
  }
  
  public IntListNode getNext(){
    return this.next;
  }
  
  public void setNext(IntListNode next){
    this.next = next;
  }

  public int getValue() {
    return this.value;
  }
  
  public String toString() {
    return this.value + "";
  }

}