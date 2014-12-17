package reifman.acm2;

import java.util.Scanner;

public class MaxSum {



	public static int maxSubArray(int[][] numbers, int size) {

		int maxSum = -128;
		int sum = 0;
		int temp = 0;
		int temp2 = 0;
		int[] tempArray = new int[size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				
				temp2 = 0;
				tempArray = new int[size];
				for (int k = i; k < size; k++) {
					temp2 += numbers[k][j];
					temp = temp2;
					if (temp > maxSum) {
						maxSum = temp;
					}
					for (int l = j + 1; l < size; l++) {
						tempArray[l] += numbers[k][l];
						temp += tempArray[l];
						if (temp > maxSum) {
							maxSum = temp;
						}
					}
				}
			}
		}

		return maxSum;

	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int size = input.nextInt();
		int max = 0;
		if (size >= 1 && size <= 100) {
			int[][] numbers = new int[size][size];
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					int num = input.nextInt();
					if (num >= -127 && num <= 127) {
						numbers[i][j] = num;
					}

				}
			}
			max = maxSubArray(numbers, size);

		} else {
			System.out.println("Input Error");
			System.exit(0);
		}

		System.out.println(max);

	}

}
