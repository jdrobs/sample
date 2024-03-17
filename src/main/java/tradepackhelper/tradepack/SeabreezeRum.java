package tradepackhelper.tradepack;

import java.util.HashMap;
import java.util.Map;

import tradepackhelper.item.Item;

/**
 * Seabreeze Rum {@link Tradepack}
 */
public class SeabreezeRum extends Tradepack {

	public SeabreezeRum() {
		Map<Item, Integer> contents = new HashMap<Item, Integer>();
		contents.put(Item.CORN, 10);
		contents.put(Item.CABBAGE, 8);
		contents.put(Item.BLUEBERRY, 30);
		contents.put(Item.BANANA, 5);
		this.items = contents;
		
	}

}
