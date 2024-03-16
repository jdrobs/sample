package tradepackhelper;

import java.util.EnumSet;
import java.util.Iterator;

// Enum to represent the places
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
