package tradepackhelper.tradepack;

import java.util.HashMap;
import java.util.Map;

import tradepackhelper.item.Item;

/**
 * Sajecho Fruit Basket {@link Tradepack}
 */
public class SajechoFruitBasket extends Tradepack {

	public SajechoFruitBasket() {
		Map<Item, Integer> contents = new HashMap<Item, Integer>();
		contents.put(Item.BANANA, 2);
		contents.put(Item.WATERMELON, 1);
		contents.put(Item.ORANGE, 4);
		contents.put(Item.GRAPE, 10);
		this.items = contents;
		
	}

}
