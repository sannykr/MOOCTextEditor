package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		
		size=0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null); 
		head.next=tail;
		tail.prev=head;
		
		
		
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		if(element==null){
			throw new NullPointerException();
		}
		
		LLNode<E> s=head;
		while(s.next!=tail){
			s=s.next;
		}
		LLNode<E> temp=new LLNode<E>(element);
		temp.next=tail;
		s.next=temp;
		temp.prev=s;
		tail.prev=temp;
		
		size+=1;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		
		
		// TODO: Implement this method.
		if(index < 0 || index >= size()){
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> s=head;
		for(int i=0; i<index; i++){
			s=s.next;
		}
		
		return (E)s.next.data;
		
		
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		
		
		if(index<0 || index>size()){
			throw new IndexOutOfBoundsException();
		}
		
		if(element==null){
			throw new NullPointerException();
		}
		
		LLNode<E> s=head;
		for(int i=0; i<index; i++){
			s=s.next;
		}
		LLNode<E> temp = new LLNode<E>(element);
		temp.next=s.next;
		s.next=temp;
		temp.prev=s;
		temp.next.prev=temp;
		size+=1;
		
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		/*
		LLNode<E> s=head;
		int c=0;
		while(s.next!=tail){
			s=s.next;
			c=c+1;
		}
		*/
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
		
		if(index<0 || index>=size()){
			throw new IndexOutOfBoundsException();
		}
		
		LLNode<E> s=head;
		for(int i=0;i<index;i++){
			s=s.next;
		}
		E data=s.next.data;
		LLNode<E> t=s.next.next;
		s.next=t;
		t.prev=s;
		
		size=size-1;
		return data;
		
		//return null;
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
		
		if(index < 0 || index>=size()){
			throw new IndexOutOfBoundsException();
		}
		
		if(element==null){
			throw new NullPointerException();
		}
		
		// TODO: Implement this method
		LLNode<E> s=head;
		for(int i=0; i<index; i++){
			s=s.next;
		}
		E data=s.next.data;
		s.next.data=element;
		return data;
		
		//return null;
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
