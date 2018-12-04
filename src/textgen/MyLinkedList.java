package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	// LLNode represents a single element in a doubly-linked list.
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * Null elements are not allowed in the list
	 * if someone tries to insert one you should throw a NullPointerException.
	 * @param element The element to add
	 */
	public boolean add(E element ) {
		// TODO: Implement this method
		if (element == null) {
			throw new NullPointerException();
		}
		
		LLNode<E> currNode = new LLNode<E>(element);
		if (size == 0) {
			head.next = currNode;
			tail.prev = currNode;
			size++;
		} else {			
			currNode.prev = tail.prev;
			currNode.next = tail;
			tail.prev.next = currNode;
			tail.prev = currNode;
			size++;
		}
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) {
		// TODO: Implement this method.
		// if list is empty, also throw IndexOutOfBoundsException 
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		
		if (size == 0) {
			throw new NullPointerException();
		}
		
		LLNode<E> nextNode;
		nextNode = head.next;
		for (int i=0; i<index;i++) {
			nextNode = nextNode.next;
		}
		return nextNode.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element) {
		// TODO: Implement this method
		if (element == null) {
			throw new NullPointerException();
		}

		if (size == 0) {
			System.out.println("this is an empty list");
			add(element);
		} else {			
			LLNode<E> currNode = new LLNode<E>(element);
			LLNode<E> nextNode = head.next;
			
			if (index < 0 || index > size - 1) {
				throw new IndexOutOfBoundsException();
			}
			
			for (int i=0; i<index; i++) {
				nextNode = nextNode.next;
			}
			currNode.next = nextNode;
			currNode.prev = nextNode.prev;
			
			if (nextNode.prev == null) {
				head.next = currNode;
			} else {
				nextNode.prev.next = currNode;				
			}
			nextNode.prev = currNode;
			size++;
		}
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		int size = 0;
		LLNode<E> nextNode = head.next;
		while (!(nextNode.data == null)) {			
			size++;
			nextNode = nextNode.next;
		}
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if (size == 0 ) {
			throw new NullPointerException();
		}
		
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		
		LLNode<E> currNode = head.next;
		for (int i=0; i<index;i++) {
			currNode = currNode.next;
		}
		
		if (currNode.prev == null) {
			head.next = currNode.next;			
		} else {
			currNode.prev.next = currNode.next;
			currNode.prev = null;
		}
		
		if (currNode.next == null) {
			tail.prev = currNode.prev;
		} else {
			currNode.next.prev = currNode.prev;
			currNode.next = null;
		}
						
		return currNode.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		LLNode<E> newNode = new LLNode<E>(element);
		
		if (element == null) {
			throw new NullPointerException();
		}
		// how about emptyList.set(1,3)??
		if (size == 0 || index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		
		LLNode<E> currNode = head.next;
		for (int i=0; i<index;i++) {
			currNode = currNode.next;
		}
		currNode.next.prev = newNode;
		currNode.prev.next = newNode;
		currNode.next = null;
		currNode.prev = null;
		
		return currNode.data;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
