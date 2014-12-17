package reifman.acm2;

import java.util.Scanner;

public class RascalTriangle {

	public static void main(String[] args) {

		Scanner kybd = new Scanner(System.in);
		int dataSets = kybd.nextInt();
		int[] answers= new int[dataSets];

		for (int i = 0; i < dataSets; i++) {
			kybd.next();
			int n = kybd.nextInt();
			int m = kybd.nextInt();
			
			answers[i] = m * (n-m) + 1;
		}
		
		for (int i = 0; i < dataSets; i++) {
			System.out.print(i+1);
			System.out.print(" " + answers[i]);
			System.out.println();
		}

	}

}
