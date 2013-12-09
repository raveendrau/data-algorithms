import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;



public class StringLinkedListTester {
 
    @Test
    public void testAddAtBeginning(){
        StringLinkedList s = new StringLinkedList();
        assertEquals("",s.toString());
        s.addAtBeginning("a");
        assertEquals("a ",s.toString());
        s.addAtBeginning("b");
        assertEquals("b a ",s.toString());
    }

    @Test
    public void testAddAtEnd(){
        StringLinkedList s = new StringLinkedList();
        assertEquals("",s.toString());
        s.addAtEnd("a");
        assertEquals("a ",s.toString());
        s.addAtEnd("b");
        assertEquals("a b ",s.toString());
    }
    
    @Test
    public void testRemoveLongest(){
        
        //testing longest in middle
        StringLinkedList s = new StringLinkedList();
        assertEquals("",s.toString());
        s.addAtEnd("a");
        s.addAtEnd("b");
        s.addAtEnd("long");
        s.addAtEnd("c");
        s.removeLongestString();
        assertEquals("a b c ",s.toString());

        //testing empty list
        s = new StringLinkedList();
        s.removeLongestString();
        assertEquals("",s.toString());

        //testing 1 element list
        s = new StringLinkedList();
        s.addAtEnd("a");
        s.removeLongestString();
        assertEquals("",s.toString());
        
        //testing longest first
        s = new StringLinkedList();
        s.addAtEnd("long");
        s.addAtEnd("c");
        s.removeLongestString();
        assertEquals("c ",s.toString());
        
        //testing longest last
        s = new StringLinkedList();
        s.addAtEnd("a");
        s.addAtEnd("b");
        s.addAtEnd("long");
        s.removeLongestString();
        assertEquals("a b ",s.toString());
        
    }

    @Test
    public void testDouble(){
        
        //testing longest in middle
        StringLinkedList s = new StringLinkedList();
        assertEquals("",s.toString());
        s.addAtEnd("a");
        s.addAtEnd("b");
        s.addAtEnd("c");
        s.doubleList();
        assertEquals("a a b b c c ",s.toString());
    }
    
    @Test
    public void moveToEnd(){
        
        //testing longest in middle
        StringLinkedList s = new StringLinkedList();
        s.addAtEnd("a");
        s.addAtEnd("b");
        s.addAtEnd("c");
        s.addAtEnd("d");
        s.moveToEnd(2);
        assertEquals("c d a b ",s.toString());
        
        //testing move 0
        s = new StringLinkedList();
        s.addAtEnd("a");
        s.addAtEnd("b");
        s.addAtEnd("c");
        s.addAtEnd("d");
        s.moveToEnd(0);
        assertEquals("a b c d ",s.toString());
        
        //testing move length
        s = new StringLinkedList();
        s.addAtEnd("a");
        s.addAtEnd("b");
        s.addAtEnd("c");
        s.addAtEnd("d");
        s.moveToEnd(4);
        assertEquals("a b c d ",s.toString());
        
        //testing move 1
        s = new StringLinkedList();
        s.addAtEnd("a");
        s.addAtEnd("b");
        s.addAtEnd("c");
        s.addAtEnd("d");
        s.moveToEnd(1);
        assertEquals("b c d a ",s.toString());
        
        //testing move length -1
        s = new StringLinkedList();
        s.addAtEnd("a");
        s.addAtEnd("b");
        s.addAtEnd("c");
        s.addAtEnd("d");
        s.moveToEnd(3);
        assertEquals("d a b c ",s.toString());
        
    }

    @Test
    public void testReverse()
    {
        StringLinkedList s = new StringLinkedList();
        s.addAtEnd("a");
        s.addAtEnd("b");
        s.addAtEnd("c");
        s.addAtEnd("d");
        s.reverse();
        assertEquals("d c b a ",s.toString());
        
        //testing empty
        s = new StringLinkedList();
        s.reverse();
        assertEquals("",s.toString());

        //testing 1 element
        s = new StringLinkedList();
        s.addAtEnd("a");
        s.reverse();
        assertEquals("a ",s.toString());
    }
    
    @Test
    public void testCompareTo()
    {
        StringLinkedList a = new StringLinkedList();
        StringLinkedList b = new StringLinkedList();
        a.addAtEnd("a");
        a.addAtEnd("b");
        b.addAtEnd("aa");
        b.addAtEnd("b");
        
        assertTrue(a.compareTo(b) < 0);
        assertTrue(b.compareTo(a) > 0);
        assertTrue(b.compareTo(b) == 0);
        
        b = new StringLinkedList();
        b.addAtEnd("a");
        b.addAtEnd("b");
        b.addAtEnd("b");
        assertTrue(a.compareTo(b) < 0);
        assertTrue(b.compareTo(a) > 0);
        
        
    }

}
