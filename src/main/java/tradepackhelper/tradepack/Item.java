package tradepackhelper.tradepack;

import java.util.EnumSet;
import java.util.Iterator;

public enum Item {
	//TODO: have the prices of the items populate from either a config/excel file that
	//is easier to read and doesnt require changing code or maybe input from a gui
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
	
	private final int price;

	Item(int price) {
        this.price = price;
    }	
	
	public int getPrice() {
		return price;
	}
	
	public static Iterator<Item> getIterator() {
		return EnumSet.allOf(Item.class).iterator();
	}
}
