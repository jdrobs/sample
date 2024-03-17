package com.tradepackhelper.tradepack;

import java.util.HashMap;
import java.util.Map;

import com.tradepackhelper.item.Item;

/**
 * Abstract class representing tradepacks. Contains a list of {@link com.tradepackhelper.item.Item Items}
 * and a method of getting the cost of the tradepack given current prices.
 */
public abstract class Tradepack {
	Map<Item, Integer> items = null;

	/**
	 * Gets the cost of making the tradepack given the current prices of the {@link com.tradepackhelper.item.Item Items}. 
	 * Sums the total of item prices * quantity of those items.
	 * @return int representing the cost
	 */
	public int getCost() {
		int totalPrice = 0;

		// iterate through the items in the pack, adding the product of the
		// quantity and the price of that item
		for (HashMap.Entry<Item, Integer> entry : items.entrySet()) {
			Item item = entry.getKey();
			int quantity = entry.getValue();
			totalPrice += item.getPrice() * quantity;
		}

		return totalPrice;
	}
}
