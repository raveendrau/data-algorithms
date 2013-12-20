public class StringStack {
	Node myFront;
	int mySize;
	
	public class Node{
		Node myNext;
		String myData;
		
		public Node(String data, Node next){
			myNext = next;
			myData = data;
		}
	}
	
	public StringStack(){
		myFront = null;
		mySize = 0;
	}

	public void push(String data){
		Node newFront = new Node(data, myFront);
		myFront = newFront;
		mySize ++;
	}

	public String pop(){ 		//returns and removes element
		String data = myFront.myData;
		myFront = myFront.myNext;
		mySize--;
		return data;
	}

	public boolean isEmpty(){
		return mySize == 0;
	}
}
