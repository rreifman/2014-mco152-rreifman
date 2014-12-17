package reifman.acm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RepeatingCharacters {

	public static boolean checkIfValid(char letter) {
		List<Character> alphanumericList = new ArrayList<Character>();
		char[] alphanumericArray = new char[] { '1', '2', '3', '4', '5', '6',
				'7', '8', '9', '0', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
				'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
				'U', 'V', 'W', 'X', 'Y', 'Z', '$', '%', '*', '+', '-', '.',
				'/', ':' };
		for (int i = 0; i < alphanumericArray.length; i++) {
			alphanumericList.add(alphanumericArray[i]);
		}
		return alphanumericList.contains(letter);
	}

	public static String returnString(String input) throws InvalidDataException {
		StringBuilder output = new StringBuilder();

		Scanner in = new Scanner(input);
		in.next();
		int count = in.nextInt();
		
		String chars = in.next();

		
		if (count == -1) {
			throw new InvalidDataException();
		}

		for (int i = 0; i < chars.length(); i++) {
			if (checkIfValid(chars.charAt(i))) {
				for (int j = 0; j < count; j++) {
					output.append(chars.charAt(i));
				}
			} else {
				throw new InvalidDataException();
			}
		}

		return output.toString();
	}

	public static void main(String[] args) throws InvalidDataException {
		// TODO Auto-generated method stub

		Scanner kybd = new Scanner(System.in);
		int dataSets = kybd.nextInt();
		ArrayList<String> outputList = new ArrayList<String>();
		kybd.nextLine();

		for (int i = 0; i < dataSets; i++) {
			String input = kybd.nextLine();
			outputList.add(returnString(input));
		}

		for (int i = 0; i < dataSets; i++) {
			System.out.print(i + 1);
			System.out.print(" " + outputList.get(i));
			System.out.println();
		}

	}

}
