package reifman.scrabble;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ScrabbleDictionary {
	
	private Set<String> allWords;
	
	public ScrabbleDictionary(){
		allWords = new HashSet<String>();
		try{
		Scanner fileReader = new Scanner(new File("OWL.txt"));
		while(fileReader.hasNext()){
			allWords.add(fileReader.next());
		}
		}
		catch(FileNotFoundException ex1){
			System.out.println("Error: File Not Found");
		}
	}

	public boolean contains(String word){
		if (word == null){
			return false;
		}
		String upper = word.toUpperCase();
				return this.allWords.contains(upper);
			}
		
	
		
	}


