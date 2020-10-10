/**
**Author: Cassandra Jacklya 
** Purpose: Create a doubly-linked, double-ended linked list 
** Last modified on: 14th September 2020
**/

import java.util.*;
import java.io.Serializable;

public class DSALinkedList implements Iterable, Serializable {
	
   /** serialversionUID is used during deserialization to verify that the sender
	** and receiver of a serialized object both have the same version number to
	** avoid an InvalidClassException
	**/
	private static final long serialversionUID = 3000L;
	private DSAListNode head;
	private DSAListNode tail;
	private int count = 0;
	
	//this class is made private since it is only used within the DSALinkedList class
	private class DSAListNode implements Serializable {
		private Object value;
		private DSAListNode next;
		private DSAListNode prev;
		private int node;
		
		//constructor
		private DSAListNode(Object inValue) {
			value = inValue;
			next = null;	//both pointers are first set to null
			prev = null;
		}
		
		//getters and setters
		public void setValue (Object inValue) {
			value = inValue;
		}
		
		public Object getValue() {
			return this.value;
		}
		
		public DSAListNode getNext() {
			return this.next;
		}
		
		public DSAListNode getPrev() {
			return this.prev;
		}
		
		public void setNext(DSAListNode newNext) {
			next = newNext;
		}
		
		public void setPrev(DSAListNode newPrev) {
			prev = newPrev;
		}
		
	}
	
	//this class is used to allow the user to iterate through all items in the list
	public class DSALinkedListIterator implements Iterator {
		
		private DSAListNode iterNext;
		
		public DSALinkedListIterator(DSALinkedList inList) {
			iterNext = inList.head;	//sets the node first to point to the head
		}
		
		public boolean hasNext() {
			return (iterNext != null);	//checks whether there are more values in the list
		}
		
		//aids to iterate through the list and return the values to the main caller
		public Object next() {
			Object value;
			if (iterNext == null) {
				value = null;
			}
			else {
				value = iterNext.getValue();
				iterNext = iterNext.getNext();
			}
			return value;
		}
		
		public void remove() {	//this operation is to override the built-in method 
			throw new UnsupportedOperationException("Not supported");	//serves no use for this practical
		}
	}
	
	/** Methods of DSALinkedList starts here **/
	
	//constructor
	public DSALinkedList() {
		this.head = null;
		this.tail = null;
	}
	
	//getters and setters
	public Object getTail() {
		Object c = null;
		try {
			c = tail.getValue();
		}
		catch (NullPointerException e) {
			System.out.println("List is empty");
		}
		return (c);
	}
	
	public Object getHead() {
		Object c = null;
		try {
			c = head.getValue();
		}
		catch (NullPointerException e) {
			System.out.println("List is empty");
		}
		return (c);
	}
	
	public int getCount() {
		return this.count;
	}
	
	public Iterator iterator() {
		return new DSALinkedListIterator(this);
	}
	
	//as the name says to insert a value in the first node
	public void insertFirst(Object newVal) {
		DSAListNode newNode = new DSAListNode(newVal);	//creates a new node
		DSAListNode prevNode = head;
		if (isEmpty()) {	//checks whether the list is currently empty
			head = newNode;
			tail = newNode;	//if it is empty, both tail and head points to the new node
		}
		else {
			newNode.setNext(head);	//fix the links if list is not empty
			head = newNode;
			prevNode.setPrev(newNode);
		}
		count = count + 1;
	}
	
	//inserts the value at the last node
	public void insertLast(Object newVal) {
		DSAListNode currentNode, newNode, prevNode;
		newNode = new DSAListNode(newVal);
		prevNode = tail;
		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		}
		else {
			currentNode = head;		//still needs to iterate through the list to
			while (currentNode.getNext() != null) {	//reach the second last item
				currentNode = currentNode.getNext();
			}
			prevNode.setPrev(tail);		//fix links as needed
			tail = newNode;
			currentNode.setNext(newNode);
		}
		count = count + 1;
	}
	
	//aids in checking whether the list is empty
	public boolean isEmpty() {
		boolean empty = false;
		try {
			if (head == null) {
				empty = true;
			}
		}
		catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
		return empty;
	}
	
	//obtains the value of the first node in the list without removing the node
	public Object peekFirst() {
		Object value;
		if (isEmpty()) {
			throw new NullPointerException("Operation aborted: No elements found");
		}
		else {
			value = head.getValue();
		}
		return value;
	}
	
	//obtains the value of the last node in the list without removing the node
	public Object peekLast() {
		Object value;
		DSAListNode currentNode;
		if (isEmpty()) {
			throw new NullPointerException("Operation aborted: No elements found");
		}
		else {
			value = tail.getValue();
		}
		return value;
	}
	
	//removes the first node in the list
	public Object removeFirst() {
		DSAListNode prevNode;
		Object nodeValue;
		if (isEmpty()) {	//if empty, exceptions must be thrown
			throw new NullPointerException("Operation aborted: List is empty");
		}
		else {
			prevNode = head;	
			nodeValue = head.getValue();	//gets the value of the removed node
			head = head.getNext();	//head now points to the next node
			if (head == null) {
				tail = null;	//if the removed node was the last item in the list
			}
			else {
				prevNode.setPrev(null);
			}
			count = count - 1;
		}
		return nodeValue;
	}
	
	//removes the last node in the list
	public Object removeLast() {
		Object nodeValue;
		DSAListNode currentNode, prevNode;
		if (isEmpty()) {	//checks if list is empty
			throw new NullPointerException("Operation aborted: List is empty");
		}
		else {
			prevNode = null;
			currentNode = head;
			while (currentNode.getNext() != null) {
				prevNode = currentNode;
				currentNode = currentNode.getNext();
			}
			nodeValue = tail.getValue();
			tail = prevNode;
			prevNode.setNext(null);
			count = count - 1;
		}
		return nodeValue;
	}
	
	//prints out the value of the nodes in the list
	public String toString() {
		Object s;
		String word = "";
		Iterator iter = iterator();
		while(iter.hasNext()) {
			s = (Object)iter.next();
			word = word + " --> " + s.toString();
		}
		return word;
	}
}
	
			
	
				
	
	
		
			