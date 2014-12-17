package reifman.scrabble;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

			Scanner keyboard = new Scanner(System.in);
			System.out.println("Enter the word you would like to search for.");
			String word = keyboard.next();
			ScrabbleDictionary tryout = new ScrabbleDictionary();
			System.out.println(word);
			long startTime = System.currentTimeMillis();
			for(int i = 0; i<1000000; i++){
			tryout.contains(word);
			}
			long endTime = System.currentTimeMillis();
			System.out.println(endTime-startTime);
			/*if(contains){
				System.out.println(word + " was found in the dictionary.");
				}
			else{
				System.out.println(word + " WAS NOT FOUND!!");
			}*/
			
	}

}
