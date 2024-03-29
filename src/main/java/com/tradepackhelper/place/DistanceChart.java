package com.tradepackhelper.place;

import java.util.EnumMap;

/**
 * Utility class that stores the various distances between the {@link Place Places}
 */
public class DistanceChart {

	//Map to store distances
	private EnumMap<Place, EnumMap<Place, Integer>> distances;

	
	/**
	 * DistanceChart has the EnumMap that contains distances between the {@link Place Places}. When 
	 * adding a new place, only one direction of distances is needed as the distance 
	 * from A to B is equal to the distance from B to A.
	 */
	public DistanceChart() {
		setDistances(new EnumMap<>(Place.class));
		for (Place from : Place.values()) {
			EnumMap<Place, Integer> innerMap = new EnumMap<>(Place.class);
			for (Place to : Place.values()) {
				//Initialize distances with 0
				innerMap.put(to, 0);
			}
			getDistances().put(from, innerMap);
		}

		//Populate distances with actual values
		//Since the distance is the same regardless of direction, only one distance pair needs
		//to be formally set and we can take the max of the desired route and the reverse of it
		
		// route starting from margrove
		getDistances().get(Place.MARGROVE).put(Place.RAVENCREST, 234);
		getDistances().get(Place.MARGROVE).put(Place.RIVERREND, 584);
		getDistances().get(Place.MARGROVE).put(Place.DARZUAC, 811);
		getDistances().get(Place.MARGROVE).put(Place.DEFIANCE, 945);

		//starting from ravencrest
		getDistances().get(Place.RAVENCREST).put(Place.RIVERREND, 412);
		getDistances().get(Place.RAVENCREST).put(Place.DARZUAC, 577);
		getDistances().get(Place.RAVENCREST).put(Place.DEFIANCE, 773);

		//starting from riverrend
		getDistances().get(Place.RIVERREND).put(Place.DARZUAC, 535);
		getDistances().get(Place.RIVERREND).put(Place.DEFIANCE, 360);
		
		//starting from darzuac
		getDistances().get(Place.DARZUAC).put(Place.DEFIANCE, 486);
		
		//NOTE: to add new places, you just need to set the distances from the new 
		//place to all of the current places. if there are n current places, you'll
		//need n distances.get(Place.NEWPLACE).put(Place.SOMECURRENTPLACE, distance)
		

	}

	/**
	 * Gets the distance between two {@link Place Places}. 
	 * @param from - the {@link Place} where the {@link com.tradepackhelper.tradepack.Tradepack Tradepack} is starting from
	 * @param to - the {@link Place} where the {@link com.tradepackhelper.tradepack.Tradepack Tradepack} will be sold
	 * @return int that represents the amount of tiles between the two {@link Place Places}
	 */
	public int getDistance(Place from, Place to) {
		if (from == null || to == null) 
			return 0;
		return Math.max(getDistances().get(from).get(to), getDistances().get(to).get(from));
	}

	public EnumMap<Place, EnumMap<Place, Integer>> getDistances() {
		return distances;
	}

	public void setDistances(EnumMap<Place, EnumMap<Place, Integer>> distances) {
		this.distances = distances;
	}

}