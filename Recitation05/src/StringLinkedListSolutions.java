public class StringLinkedListSolutions extends StringLinkedList {

    private class Node {
        String myValue;
        Node myNext;
        
        Node(String value, Node next)
        {
            myValue = value;
            myNext = next;
        }
    }
    
    //you should not need any more instance variables
    private Node myHead;
    
    //BE SURE YOU TEST THIS CODE WORKS WITH THE StringLinkedListTester
    //BEFORE YOU SUBMIT!
    
    /**
     * Adds the given value to the end of the list.
     */
    public void addAtEnd(String valueToAdd)
    {
    	if(myHead == null){
    		addAtBeginning(valueToAdd);
    	}
    	else{
    		Node current = myHead;
    		while(current.myNext != null){
    			current = current.myNext;
    		}
    		current.myNext = new Node(valueToAdd, null);
    	}
    }
    
    /**
     * Adds the given value to the beginning of the list.
     */
    public void addAtBeginning(String valueToAdd)
    {
        myHead = new Node(valueToAdd, myHead);
    }
    
    /** Removes the longest string from the list.
     * So if you have the list [a,b,longstring,z,q]
     * after this function runs you end up with [a,b,z,q]
     * If more than one string has the same longest length, remove the
     * first one.
     * If the list is empty, do nothing
     * If the list has only 1 element, remove it
     */
    public void removeLongestString()
    {
    	// if list is empty, return
    	if (myHead == null) {
    		return;
    	}
    	
    	// pointer to current node we're checking
    	Node current = myHead;
    	
    	// pointer to node right before current longest
    	Node beforeLongest = null;
    	
    	// store the current longest value
    	String longest = myHead.myValue;
    	
    	// iterate over list
    	while (current.myNext != null) {
    		
    		// if next of current node is longer than current longest value, update pointers
    		if (longest.length() < current.myNext.myValue.length()) {
    			beforeLongest = current;
    			longest = current.myNext.myValue;
    		}
    		
    		// move to next element in list
    		current = current.myNext;
    	}
    	
    	// if beforeLongest is null, that means we never found a Node with value longer than the
    	// initial value of longest, which was the value of the head, so head is longest
    	if (beforeLongest == null) {
    		myHead = myHead.myNext;
    	}
    	// change the next pointer of the node before the longest to skip the longest node
    	else {
    		beforeLongest.myNext = beforeLongest.myNext.myNext;
    	}
    }
    
    /**
     * Modify the list so that every element occurs twice. For example, [a, b, c] becomes
     * [a, a, b, b, c, c].
     */
    public void doubleList()
    {
    	// create current node pointer
        Node current = myHead;
        
        // iterate over list
        while (current != null) {
        	
        	// create a new node with same value and next as current, then change current's next to
        	// point to the new node
        	Node newNode = new Node(current.myValue, current.myNext);
        	current.myNext = newNode;
        	
        	// skip over node we just added so we don't duplicate indefinitely
        	current = current.myNext.myNext;
        }
    }
    
    /**
     * Move the first k elements of the list to the end of the list. Assume that k is not greater than
     * the size of the list.
     */
    public void moveToEnd(int k)
    {
    	// if list is empty, return
    	if (myHead == null) {
    		return;
    	}
    	
    	// create current pointer
    	Node current = myHead;
    	
    	// iterate to end of list
    	while (current.myNext != null) {
    		current = current.myNext;
    	}
    	
    	// set next of last node in list to the head--this creates a circular list!!
    	current.myNext = myHead;
    	
    	// skip ahead k nodes--this makes current point to the new end of the list
    	for (int i=0; i<k; i++) {
    		current = current.myNext;
    	}
    	
    	// set head to be the node after current, then set current's next to null, thus
    	// making current the new end of the list
    	myHead = current.myNext;
    	current.myNext = null;
    }
    
    /**
     * Reverse the list. This one is tricky!
     */
    public void reverse()
    {
    	// if list is empty or only one element, return
        if (myHead == null || myHead.myNext == null) {
            return;
        }
        
        // create two current node pointers, first will be the first current node, second the second
        Node firstNode = myHead;
        Node secondNode = myHead.myNext;
        
        // first points to the head of the list, which after reversing becomes the last node in the list,
        // so we update its next to point to null
        firstNode.myNext = null;
        
        // iterate over the list until the second current node pointer is null
        while (secondNode != null) {
        	
        	// create a temporary reference to the node after second
        	Node temp = secondNode.myNext;
        	
        	// modify second node so that its next points to the first node
        	secondNode.myNext = firstNode;
        	
        	// move down one element in the list; first now points to second, second now points to temp
        	firstNode = secondNode;
        	secondNode = temp;
        }
        
        // when the while loop exits, first will point to the original final node, and second will be null
        // we now update myHead to point to first, so that the last node in the list becomes the first
        myHead = firstNode;
    }
    
    public String toString()
    {
        //I've written this one for you!
        StringBuilder b = new StringBuilder();
        Node current = myHead;
        while(current != null)
        {
            b.append(current.myValue);
            // this is a little crude because it prints out a blank for last element too
            // I opted to keep the code extra simple rather than print right
            b.append(" ");
            current = current.myNext;
        }
        return b.toString();
    }

    /** To compare string lists, first compare 1st elements, than second elements, etc.
     * using the standard string comparison.  (e.g. [a z c] < [aa b c])
     * If one list has the same starting elements as another, but is shorter (e.g. [a] and [a b])
     * the shorter one is smaller
     * if two stringlist have the same elements are are the same length, they are equal
     */
    public int compareTo(StringLinkedListSolutions other) {
        
        // I'm not going to talk about this one in class
        // But you can do it if you've finished whatever problem we're working on
        
        Node thisCurrent = myHead;
        Node otherCurrent = other.myHead;
        while (thisCurrent != null && otherCurrent != null) {
        	if (thisCurrent.myValue.compareTo(otherCurrent.myValue) != 0) {
        		return thisCurrent.myValue.compareTo(otherCurrent.myValue);
        	}
        	thisCurrent = thisCurrent.myNext;
        	otherCurrent = otherCurrent.myNext;
        }        
        if (thisCurrent == null && otherCurrent == null) {
        	return 0;
        }
        return (thisCurrent == null ? -1 : 1);
    }
    
}