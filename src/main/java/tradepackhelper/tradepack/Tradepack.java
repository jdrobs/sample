package tradepackhelper.tradepack;

import java.util.HashMap;
import java.util.Map;

public abstract class Tradepack {
	Map<Item, Integer> items = null;
	int cost;
	
	//get the cost of making the tradepack given the current market prices
	public int getCost() {
		int totalPrice = 0;
		
		//iterate through the items in the pack, adding the product of the 
		//quantity and the price of that item
        for (HashMap.Entry<Item, Integer> entry : items.entrySet()) {
            Item item = entry.getKey();
            int quantity = entry.getValue();
            totalPrice += item.getPrice() * quantity;
        }
        
        return totalPrice;
	}
}
