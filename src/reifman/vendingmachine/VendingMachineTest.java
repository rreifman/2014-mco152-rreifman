package reifman.vendingmachine;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class VendingMachineTest {

	@Test
	public void testPaid() throws IOException {
		Inventory testInv = new Inventory();
		testInv.load("inventory.txt");
		Money bank = new Money(10, 10, 10, 10);
		VendingMachine test = new VendingMachine(testInv, bank);
		Money pay = new Money(2, 0, 1, 0);
		test.pay(pay);
		Assert.assertEquals(pay.getTotal(), test.getPaid().getTotal(), 0);
		Assert.assertEquals(2.1, test.getPaid().getTotal(), 0);
	}

	@Test
	public void testBuyOne() throws CodeNotFoundException,
			NotEnoughPaidException, NotEnoughChangeException, IOException {
		Inventory testInv = new Inventory();
		testInv.load("inventory.txt");
		Money bank = new Money(10, 10, 10, 10);
		VendingMachine test = new VendingMachine(testInv, bank);
		Money pay = new Money(2, 0, 1, 0);
		test.pay(pay);
		Money change = test.buy("B02");
		Assert.assertEquals(.80, change.getTotal(), 0);
		Assert.assertEquals(0.0, test.getPaid().getTotal(), 0);
		Assert.assertEquals(2, testInv.get("B02").getQuantity());
	}

	@Test
	public void testBuyTwo() throws CodeNotFoundException,
			NotEnoughPaidException, NotEnoughChangeException, IOException {
		Inventory testInv = new Inventory();
		testInv.load("inventory.txt");
		Money bank = new Money(0, 0, 0, 100);
		VendingMachine test = new VendingMachine(testInv, bank);
		Money pay = new Money(2, 0, 1, 0);
		test.pay(pay);
		Money change = test.buy("B02");
		Assert.assertEquals(.80, change.getTotal(), 0);
	}

	@Test
	public void testCodeNotFoundException() throws CodeNotFoundException,
			NotEnoughPaidException, NotEnoughChangeException, IOException {
		Inventory testInv = new Inventory();
		testInv.load("inventory.txt");
		Money bank = new Money(10, 10, 10, 10);
		VendingMachine test = new VendingMachine(testInv, bank);
		Money pay = new Money(2, 0, 1, 0);
		test.pay(pay);
		try {
			test.buy("b00");
			Assert.fail("CodeNotFoundException should be thrown here");
		} catch (CodeNotFoundException e) {

		}
	}
	
	@Test
	public void testNotEnoughPaidException() throws CodeNotFoundException,
			NotEnoughPaidException, NotEnoughChangeException, IOException {
		Inventory testInv = new Inventory();
		testInv.load("inventory.txt");
		Money bank = new Money(10, 10, 10, 10);
		VendingMachine test = new VendingMachine(testInv, bank);
		Money pay = new Money(1, 0, 1, 0);
		test.pay(pay);
		try {
			test.buy("A01");
			Assert.fail("NotEnoughPaidException should be thrown here");
		} catch (NotEnoughPaidException e) {

		}
	}
	
	@Test
	public void testNotEnoughChangeException() throws CodeNotFoundException,
			NotEnoughPaidException, NotEnoughChangeException, IOException {
		Inventory testInv = new Inventory();
		testInv.load("inventory.txt");
		Money bank = new Money(0, 0, 1, 1);
		VendingMachine test = new VendingMachine(testInv, bank);
		Money pay = new Money(2, 0, 1, 0);
		test.pay(pay);
		try {
			test.buy("A01");
			Assert.fail("NotEnoughChangeException should be thrown here");
		} catch (NotEnoughChangeException e) {

		}
	}
	
	@Test
	public void testNotEnoughChangeExceptionOnlyDollars() throws CodeNotFoundException,
			NotEnoughPaidException, NotEnoughChangeException, IOException {
		Inventory testInv = new Inventory();
		testInv.load("inventory.txt");
		Money bank = new Money(10, 0, 0, 0);
		VendingMachine test = new VendingMachine(testInv, bank);
		Money pay = new Money(2, 0, 1, 0);
		test.pay(pay);
		try {
			test.buy("A01");
			Assert.fail("NotEnoughChangeException should be thrown here");
		} catch (NotEnoughChangeException e) {

		}
	}

	@Test
	public void testNullPointer() throws IOException, CodeNotFoundException, NotEnoughPaidException, NotEnoughChangeException {
		Inventory testInv = new Inventory();
		testInv.load("inventory.txt");
		Money bank = new Money(10, 10, 10, 10);
		VendingMachine test = new VendingMachine(testInv, bank);
		Money pay = new Money(1, 0, 0, 0);
		test.pay(pay);
		
		Money change = test.buy("C03");
		Assert.assertEquals(0.0, change.getTotal(), 0);
				
	}
	@Test
	public void testPay() throws CodeNotFoundException, NotEnoughPaidException, NotEnoughChangeException{
		Inventory inventory = new Inventory();
		Money bank = new Money();
		Item item = new Item("A01", "Candy", 1.00, 2);
		inventory.add(item);
		VendingMachine machine = new VendingMachine(inventory, bank);
		machine.pay(new Money(1, 0, 0, 0));
		Money change = machine.buy("A01");
		System.out.println(change);
		Assert.assertEquals(0.0, change.getTotal(), 0);
		
	}
	

	/*
	 * @Test public void testBuy() throws CodeNotFoundException,
	 * NotEnoughPaidException, NotEnoughChangeException, IOException{ Inventory
	 * testInv = new Inventory(); testInv.load("inventory.txt"); Money bank =
	 * new Money(10,10,10,10); VendingMachine test = new VendingMachine(testInv,
	 * bank); Money pay = new Money(2,0,0,0); test.pay(pay);
	 * Assert.assertNotNull(test.buy("A01")); }
	 * 
	 * @Test public void testBuyDunno() throws CodeNotFoundException,
	 * NotEnoughPaidException, NotEnoughChangeException, IOException{ Inventory
	 * testInv = new Inventory(); testInv.load("inventory.txt"); Money bank =
	 * new Money(10,10,10,10); VendingMachine test = new VendingMachine(testInv,
	 * bank); Money pay = new Money(1,2,0,1); test.pay(pay);
	 * Assert.assertNull(test.buy("A01")); }
	 */
}
