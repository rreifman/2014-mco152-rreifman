package reifman.acm;

import java.util.Scanner;

public class PenneyGame {

	public static int[] play(String input) {
		int[] array = new int[8];
		array[0] = howMany("TTT", input);
		array[1] = howMany("TTH", input);
		array[2] = howMany("THT", input);
		array[3] = howMany("THH", input);
		array[4] = howMany("HTT", input);
		array[5] = howMany("HTH", input);
		array[6] = howMany("HHT", input);
		array[7] = howMany("HHH", input);
		return array;
	}

	public static int howMany(String sequence, String input) {
		int howMany = 0;
		for (int i = 0; i < input.length() - 2; i++) {
			String bit = input.substring(i, i + 3);
			if (bit.equals(sequence)) {
				howMany++;
			}
		}
		return howMany;
	}

	public static void main(String args[]) {

		Scanner input = new Scanner(System.in);
		int dataSets = input.nextInt();
		int[][] output = new int[dataSets][8];
		for (int i = 0; i < dataSets; i++) {
			int dataSet = input.nextInt();
			String userInput = input.next();
			output[i] = play(userInput);

		}
		for (int i = 0; i < dataSets; i++) {
			System.out.print(i + 1);
			for (int j = 0; j < 8; j++) {
				System.out.print(" " + output[i][j]);
			}
			System.out.println();
		}
	}
}
