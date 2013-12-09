
public class StringLinkedList implements Comparable<StringLinkedList>{

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
    
    public void addAtEnd(String valueToAdd)
    {
        //your code goes here
        if(myHead == null)
        {
            addAtBeginning(valueToAdd);
        } else {
            Node current = myHead;
            while(current.myNext != null) {
                current = current.myNext;
            }
            current.myNext = new Node(valueToAdd, null);
        }
        
    }
    
    public void addAtBeginning(String valueToAdd)
    {
        myHead = new Node(valueToAdd, myHead);
    }
    
    // Removes the longest string from the list
    // so if you have the list [a,b,longstring,z,q]
    // after this function runs you end up with [a,b,z,q]
    // if more than one string has the same longest length, remove the
    // first one
    // if the list is empty, do nothing
    // if the list has only 1 element, remove it
    public void removeLongestString()
    {
        if(myHead == null)
            return;
        Node prevLongest = null;
        Node current = myHead;
        String longest = myHead.myValue;
        while(current.myNext != null) {
            if(longest.length() < current.myNext.myValue.length()) {
                prevLongest = current;
                longest = current.myNext.myValue;
            }
            current = current.myNext;
        }
        if(prevLongest == null) {
            //first was longest
            myHead = myHead.myNext;
        } else {
            prevLongest.myNext = prevLongest.myNext.myNext;
        }
    }
    
    public void doubleList()
    {
        Node current = myHead;
        while(current != null) {
            current.myNext = new Node(current.myValue, current.myNext);
            current = current.myNext.myNext;
        }
    }
    
    public void moveToEnd(int k)
    {
        if(k == 0) return;
        if(k == 1 && myHead.myNext == null) return;
        
        Node newLast = myHead;
        Node newHead = myHead.myNext;
        
        for(int i = 0; i < k - 1; i++)
        {
            newLast = newHead;
            newHead = newHead.myNext;
        }
        
        if(newHead == null) return;
        
        Node last = newHead;
        
        while(last.myNext != null) {
            last = last.myNext;
        }
        last.myNext = myHead;
        myHead = newHead;
        newLast.myNext = null;
        
    }
    
    public void reverse()
    {
        if( myHead == null || myHead.myNext == null)
            return;
        Node backwardsList = myHead;
        Node forwardsList = myHead.myNext;
        backwardsList.myNext = null;
        while(forwardsList != null) {
            Node temp = forwardsList;
            forwardsList = forwardsList.myNext;
            temp.myNext = backwardsList;
            backwardsList = temp;
        }
        myHead = backwardsList;
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

    // To compare string lists, first compare 1st elements, than second elements, etc.
    // using the standard string comparison.  (e.g. [a z c] < [aa b c])
    // If one list has the same starting elements as another, but is shorter (e.g. [a] and [a b])
    // the shorter one is smaller
    // if two stringlist have the same elements are are the same length, they are equal
    public int compareTo(StringLinkedList other) {
        Node current = myHead;
        Node otherCurrent = other.myHead;
        while(current != null & otherCurrent != null) {
            int nodeCompare = current.myValue.compareTo(otherCurrent.myValue);
            if(nodeCompare != 0)
            {
                return nodeCompare;
            }
            current = current.myNext;
            otherCurrent = otherCurrent.myNext;
        }
        if(current == null && otherCurrent == null)
            return 0;
        
        if(current == null) return -1;
        
        return 1;
    }
    
}