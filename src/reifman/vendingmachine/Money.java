package reifman.vendingmachine;

public class Money {

	private int numDollars;
	private int numQuarters;
	private int numDimes;
	private int numNickles;
	
	public Money() {
		
	}
	
	public Money(int numDollars, int numQuarters, int numDimes, int numNickles) {
		this.numDollars = numDollars;
		this.numQuarters = numQuarters;
		this.numDimes = numDimes;
		this.numNickles = numNickles;
	}

	public void add(Money money) {
		setNumQuarters(getNumQuarters() + money.getNumQuarters());
		setNumDollars(getNumDollars() + money.getNumDollars());
		setNumDimes(getNumDimes() + money.getNumDimes());
		setNumNickles(getNumNickles() + money.getNumNickles());
		
	}
	
	public Money remove(double amount) throws NotEnoughChangeException {
		if (amount > getTotal() || (numDimes==0 && numNickles ==0 && numQuarters ==0) || (amount<.1 && numNickles==0)){
			throw new NotEnoughChangeException();
		}
		else{
			//calculating
			int dollars = (int)amount / 1;
			double newAmount = amount - dollars;
			newAmount = Math.round(newAmount * 100.0)/100.0;
			int quarters = (int) (newAmount / .25);
			newAmount = newAmount - (quarters * .25);
			newAmount = Math.round(newAmount * 100.0)/100.0;
			int dimes = (int) (newAmount / .1);
			newAmount = newAmount - (dimes * .1);
			newAmount = Math.round(newAmount * 100.0)/100.0;
			int nickles = (int) (newAmount / .05);
			newAmount = newAmount - (nickles * .05);
			newAmount = Math.round(newAmount * 100.0)/100.0;
			//removing money from the bank
			setNumDollars(getNumDollars() - dollars);
			setNumQuarters(getNumQuarters() - quarters);
			setNumDimes(getNumDimes() - dimes);
			setNumNickles(getNumNickles() - nickles);
			//returning money
			Money remove = new Money(dollars, quarters, dimes, nickles);
			return remove;

		}
	}
	
	public double getTotal() {
		return getNumDollars() + (getNumQuarters() * .25) + (getNumDimes() * .1) + (getNumNickles() * .05);
		
	}

	public int getNumDollars() {
		return numDollars;
	}

	public void setNumDollars(int numDollars) {
		this.numDollars = numDollars;
	}

	public int getNumQuarters() {
		return numQuarters;
	}

	public void setNumQuarters(int numQuarters) {
		this.numQuarters = numQuarters;
	}

	public int getNumNickles() {
		return numNickles;
	}

	public void setNumNickles(int numNickles) {
		this.numNickles = numNickles;
	}

	public int getNumDimes() {
		return numDimes;
	}

	public void setNumDimes(int numDimes) {
		this.numDimes = numDimes;
	}
	
}
