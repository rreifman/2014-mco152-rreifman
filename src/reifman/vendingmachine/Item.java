package reifman.vendingmachine;

import java.text.DecimalFormat;

public class Item {

	private String code;
	private String name;
	private double price;
	private int quantity;

	public Item(String code, String name, double price, int quantity) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int amount) {
		quantity = amount;
	}

	public String toString() {
		DecimalFormat formatter = new DecimalFormat("$0.00");
		return (code + " " + name + " @ " + formatter.format(price) + " x " + quantity);
	}

}
