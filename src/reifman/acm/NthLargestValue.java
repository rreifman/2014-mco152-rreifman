package reifman.acm;

import java.util.Arrays;
import java.util.Scanner;

public class NthLargestValue {

	public static int get3rdLastValue(String input) throws InvalidDataException {
		String[] arrayString = input.split(" ");
		int[] arrayNum = new int[10];
		int counter = 0;
		for (int i = 1; i < arrayString.length; i++) {
			if (arrayString[i] != null && (!"0".equals(arrayString[i]))
					&& (!"".equals(arrayString[i]))) {
				int num = Integer.parseInt(arrayString[i]);
				if (num < 1 || num > 1000) {
					throw new InvalidDataException();
				}
				arrayNum[counter++] = num;
			}

		}
		Arrays.sort(arrayNum);

		return arrayNum[7];

	}

	public int[] sort(int[] numbers) {
		return numbers;
	}

	public static void main(String args[]) throws InvalidDataException {
		Scanner kybd = new Scanner(System.in);
		int dataSets = kybd.nextInt();
		int[] output = new int[dataSets];
		kybd.next();
		for (int i = 0; i < dataSets; i++) {
			String input = kybd.nextLine();
			output[i] = get3rdLastValue(input);
		}
		for (int i = 0; i < dataSets; i++) {
			System.out.print(i + 1);
			System.out.print(" " + output[i] + "\n");
		}
	}
}
