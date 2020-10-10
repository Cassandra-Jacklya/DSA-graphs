import java.util.Iterator;

/**
** Author: Cassandra Jacklya
** Purpose: creates a vertex as an object to store in data for usage 
** Last modified on: 14th September 2020
**/

public class DSAGraphVertex {
	
	private String label;
	private boolean visited;
	private DSALinkedList links;
	//----------------------------------------------------------------
	
	//Default constructor
	public DSAGraphVertex(String inLab) {
		links = new DSALinkedList();
		label = inLab;
		visited = false;
	}
	
	//getter and setter methods
	public String getLabel() {
		return this.label;
	}
	
	
	public DSALinkedList getAdjacent() {
		return this.links;
	}
	
	public void addEdge(DSAGraphVertex vertex) {
		if (!(isDuplicate(vertex))) {
			links.insertLast(vertex);
		}
	}
	
	//these three are used for dfs and bfs
	public void setVisited() {
		this.visited = true;
	}
	
	public void clearVisited() {
		this.visited = false;
	}
	
	public boolean getVisited() {
		return this.visited;
	}
	
	
	//prints all the labels that exists in the current vertex object
	public String toString() {
		DSAGraphVertex v;
		String word = "";
		Iterator iter = links.iterator();
		while (iter.hasNext()) {
			v = (DSAGraphVertex)iter.next();
			word = word + v.getLabel() + " ";
		}
		return word;
	}
	
	//ensures there are no duplicate labels in the vertex
	private boolean isDuplicate(DSAGraphVertex v) {
		boolean duplicate = false;
		String lab1, lab2;
		DSAGraphVertex temp;
		Iterator iter = links.iterator();
		while (iter.hasNext()) {
			temp = (DSAGraphVertex)(iter.next());
			lab1 = temp.getLabel();
			lab2 = v.getLabel();
			if (lab1.equals(lab2)) {
				duplicate = true;
			}
		}
		return duplicate;
	}
}
	