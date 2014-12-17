package reifman.vendingmachine;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class RunVendingMachine {

	// print inventory
	// make selection
	// input money
	// input selection
	// output change
	public static void main(String[] args) throws IOException,
			CodeNotFoundException, NotEnoughPaidException,
			NotEnoughChangeException {
		Scanner keyboard = new Scanner(System.in);
		String choice = " ";
		DecimalFormat formatter = new DecimalFormat("$0.00");
		Inventory inventory = new Inventory();
		inventory.load("inventory.txt");
		Money bank = new Money(10, 10, 10, 10);
		VendingMachine vend = new VendingMachine(inventory, bank);
		double paid = 0;
		Money change = new Money();
		String code = " ";
		Item item = inventory.get("E05");
		boolean dispense = false;
		System.out.println(inventory.toString());
		System.out.println("Add Money/Make Selection?");
		System.out.println("1. Dollar");
		System.out.println("2. Quarter");
		System.out.println("3. Dime");
		System.out.println("4. Nickle");
		System.out.println("or enter in the Item Code");

		while (!dispense) {

			choice = keyboard.nextLine();

			if ("1".equals(choice)) {
				Money dollar = new Money(1, 0, 0, 0);
				paid = vend.pay(dollar);
				System.out.println("Balance " + formatter.format(paid));

			}

			else if ("2".equals(choice)) {
				Money quarter = new Money(0, 1, 0, 0);
				paid = vend.pay(quarter);
				System.out.println("Balance " + formatter.format(paid));

			} else if ("3".equals(choice)) {
				Money dime = new Money(0, 0, 1, 0);
				paid = vend.pay(dime);
				System.out.println("Balance " + formatter.format(paid));
			} else if ("4".equals(choice)) {
				Money nickle = new Money(0, 0, 0, 1);
				paid = vend.pay(nickle);
				System.out.println("Balance " + formatter.format(paid));
			} else {
				code = choice.toUpperCase();

				try {

					change = vend.buy(code);
					item = inventory.get(code);
					if (paid >= item.getPrice()) {
						dispense = true;
					}

				} catch (CodeNotFoundException ex1) {
					System.out.println("Code Not Found");
				} catch (NotEnoughPaidException ex2) {
					System.out.println("Not Enough Paid");
				} catch (NotEnoughChangeException ex3) {
					System.out.println("Not Enough Change");
				}

			}

		}

		item = inventory.get(code);
		System.out.println("Dispensing " + item.getName());
		if (change != null) {
			System.out.println("Change " + formatter.format(change.getTotal()));
		}
	}

}
