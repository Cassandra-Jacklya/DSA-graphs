/**
** Author: Cassandra Jacklya
** Purpose: creates a queue class to implement the last in first out operation
** Last modified on: 14th September 2020
**/
import java.util.*;
import java.io.Serializable;

public class DSAQueue implements Iterable, Serializable {
	private static final long serialversionUID = 2000L;
	int count;
	private DSALinkedList list;
	
	//default constructor
	public DSAQueue() {
		list = new DSALinkedList();
		count = 0;
	}
	
	public Iterator iterator() {
		return list.iterator();
	}
	
	public int getCount() {
		return this.count;
	}
	
	public Object peek() {
		Object top = null;
		try {
			if (isEmpty()) {
				throw new ArrayIndexOutOfBoundsException("No elements found. Queue is empty");
			}
			else {
				top = list.peekFirst();
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
		return top;
	}
	
	public boolean isEmpty() {
		return (list.isEmpty());
	}
	
	//this method displays the current queue to the main
	public String toString() {
		Object s;
		String word = "";
		Iterator iter = list.iterator();
		while(iter.hasNext()) {
			s = (Object)iter.next();
			word = word + "\n" + s.toString();
		}
		return word;
	}
	
	//setter methods 
	public void enqueue(Object value) {
		list.insertLast(value);
		count++;
	}
	
	public Object dequeue() {
		Object topValue = null;
		try {
			if (isEmpty()) {
				throw new ArrayIndexOutOfBoundsException("Operation aborted: The queue is empty");
			}
			else {
				topValue = list.peekFirst();
				list.removeFirst();
				count--;
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
		return topValue;
	}
}
	

