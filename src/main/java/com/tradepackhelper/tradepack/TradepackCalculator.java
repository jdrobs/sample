package com.tradepackhelper.tradepack;

import com.tradepackhelper.place.DistanceChart;
import com.tradepackhelper.place.Place;

/**
 * Utility class that provides the ability to do calculations for 
 * {@link com.tradepackhelper.tradepack.Tradepack Tradepacks}
 * based either on demand or on minimum profit desired.
 */
public class TradepackCalculator {

	// modifiers for the value of tradepacks
	private boolean warmode = false;
	private boolean perk1 = false;
	private boolean perk2 = false;

	DistanceChart dist = new DistanceChart();

	/**
	 * Calculates the base value of the {@link Tradepack} which is the price a pack would sell
	 * for with no modifiers given the distance between to starting and ending {@link com.tradepackhelper.place.Place Places}.
	 * If the start and end are equal, the result is 0
	 * @param from - starting {@link com.tradepackhelper.place.Place Place} for {@link Tradepack}
	 * @param to - ending {@link com.tradepackhelper.place.Place Place} for {@link Tradepack}
	 * @return double representing the value
	 */
	public double calculateBaseValue(Place from, Place to) {
		if (from.equals(to))
			return 0;

		return 0.8 * ((8 * dist.getDistance(from, to)) + 12000);
	}

	/**
	 * Calculates the value of the {@link Tradepack} which is the price a pack would sell
	 * for with modifiers given the distance between to starting and ending {@link com.tradepackhelper.place.Place Places}.
	 * If the start and end are equal, the result is 0
	 * @param from - starting {@link com.tradepackhelper.place.Place Place} for {@link Tradepack}
	 * @param to - ending {@link com.tradepackhelper.place.Place Place} for {@link Tradepack}
	 * @return double representing the value
	 */
	public double calculateValue(Place from, Place to) {
		if (from.equals(to))
			return 0;

		double base = calculateBaseValue(from, to);

		// the multiplier is default 1. if any bonuses are active,
		// they are added to the default
		double multiplier = 1.00;
		if (isWarmode())
			multiplier += 0.20;
		if (isPerk1())
			multiplier += 0.05;
		if (isPerk2())
			multiplier += 0.10;

		return multiplier * base;
	}

	/**
	 * profit is calculated as demand times value minus the cost to create
	 * @param demand - the demand for the current {@link Tradepack} 
	 * at the ending {@link com.tradepackhelper.place.Place Place}
	 * @param pack - the {@link Tradepack} to do the calculations for
	 * @param from - starting {@link com.tradepackhelper.place.Place Place} for {@link Tradepack}
	 * @param to - ending {@link com.tradepackhelper.place.Place Place} for {@link Tradepack}
	 * @return int representing how much profit 
	 */
	public int calculateProfit(int demand, Tradepack pack, Place from, Place to) {
		return (int) Math.floor(((demand * calculateValue(from, to)) / 100) - pack.getCost());
	}

	// 
	/**
	 * calculate how much demand is needed to give us at least the profit desired
	 * @param profit - the desired amount of profit
	 * @param pack - the {@link Tradepack} to to the calculations for
	 * @param from - starting {@link com.tradepackhelper.place.Place Place} for {@link Tradepack}
	 * @param to - ending {@link com.tradepackhelper.place.Place Place} for {@link Tradepack}
	 * @return int representing how much demand results in specified profit
	 */
	public int calculateMinDemandForProfit(int profit, Tradepack pack, Place from, Place to) {
		return (int) Math.round(100 * (profit + pack.getCost()) / calculateValue(from, to));
	}

	public TradepackCalculator() {

	}

	public boolean isWarmode() {
		return warmode;
	}

	public void setWarmode(boolean warmode) {
		this.warmode = warmode;
	}

	public boolean isPerk1() {
		return perk1;
	}

	public void setPerk1(boolean perk1) {
		this.perk1 = perk1;
	}

	public boolean isPerk2() {
		return perk2;
	}

	public void setPerk2(boolean perk2) {
		this.perk2 = perk2;
	}
}
