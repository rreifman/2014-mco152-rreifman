package reifman.morsecode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MorseCode {
	// private String [][] code;
	private Map<String,String> toMorseCode;
	private Map<String,String> toText;

	public MorseCode() {
		toMorseCode = new HashMap<String,String>();
		toText = new HashMap<String,String>();
		String letter = " ";
		String morse = " ";
		try {
			// code = new String[36][2];

			Scanner file = new Scanner(new File("./morsecode.txt"));
			while (file.hasNext()) {
				/*
				 * for(int i=0; i<code.length; i++){ for(int j=0;
				 * j<code[i].length; j++){ code[i][j] = file.next(); } } }
				 */
				letter = file.next();
				morse = file.next();

				toMorseCode.put(letter, morse);
				toText.put(morse, letter);

			}
		} catch (FileNotFoundException ex1) {
			System.out.println("Error: File Not Found.");
		}
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter letter");
		String let = keyboard.next();
		System.out.println(toMorseCode.get(let));

	}

	public String toMorseCode(String text) {
		String upper = text.toUpperCase();

		StringBuilder output = new StringBuilder();

		for (int i = 0; i < upper.length(); i++) {

			char letter = upper.charAt(i);

			String letterString = "";
			letterString = letterString + letter;

			if (letterString.equals(" ")) {
				output.append(" / ");
			}

			else {
				/*
				 * for(int j=0; j<code.length; j++){
				 * if(letterString.equals(code[j][0])){
				 * output.append(code[j][1]); output.append(" "); } }
				 */
				output.append(toMorseCode.get(letter));
			}

		}
		return output.toString();

	}

	public String toPlainText(String codeText) {

		StringBuilder output = new StringBuilder();

		String[] codeTextArray = codeText.split(" ");

		for (int i = 0; i < codeTextArray.length; i++) {
			if (codeTextArray[i].equals("/")) {
				output.append(" ");
			}

			else {
				/*
				 * for(int j=0; j<code.length; j++){
				 * if(codeTextArray[i].equals(code[j][1])){
				 * output.append(code[j][0]); } }
				 */
				output.append(toMorseCode.get(codeTextArray[i]));
			}

		}
		return output.toString();

	}
}
