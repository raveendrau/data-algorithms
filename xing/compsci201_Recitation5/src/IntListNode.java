
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
