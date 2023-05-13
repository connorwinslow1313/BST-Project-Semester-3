package utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class FileIO {

	/*
	 * public static void uniqueWords(String line) { int count; //Extract words from
	 * string using split() method where there are spaces. String[] words =
	 * line.split(" "); //Iterate over words array. for (int i=0; i < words.length;
	 * i++) { // Set current word count to 1. count = 1; for (int j= i+1; j <
	 * words.length; j++) { if (words[i].equalsIgnoreCase(words[j])) { count++;
	 * words[j]=""; } }
	 * 
	 * if (count == 1 && words[i] != null) //print to console for testing
	 * System.out.println(words[i]); } }
	 */

	public static void main(String[] args) throws IOException {
		
		
		
		  List<String> listOfStrings = new ArrayList<String>();
		  
		  BufferedReader reader = new BufferedReader(new FileReader("textfile.txt"));
		  String line = reader.readLine(); 
		  
		  String[] words;
			Set<String> uniqueWords = new HashSet<String>();
			words = line.split("[\\W]+");
			
			/*
			 * for (int i = 0; i < words.length; i++) { uniqueWords.add(words[i]); line =
			 * reader.readLine(); System.out.println(uniqueWords); }
			 */
			
		  
		  
		  while (line != null) {
			  listOfStrings.add(line);
			
			  line = reader.readLine();
			  
			  //listOfStrings.add("\n");
			  
			  for (int i = 0; i < words.length; i++)
				{
					uniqueWords.add(words[i]);
					
					System.out.println(uniqueWords);
				}
			  
		  } 
		  reader.close();
		  
		  String[] array = uniqueWords.toArray(new String[0]);
		  
		  try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
				writer.write(Arrays.toString(array));
				writer.close();
			  } 
		  catch (IOException e) {
			   e.printStackTrace();
		      }
		}
	}
