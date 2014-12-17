package reifman.acm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PenneyGameMap {

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
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		for (int i = 0; i < dataSets; i++) {
			int dataSet = input.nextInt();
			String userInput = input.next();
			map.put("TTT",howMany("TTT", userInput));
			map.put("TTH",howMany("TTH", userInput));
			map.put("THT",howMany("THT", userInput));
			map.put("THH",howMany("THH", userInput));
			map.put("HTT",howMany("HTT", userInput));
			map.put("HTH",howMany("HTH", userInput));
			map.put("HHT",howMany("HHT", userInput));
			map.put("HHH",howMany("HHH", userInput));

			System.out.print(i + 1);
			System.out.print(" " + map.get("TTT") + " " + map.get("TTH") + " " + map.get("THT") + " " 
			+ map.get("THH") + " " + map.get("HTT") + " " + map.get("HTH") + " " + map.get("HHT") + " " +
			map.get("HHH"));

			System.out.println();
		}
	}
}