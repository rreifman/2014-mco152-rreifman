package reifman.vendingmachine;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class InventoryTest {

	@Test
	public void testGetItem() throws IOException{
		Inventory test = new Inventory();
		test.load("inventory.txt");
		Assert.assertNotNull(test.get("A01"));
	}
	
	@Test
	public void testGetItemNull() throws IOException{
		Inventory test = new Inventory();
		test.load("inventory.txt");
		Assert.assertNull(test.get("Ap1"));
	}
	
	@Test
	public void testIsEmpty() throws IOException{
		Inventory test = new Inventory();
		test.load("inventory.txt");
		Assert.assertTrue(test.isEmpty("A01"));
	}
	
	@Test
	public void testAddItem() throws IOException{
		Inventory test = new Inventory();
		test.load("inventory.txt");
		Item item = new Item("F06","Sandwich",3.5, 5 );
		test.add(item);
		Assert.assertEquals(test.get("F06"),item);
	}

	@Test
	public void testRemoveOne() throws IOException{
		Inventory test = new Inventory();
		test.load("inventory.txt");
		test.removeOne("B02");
		test.removeOne("B02");
		test.removeOne("B02");
		Assert.assertTrue(test.isEmpty("B02"));
	}

}
