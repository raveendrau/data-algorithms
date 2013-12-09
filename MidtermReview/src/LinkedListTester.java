import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class LinkedListTester {

	private LinkedList test;
	
	@Before
	public void setup() {
		test = new LinkedList();
	}
	
	@Test
	public void testAdd() {
		test.add(1);
		test.add(2);
		test.add(3);
		assertEquals("1 2 3", test.toString());
		
		test.add(4);
		test.add(4);
		test.add(5);
		assertEquals("1 2 3 4 4 5", test.toString());
	}
	
	@Test
	public void testRemove() {
		test.add(1); test.add(2); test.add(3);
		test.add(4); test.add(4); test.add(5);
		test.remove(1);
		assertEquals("2 3 4 4 5", test.toString());
		
		test.remove(6);
		assertEquals("2 3 4 4 5", test.toString());
		
		test.remove(4);
		assertEquals("2 3 4 5", test.toString());
		
		test.remove(5);
		assertEquals("2 3 4", test.toString());
		
		test.remove(2);
		test.remove(3);
		test.remove(4);
		assertEquals("", test.toString());
		
		test.remove(10);
		assertEquals("", test.toString());
	}
	
	@Test
	public void testRemoveEveryOther() {
		test.removeEveryOther();
		assertEquals("", test.toString());
		
		test.add(1); test.add(2); test.add(3); test.add(4);
		test.removeEveryOther();
		assertEquals("1 3", test.toString());
		
		test.add(5);
		test.removeEveryOther();
		assertEquals("1 5", test.toString());
		
		test.removeEveryOther();
		assertEquals("1", test.toString());
		
		test.removeEveryOther();
		assertEquals("1", test.toString());
	}
	
	@Test
	public void testMoveToEnd() {
		test.moveToEnd(0);
		assertEquals("", test.toString());
				
		test.add(1); test.add(2); test.add(3); test.add(4);
		test.add(5); test.add(6); test.add(7); test.add(8);
		test.moveToEnd(0);
		assertEquals("1 2 3 4 5 6 7 8", test.toString());
		
		test.moveToEnd(4);
		assertEquals("5 6 7 8 1 2 3 4", test.toString());
		
		test.moveToEnd(6);
		assertEquals("3 4 5 6 7 8 1 2", test.toString());
		
		test.moveToEnd(10);
		assertEquals("3 4 5 6 7 8 1 2", test.toString());
	}
	
	@Test
	public void testReverse() {
		test.reverse();
		assertEquals("", test.toString());
		
		test.add(1);
		test.reverse();
		assertEquals("1", test.toString());
		
		test.add(2); test.add(3); test.add(4); test.add(5);
		test.reverse();
		assertEquals("5 4 3 2 1", test.toString());
	}
	
}