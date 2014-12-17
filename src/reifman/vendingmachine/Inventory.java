package reifman.vendingmachine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory {
	private ArrayList<Item> items;
	private Map<String,Item> itemsMap;

	public Inventory() {
		items = new ArrayList<Item>();
		itemsMap = new HashMap<String,Item>();
	}

	public void load(String inventoryFilename) throws IOException {

		Scanner input = new Scanner(new File(inventoryFilename));
		while (input.hasNextLine()) {
			String nextLine = input.nextLine();
			String[] array = nextLine.split(",");
			String code = array[0];
			String name = array[1];
			double price = Double.parseDouble(array[2]);
			int quantity = Integer.parseInt(array[3]);
			Item newItem = new Item(code, name, price, quantity);
			items.add(newItem);
			itemsMap.put(code,newItem);
		}
	}

	/**
	 * 
	 * @param code
	 * @return the item or null if an item with that code doesn't exist
	 */
	public Item get(String code) {
		if(itemsMap.containsKey(code)) {
			return itemsMap.get(code);
		}
		else{
			return null;
		}

	}

	/**
	 * 
	 * @param item
	 *            to add
	 */
	public void add(Item item) {
		items.add(item);
		itemsMap.put(item.getCode(),item);
	}

	/**
	 * Removes one from quantity of the specified item
	 * 
	 * @param code
	 */
	public void removeOne(String code) {
		for (Item x: items) {
			if (x.getCode().equalsIgnoreCase(code)) {
				x.setQuantity(x.getQuantity() - 1);	
			}

		}
	}

	/**
	 * 
	 * @param code
	 * @return true if the Item exists and there is at least one quantity,
	 *         otherwise false.
	 */
	public boolean isEmpty(String code) {
		boolean exists = true;
		if (itemsMap.get(code).getQuantity() > 0) {
			exists = false;
		}
		return true;
	}

	/**
	 * Lists the items in the inventory one per line in the format code name @
	 * price x quantity\n
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Item x : items) {
			builder.append(x.toString());
			builder.append("\n");
		}
		return builder.toString();
	}

}
