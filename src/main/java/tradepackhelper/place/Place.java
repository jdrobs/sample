package tradepackhelper.place;

import java.util.EnumSet;
import java.util.Iterator;

/**
 * The various places where a {@link tradepackhelper.tradepack.Tradepack Tradepack} can be created and delivered.
 */
public enum Place {
	MARGROVE, 
	RAVENCREST, 
	RIVERREND, 
	DARZUAC, 
	DEFIANCE;
	
	public static Iterator<Place> getIterator() {
		return EnumSet.allOf(Place.class).iterator();
	}
}
