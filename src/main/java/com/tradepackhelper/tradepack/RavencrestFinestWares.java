package com.tradepackhelper.tradepack;

import java.util.HashMap;
import java.util.Map;

import com.tradepackhelper.item.Item;

/**
 * Ravencrest Finest Wares {@link Tradepack}
 */
public class RavencrestFinestWares extends Tradepack {

	public RavencrestFinestWares() {
		Map<Item, Integer> contents = new HashMap<Item, Integer>();
		contents.put(Item.WOOL, 8);
		contents.put(Item.COTTON, 8);
		contents.put(Item.HIDE, 8);
		this.items = contents;
		
	}

}
