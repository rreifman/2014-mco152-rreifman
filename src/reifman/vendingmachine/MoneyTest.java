package reifman.vendingmachine;

import org.junit.Assert;
import org.junit.Test;

public class MoneyTest {

	@Test
	public void testGetTotal() {
		Money test = new Money(10, 10, 10, 10);
		Assert.assertEquals(14.00, test.getTotal(), 0);
	}

	@Test
	public void testAddMoney() {
		Money test = new Money(10, 10, 10, 10);
		Money add = new Money(1, 1, 1, 1); // $1.40
		test.add(add);
		Assert.assertEquals(15.40, test.getTotal(), 0);
	}

	@Test
	public void testRemoveMoney() throws NotEnoughChangeException {
		Money test = new Money(10, 10, 10, 10);// $14
		test.remove(2.0);
		Assert.assertEquals(12.00, test.getTotal(), 0);
	}
	
	@Test
	public void testRemoveThrowsNotEnoughChangeException(){
		Money test = new Money(20, 0, 0, 0);
		try{
		test.remove(.05);
		Assert.fail("NotEnoughChangeException should be thrown here");
		}catch(NotEnoughChangeException e){
			
		}
	}
	
	@Test
	public void testOnlyNickels() throws NotEnoughChangeException {
		Money test = new Money(0, 0, 0, 100);
		test.remove(1.4);
		Assert.assertEquals(3.6, test.getTotal(), 0);
	}
	
	@Test
	public void testRemove() throws NotEnoughChangeException {
		Money test = new Money(1, 1, 1, 1);
		test.remove(1.4);
		Assert.assertEquals(0.0, test.getTotal(), 0);
	}


}
