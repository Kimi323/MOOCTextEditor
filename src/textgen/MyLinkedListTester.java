/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			System.out.println("inside testGet()");
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("Catch IndexOutOfBoundsException");
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
		try {
			list1.remove(5);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("caught exception: IndexOutOfBoundsException");
		}
		
		try {
			list1.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("caught exception: IndexOutOfBoundsException");
		}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		assertEquals("AddEnd: check last element is correct", "B", shortList.get(1));
		assertEquals("AddEnd: Check size is correct", 2, shortList.size);
		
		try {
			shortList.add(-2, "E");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("caught exception: IndexOutOfBoundsException");
		}
		
		try {
			shortList.add(10, "E");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("caught exception: IndexOutOfBoundsException");
		}
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		assertEquals("Size: check size is correct", 10, longerList.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		emptyList.add(0, 1);
		emptyList.add(0, 2);
		assertEquals("addAtIndex: check add to an empty list", (Integer)1, emptyList.get(1));
		assertEquals("addAtIndex: check add to an empty list", (Integer)2, emptyList.get(0));
		
		shortList.add(1, "");
		assertEquals("AddAtIndex: check element at position index is correct", "", shortList.get(1));
		assertEquals("AddAtIndex: check element at position index+1 is correct", "B", shortList.get(2));
		assertEquals("AddAtIndex: check size is correct", 3, shortList.size);
		
//		try {
//			emptyList.add(1, 2);
//			fail("Check out of bounds");
//		}
//		catch (IndexOutOfBoundsException e) {
//			System.out.println("caught exception: IndexOutOfBoundsException");
//		}
		
		try {
			shortList.add(5, "Z");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("caught exception: IndexOutOfBoundsException");
		}
		
		try {
			shortList.add(1, null);
			fail("Check out of bounds");
		}
		catch (NullPointerException e) {
			System.out.println("caught exception: NullPointerException");
		}

	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		assertEquals("Set: check replaced element is correct", (Integer)1, longerList.set(1, 100));
		assertEquals("Set: check new element is correct", (Integer)100, longerList.get(1));	
		
		try {
			longerList.set(20, 120);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("caught exception: IndexOutOfBoundsException");
		}
	
	
	}
	
	
	// TODO: Optionally add more test methods.
	
}
