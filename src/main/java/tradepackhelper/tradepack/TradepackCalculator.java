package tradepackhelper.tradepack;

import tradepackhelper.place.DistanceChart;
import tradepackhelper.place.Place;

public class TradepackCalculator {

	// modifiers for the value of tradepacks
	private boolean warmode = false;
	private boolean perk1 = false;
	private boolean perk2 = false;

	DistanceChart dist = new DistanceChart();

	// calculate the base value of the pack from starting to ending
	// if start and end location are the same, value is 0
	public double calculateBaseValue(Place from, Place to) {
		if (from.equals(to))
			return 0;

		return 0.8 * ((8 * dist.getDistance(from, to)) + 12000);
	}

	// calculates the value of the pack from starting to ending
	// if any multipliers are active, it factors those in before the return
	// if start and end location are the same, value is 0
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

	// profit is calculated as demand times value minus the cost to create
	public int calculateProfit(int demand, Tradepack pack, Place from, Place to) {
		return (int) Math.floor(((demand * calculateValue(from, to)) / 100) - pack.getCost());
	}

	// calculate how much demand is needed to give us at least the profit desired
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
