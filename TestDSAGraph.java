import java.util.*;

/**
** Author: Cassandra Jacklya
** Purpose: to serve as a test harness for the DSAGraph class
** Last modified on: 14th September 2020
**/

public class TestDSAGraph {
	public static void main(String[] args) {
		
		
		DSAGraph g = new DSAGraph();
		FileIO iFile = new FileIO();
		FileIO iFile2 = new FileIO();
		
		//prac6_1 file details
		System.out.println("\nprac6_1.al file details shown below:");
		g = iFile.readFile("prac6_1.al");
		
		System.out.println("Breadth First Search: ");
		g.breadthFirstSearch();
		
		System.out.println("\n" + "Depth First Search: ");
		System.out.println(g.depthFirstSearch());
		
		g.displayAsList();
		
		System.out.println("Consider the matrix to be from alphabetical order in rows and columns:");
		g.displayAsMatrix();
		
		//prac6_2 file details
		System.out.println("\nprac6_2.al file details shown below:");
		g = iFile2.readFile("prac6_2.al");
		
		System.out.println("Breadth First Search: ");
		g.breadthFirstSearch();
		
		System.out.println("\n" + "Depth First Search: ");
		System.out.println(g.depthFirstSearch());
		
		g.displayAsList();
		
		System.out.println("Consider the matrix to be from alphabetical order in rows and columns:");
		g.displayAsMatrix();
	}
}