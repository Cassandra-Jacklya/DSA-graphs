import java.util.Iterator;

/**
** Author: Cassandra Jacklya
** Purpose: all methods for graphs are placed in one class for easier debugging
** Last modified on: 14th September 2020
**/

public class DSAGraph {

	private DSALinkedList vertexList = new DSALinkedList();
//-----------------------------------------------------------
	
	//default constructor
	public DSAGraph() {	}
	
	//adds vertices into the linked list
	public void addVertex(String inLab) {
		try {
			if (!(hasVertex(inLab))) {	
				DSAGraphVertex v = new DSAGraphVertex(inLab);
				vertexList.insertLast(v);
			}
			else {
				throw new IllegalArgumentException("Vertex already exists");
			}
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//adds edges to existing and non-exisiting vertices
	public void addEdge(String inLab1, String inLab2) {
		DSAGraphVertex v1, v2;
		if (hasVertex(inLab1)) {
			v1 = getVertex(inLab1);
			if (hasVertex(inLab2)) {
				v2 = getVertex(inLab2);
			}
			else {
				v2 = new DSAGraphVertex(inLab2);
				vertexList.insertLast(v2);
			}
		}
		else {
			v1 = new DSAGraphVertex(inLab1);
			vertexList.insertLast(v1);
			
			if (hasVertex(inLab2)) {
				v2 = getVertex(inLab2);
			}
			else {
				v2 = new DSAGraphVertex(inLab2);
				vertexList.insertLast(v2);
			}
		}
		v1.addEdge(v2);
		v2.addEdge(v1);
	}
	
	//checks if vertex exists in the linked list
	public boolean hasVertex(String inLab) {
		DSAGraphVertex v;
		Iterator iter = vertexList.iterator();
		boolean found = false;
		while (iter.hasNext()) {
			v = (DSAGraphVertex)iter.next();
			if (v.getLabel().equals(inLab)) {
				found = true;
			}
		}
		return found;
	}
	
	//gets the number of vertices in the linked list
	public int getVertexCount() {
		int number = vertexList.getCount();
		return number;
	}
	
	//obtains the vertex from the list
	public DSAGraphVertex getVertex(String inLab) {
		DSAGraphVertex word, v;
		word = null;
		Iterator iter = vertexList.iterator();
		while (iter.hasNext()) {
			v = (DSAGraphVertex)iter.next();
			if (v.getLabel().equals(inLab)) {
				word = v;
			}
		}
		return word;
	}
	
	//gets all the adjacent vertices of the vertex
	public DSALinkedList getAdjacent(String inLab) {
		DSAGraphVertex v = getVertex(inLab);
		return v.getAdjacent();
	}
	
	public void breadthFirstSearch() {
		clear();
		String word = "";
		DSAGraphVertex v, w;
		DSAQueue qu = new DSAQueue();
		DSALinkedList temp = new DSALinkedList();
		Iterator iter, iter2;
		iter = vertexList.iterator();
		while (iter.hasNext()) {
			v = (DSAGraphVertex)iter.next();
			if (v.getVisited() == false) {
				qu.enqueue(v);
				v.setVisited();
			}
			while (!(qu.isEmpty())) {
				v = (DSAGraphVertex)(qu.peek());
				qu.dequeue();
				word += " " + v.getLabel();
				temp = getAdjacent(v.getLabel());
				iter2 = temp.iterator();
				while (iter2.hasNext()) {
					w = (DSAGraphVertex)iter2.next();
					if (w.getVisited() == false) {
						qu.enqueue(w);
						w.setVisited();
					}
				}
			}
		}
		System.out.println(word);
	}
	
	//wrapper method
	public String depthFirstSearch() {
		clear();
		String word = "";
		Iterator iter = vertexList.iterator();
		DSAGraphVertex v;
		while (iter.hasNext()) {
			v = (DSAGraphVertex)iter.next();
			if (v.getVisited() == false) {
				word = " " + depthFirstSearchRec(v);
			}
		}
		return word;
	}
	
	//recursive method for dfs
	private String depthFirstSearchRec(DSAGraphVertex v) {
		String word = "";
		DSAGraphVertex w;
		DSALinkedList temp;
		Iterator iter2;
		v.setVisited();
		word = v.getLabel();
		temp = getAdjacent(v.getLabel());
		iter2 = temp.iterator();
		while (iter2.hasNext()) {
			w = (DSAGraphVertex)iter2.next();
			if (w.getVisited() == false) {
				word = word + " " + depthFirstSearchRec(w);
			}
		}
		return word;
	}
	
	//to ensure that all the vertices are set to false before performing other
	//...searches
	private void clear() {
		DSAGraphVertex v;
		Iterator iter;
		iter = vertexList.iterator();
		while (iter.hasNext()) {
			v = (DSAGraphVertex)iter.next();
			v.clearVisited();
		}
	}
	
	//adjacency list representation
	public void displayAsList() {
		System.out.println("\nList representation: ");
		String word = "";
		DSAGraphVertex v;
		Iterator iter = vertexList.iterator();
		while (iter.hasNext()) {
			v = (DSAGraphVertex)iter.next();
			word = word + v.getLabel() + " --> " + v.toString() + "\n";
		}
		System.out.println(word);
	}
	
	//adjacency matrix representation
	public void displayAsMatrix() {
		DSALinkedList list;
		String word = "";
		int count = 0;
		int n = getVertexCount();
		DSAGraphVertex v;
		Object[] temp = new Object[n];
		String[] A = new String[n];
		Iterator iter = vertexList.iterator();
		while (iter.hasNext()) {
			v = (DSAGraphVertex)iter.next();
			temp[count] = v;
			word = word + "  " + v.getLabel();
			A[count] = v.getLabel();
			count++;
		}
		System.out.println("Vertices are :- " + word + "\n");
		int[][] array = new int[n][n];
		for (int i = 0; i < array.length; i++) {
			if (temp[i] instanceof DSAGraphVertex) {
				list = ((DSAGraphVertex)(temp[i])).getAdjacent();
				array = convert(list, array, i);
			}
		}
		
		System.out.println("Matrix representation:");
		for (int j = 0; j < array.length; j++) {
			word = "";
			for (int k = 0; k < array[0].length; k++) {
				word = word + array[j][k] + "  ";
			}
			System.out.println(word);
		}
	}
	
	//aids in displaying the adjacency matrix
	private int[][] convert(DSALinkedList list, int[][] arr, int count) {
		DSAGraphVertex v;
		char letters = ' ';
		int ascii = 0;
		String word = "";
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			v = (DSAGraphVertex)iter.next();
			word = word + v.getLabel() + " ";
		}
		String[] temp = word.split(" ");
		for (int j = 0; j < temp.length; j++) {
			letters = temp[j].charAt(0);
			if (Character.isLowerCase(letters)) {
				ascii = ((int)letters) - 97;
			}
			else {
				ascii = ((int) letters) - 65;
			}
			arr[count][ascii] = 1;
		}
		return arr;
	}
}
	
