package tradepackhelper.tradepack;

import java.util.HashMap;
import java.util.Map;

import tradepackhelper.item.Item;

/**
 * Juicers Box {@link Tradepack}
 */
public class JuicersBox extends Tradepack {

	public JuicersBox() {
		Map<Item, Integer> contents = new HashMap<Item, Integer>();
		contents.put(Item.APPLE, 5);
		contents.put(Item.STRAWBERRY, 15);
		contents.put(Item.CHERRY, 5);
		contents.put(Item.BANANA, 3);
		this.items = contents;

	}

}
