package reifman.vendingmachine;

public class VendingMachine {

	private Inventory inventory;
	private Money bank;

	/**
	 * The amount of money the person has put into the Vending Machine so far
	 */
	private Money paid;

	public VendingMachine(Inventory inventory, Money bank) {
		this.inventory = inventory;
		this.bank = bank;
		paid = new Money();
	}

	public Inventory getInventory() {
		return inventory;
	}

	public Money getBank() {
		return bank;
	}

	public Money getPaid() {
		return paid;
	}

	/**
	 * Add additional Money to the machine
	 * 
	 * @param additional
	 * @return the amount that the person has put into the machine
	 */
	public double pay(Money additional) {
		paid.add(additional);
		return paid.getTotal();
	}

	/**
	 * 
	 * @param code
	 * @return the amount of change as a Money object
	 * @throws CodeNotFoundException
	 *             if there is no item with that code
	 * @throws NotEnoughPaidException
	 *             if paid is not enough to buy the item
	 * @throws NotEnoughChangeException
	 *             if the transaction cannot be completed because there isn't
	 *             enough money in the vending machine for the change
	 */
	public Money buy(String code) throws CodeNotFoundException,
			NotEnoughPaidException, NotEnoughChangeException {

		Item item = inventory.get(code);
		if (item == null) {
			throw new CodeNotFoundException();
		}
		if ((paid.getTotal()) < (item.getPrice())) {
			throw new NotEnoughPaidException();
		} else if (paid.getTotal() >= item.getPrice()) {
			double changeDec = paid.getTotal() - item.getPrice();
			if (changeDec > bank.getTotal() || (bank.getNumDimes()==0 && bank.getNumNickles()==0 && bank.getNumQuarters()==0 && changeDec>0)) {
				throw new NotEnoughChangeException();
			}
			inventory.removeOne(code);
			bank.add(paid);
			Money changeMon = bank.remove(changeDec);
			bank.remove(changeDec);
			paid = new Money();
			return changeMon;
			
		}
		return null;
	}

}
