package tradepackhelper.item;

import java.util.EnumSet;
import java.util.Iterator;

/**
 * The various items that are used in creating the various {@link tradepackhelper.tradepack.Tradepack Tradepacks}. Each item has a price
 * which is how much it costs to purchase it from the market.
 */
public enum Item {
	APPLE			(360),
	STRAWBERRY		(105),
	CHERRY			(255),
	COTTON			(158),
	WOOL			(300),
	HIDE			(330),
	WATERMELON		(509),
	ORANGE			(420),
	GRAPE			(159),
	CABBAGE			(150),
	CORN			(144),
	BLUEBERRY		(41),
	BANANA			(90);
	
	private int price;

	Item(int price) {
        this.price = price;
    }	
	
	public void setPrice(int price) {
		this.price = price;
	}	
	
	public int getPrice() {
		return price;
	}
	
	public static Iterator<Item> getIterator() {
		return EnumSet.allOf(Item.class).iterator();
	}
}
