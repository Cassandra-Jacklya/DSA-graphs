import java.util.*;
import java.io.*;

/**
** Author: Cassandra Jacklya 
** Purpose: to handle the file operations
** Last modified on: 14th September 2020
**/

//obtained from my own previous code from PDI unit with a few modifications
//... to fit the practical needs

public class FileIO {
	private DSAGraph g = new DSAGraph();
	
	/********************************************************************
	** Submodule name: readFile
	** Import: fileName (String)
	** Export: g (DSAGraph)
	** Purpose: to read in the edges from the file and add into the graph
	**********************************************************************/
	public DSAGraph readFile(String fileName) {
	    String word = "";
	    FileInputStream fileStream = null;
	    InputStreamReader reader;
	    BufferedReader buffer;
	    String line;
	    try {
	        fileStream = new FileInputStream(fileName);
    		reader = new InputStreamReader(fileStream);
			buffer = new BufferedReader(reader);
			
			//reads the first line in the file
			line = buffer.readLine();
			while (line != null) {	
			    
				//if it is a non-empty line, file continues to be read
				if (line.length() == 0) {
		    	   line = buffer.readLine();	//just to make sure that "\n" lines are not considered as EOF
				}
				else {
					word = processLine(line);	
				}
				//continues to read the next non-empty line in the file
		        line = buffer.readLine();
			}
			fileStream.close();
			System.out.println("\n" + "File has been read and saved to a graph");
		}
	    catch(IOException e) {
			if (fileStream != null) {
				try {
					fileStream.close();
				}
				catch(IOException e2) { 		     	 				
					System.out.println("Error: File not found or file data is invalid");
				}
			}
	    }
	    return g;
	}    	
	
	/**************************************************************************
	** Submodule name: processLine
	** Import: line (String)
	** Export: word (String)
	** Purpose: to separate certain parts of the string to add into the graph
	****************************************************************************/
	
	private String processLine(String line) {
	    String word = "";
	    String[] splitLine;
	    splitLine = line.split(" ");	//delimiter " " is used to separate key and data
	    for (int count = 1; count < splitLine.length; count++) {
	        word = word + splitLine[count] + " ";
	    }
		g.addEdge(splitLine[0], splitLine[1]);		//splitLine[0] represents the key (student ID)
		return word;
	}
}
