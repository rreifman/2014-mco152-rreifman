package reifman.acm2;

import java.util.ArrayList;
import java.util.Scanner;

public class HappyPrimes {

	public static boolean isPrime(int number) {
		boolean isPrime = true;
		for (int i = 2; i < number; i++) {
			if (number % i == 0) {
				isPrime = false;
				break;
			}

		}
		if (number == 1) {
			isPrime = false;
		}
		return isPrime;
	}

	public static boolean isHappy(int number) {
		boolean isHappy = false;

		ArrayList<Integer> digits = new ArrayList<Integer>();
		int counter = 0;

		while (!isHappy && counter < 10) {
			int answer = 0;

			digits = new ArrayList<Integer>();
			while (number > 0) {
				digits.add(number % 10);
				number = number / 10;
			}

			for (int i = 0; i < digits.size(); i++) {
				int num = digits.get(i);
				answer += num * num;
			}
			if (answer == 1) {
				isHappy = true;
				break;
			}

			number = answer;
			counter++;
		}

		return isHappy;
	}

	public static void main(String args[]) {
		Scanner kybd = new Scanner(System.in);
		int dataSets = kybd.nextInt();

		int[] outputNums = new int[dataSets];
		String[] outputStrings = new String[dataSets];

		for (int i = 0; i < dataSets; i++) {

			kybd.nextLine();
			kybd.next();

			int num = kybd.nextInt();

			outputNums[i] = num;
			boolean isPrime = isPrime(num);
			boolean isHappy = isHappy(num);
			if (isPrime && isHappy) {
				outputStrings[i] = "YES";
			} else {
				outputStrings[i] = "NO";
			}

		}

		for (int i = 0; i < dataSets; i++) {
			System.out.print(i + 1);
			System.out.print(" " + outputNums[i]);
			System.out.print(" " + outputStrings[i]);
			System.out.println();
		}
	}
}
