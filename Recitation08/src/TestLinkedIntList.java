import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestLinkedIntList {

	LinkedIntList listOne;
	LinkedIntList listTwo;
	
	@Before
	public void setUp() {
		listOne = new LinkedIntList();
		listTwo = new LinkedIntList();
		
		int[] oneVals = {1, 3, 5, 7};
		int[] twoVals = {2, 4, 6, 8};
		
		for(int i: oneVals) {
			listOne.addToTail(i);
		}
		for(int i: twoVals) {
			listTwo.addToTail(i);
		}
	}
	
    @Test
    public void removeLast(){
       assertEquals("[1,3,5,7]", listOne.toString());
       listOne.removeLast();
       assertEquals("[1,3,5]", listOne.toString());
       listOne.removeLast();
       assertEquals("[1,3]", listOne.toString());
       listOne.removeLast();
       assertEquals("[1]", listOne.toString());
       listOne.removeLast();
       assertEquals("[]", listOne.toString());
       listOne.removeLast();
       assertEquals("[]", listOne.toString());
    }

    @Test
    public void addToTail(){
    	listOne = new LinkedIntList();
    	int[] oneVals = {1, 3, 5, 7};
    	
    	String base = "[";
    	assertEquals(base + "]", listOne.toString());
    	
    	base += "1";
    	listOne.addToTail(1);
    	
    	for(int i: oneVals){
    		assertEquals(base + "]", listOne.toString());
    		listOne.addToTail(i);
    		base += "," + i;
    	}
    	
    	assertEquals(base + "]", listOne.toString());
    }
    
    @Test
    public void addToTailNoRecursion(){
    	listOne = new LinkedIntList();
    	int[] oneVals = {1, 3, 5, 7};
    	
    	String base = "[";
    	assertEquals(base + "]", listOne.toString());
    	
    	base += "1";
    	listOne.addToTailNoRecursion(1);
    	
    	for(int i: oneVals){
    		assertEquals(base + "]", listOne.toString());
    		listOne.addToTailNoRecursion(i);
    		base += "," + i;
    	}
    	
    	assertEquals(base + "]", listOne.toString());
    }
    
    @Test
    public void contains(){
        assertTrue(listOne.contains(1));
        assertTrue(listOne.contains(3));
        assertTrue(listOne.contains(5));
        assertTrue(listOne.contains(7));
        assertFalse(listOne.contains(2));
        assertFalse(listOne.contains(4));
        assertFalse(listOne.contains(6));
        assertFalse(listOne.contains(8));
        assertTrue(listTwo.contains(2));
        assertTrue(listTwo.contains(4));
        assertTrue(listTwo.contains(6));
        assertTrue(listTwo.contains(8));
        assertFalse(listTwo.contains(1));
        assertFalse(listTwo.contains(3));
        assertFalse(listTwo.contains(5));
        assertFalse(listTwo.contains(7));
    }

    @Test
    public void countOccurences(){
    	assertEquals(1, listOne.countOccurences(1));
    	assertEquals(1, listOne.countOccurences(3));
    	assertEquals(1, listOne.countOccurences(5));
        assertEquals(1, listOne.countOccurences(7));
        
        listOne.addToTail(3);
        assertEquals(2, listOne.countOccurences(3));
        
        listOne.addToTail(3);
        assertEquals(3, listOne.countOccurences(3));
    }
    
    @Test
    public void reverseList(){
    	assertEquals("[1,3,5,7]", listOne.toString());
    	listOne.reverseList();
    	assertEquals("[7,5,3,1]", listOne.reverseList().toString());
    }

    @Test
    public void mergeLists()
    {
    	assertEquals("[1,3,5,7]", listOne.toString());
    	assertEquals("[2,4,6,8]", listTwo.toString());
    	assertEquals("[1,2,3,4,5,6,7,8]", listOne.mergeLists(listTwo).toString());
    }
    
    @Test
    public void sum()
    {
        assertEquals(16, listOne.sum());
        assertEquals(20, listTwo.sum());
    }
    
    @Test
    public void sumEven()
    {
        assertEquals(0, listOne.sumEven());
        assertEquals(20, listTwo.sumEven());
    }
    
    

}
