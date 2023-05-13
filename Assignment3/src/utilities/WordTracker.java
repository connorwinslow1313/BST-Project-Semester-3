package utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import exceptions.TreeException;
import referenceBasedTreeImplementation.BSTree;
import referenceBasedTreeImplementation.BSTreeNode;

public class WordTracker {

	public static void main(String[] args) throws Exception, TreeException {
		String filePath = "textfile.txt";
		Path path = Paths.get(System.getProperty("user.dir"))
				.resolve(filePath);
		
		BufferedReader reader = new BufferedReader(new FileReader(path.toFile()));
		
		BSTree wordsOfArticle = new BSTree();
		
		
		String line = reader.readLine();
		
		int totalLines = 0;
		int totalWords = 0;
		int occurrenceNum = 1;
		int linesOccurrence = 1;
		boolean occurredInLine = false;
		
		BSTreeNode node = null;
		File f = new File("repository.ser");
		if(!f.exists()) { 
		while(line != null) {
			if(!line.trim().equals("")) {
				occurredInLine = false;
				String [] words = line.split(" ");
				totalWords += words.length;
				for(String word : words) {
					String cleanedUpWord = word.toLowerCase() 
							.replace(",", "") 
							.replace(":", "") 
							.replace(".", "")
							.replace(";","")
							.replace("'","")
							.replace("!", "")
							.replace("?", "")
							.replace("-","")
							.replace("(", "")
							.replace(")", "")
							.replace("[", "")
							.replace("]", "")
							.replace("\"", "");
					
					if(wordsOfArticle.isEmpty()) {
						wordsOfArticle.add(cleanedUpWord, totalLines, filePath, occurrenceNum, linesOccurrence);
						continue;
					}
					if(wordsOfArticle.contains(cleanedUpWord)) {
						if(!occurredInLine) {
							node = wordsOfArticle.search(cleanedUpWord);
							node.setOccurrsInLine(node.getOccurrence() + 1);
							occurredInLine = true;
						}
						node = wordsOfArticle.search(cleanedUpWord);
						node.setOccurrence(node.getOccurrence() + 1);
						continue;
					}
					wordsOfArticle.add(cleanedUpWord, totalLines, filePath, occurrenceNum, linesOccurrence);
					
					
				}	
			}
			totalLines++;
			line = reader.readLine();
			
		}
		System.out.println(wordsOfArticle.size());
		System.out.println(totalWords);
		System.out.println(totalLines);
		wordsOfArticle.writeTofile();
		}else {
			wordsOfArticle.readFromFile();
		}
		
		
		/*while(test2.hasNext()) {
			curr = (BSTreeNode)test2.next();
			System.out.println("--------------");
			System.out.println(curr.getTreeValue());
			System.out.println("from line: " + curr.getLine());
			System.out.println("from file: " + curr.getFile());
			System.out.println("occurrences: " + curr.getOccurrence());
		}*/
		
		Scanner input = new Scanner(System.in);
		String userInput = "";
		
		while(!userInput.equals("-e")) {
			System.out.println("Enter input");
			System.out.println("-pf to print in alphabetic order all words along with the corresponding list of files in which the words occur");
			System.out.println("-pl to print in alphabetic order all words along with the corresponding list of files and numbers of the lines in which the word occurs");
			System.out.println("-po to print in alphabetic order all words along with the corresponding list of files, numbers of the lines in which the word occur and the frequency of occurrence of the words.");
			System.out.println("-f to print report to output.txt");
			System.out.println("-e to exit");
			userInput = input.next();
			if(userInput.equals("-pf")) {
				userInput = "";
				HashSet<String> uniqueFiles = new HashSet<String>();
				Iterator test2 = wordsOfArticle.inorderIterator();
				BSTreeNode curr = null;
				System.out.println("Unique words alphabetically: ");
				while(test2.hasNext()) {
					curr = (BSTreeNode) test2.next();
					System.out.println("-----------------");
					System.out.println(curr.getTreeValue());
					uniqueFiles.add(curr.getFile());
					}
				Object[] filesArray;
				filesArray = uniqueFiles.toArray();
				System.out.println("Unique Files: ");
				for(int i = 0; i < filesArray.length;i++) {
					System.out.println(filesArray[i]);
				}

			}
			
			if(userInput.equals("-pl")) {
				userInput = "";
				HashSet<String> uniqueFiles = new HashSet<String>();
				Iterator test2 = wordsOfArticle.inorderIterator();
				BSTreeNode curr = null;
				System.out.println("Unique words alphabetically: ");
				while(test2.hasNext()) {
					curr = (BSTreeNode) test2.next();
					System.out.println("-----------------");
					System.out.println(curr.getTreeValue());
					System.out.println("Occurrs in " + curr.getOccurrsInLine() + " lines");
					uniqueFiles.add(curr.getFile());
					}
				Object[] filesArray;
				filesArray = uniqueFiles.toArray();
				System.out.println("Unique Files: ");
				for(int i = 0; i < filesArray.length;i++) {
					System.out.println(filesArray[i]);
				}
				
			}
			
			if(userInput.equals("-po")) {
				userInput = "";
				HashSet<String> uniqueFiles = new HashSet<String>();
				Iterator test2 = wordsOfArticle.inorderIterator();
				BSTreeNode curr = null;
				System.out.println("Unique words alphabetically: ");
				while(test2.hasNext()) {
					curr = (BSTreeNode) test2.next();
					System.out.println("-----------------");
					System.out.println(curr.getTreeValue());
					System.out.println("Occurrs in " + curr.getOccurrsInLine() + " lines");
					System.out.println("Occurrs " + curr.getOccurrence() + " times total");
					uniqueFiles.add(curr.getFile());
					}
				Object[] filesArray;
				filesArray = uniqueFiles.toArray();
				System.out.println("Unique Files: ");
				for(int i = 0; i < filesArray.length;i++) {
					System.out.println(filesArray[i]);
				}
			}
			if(userInput.equals("-f")) {
				 try {
					 BufferedWriter myWriter = new BufferedWriter(new FileWriter("output.txt"));
					 
					 HashSet<String> uniqueFiles = new HashSet<String>();
						Iterator test2 = wordsOfArticle.inorderIterator();
						BSTreeNode curr = null;
						myWriter.write("Unique words alphabetically: ");
						myWriter.newLine();
						while(test2.hasNext()) {
							curr = (BSTreeNode) test2.next();
							myWriter.write("-----------------");
							myWriter.newLine();
							myWriter.write((String) curr.getTreeValue());
							myWriter.newLine();
							myWriter.write("Occurrs in " + curr.getOccurrsInLine() + " lines");
							myWriter.newLine();
							myWriter.write("Occurrs " + curr.getOccurrence() + " times total");
							myWriter.newLine();
							uniqueFiles.add(curr.getFile());
							}
						Object[] filesArray;
						filesArray = uniqueFiles.toArray();
						myWriter.write("Unique Files: ");
						for(int i = 0; i < filesArray.length;i++) {
							myWriter.write((String) filesArray[i]);
							myWriter.newLine();
						}
				      myWriter.close();
				      System.out.println("Successfully wrote to the file.");
				    } catch (IOException e) {

				    }
			}
		}
	}
}

	
	
	
	

