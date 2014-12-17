package reifman.morsecode;

import java.util.Scanner;

public class MorseCodeMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MorseCode code = new MorseCode();
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Enter the text");
		String text = keyboard.nextLine();
		String output = code.toMorseCode(text);
		System.out.println(output);
		System.out.println("Enter the morse code");
		String textTwo = keyboard.nextLine();
		String outputTwo = code.toPlainText(textTwo);
		System.out.println(outputTwo);
	}

}
