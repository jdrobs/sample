package tradepackhelper.runner;

import java.io.IOException;
import java.util.Iterator;

import tradepackhelper.item.Item;
import tradepackhelper.item.PriceLoader;
import tradepackhelper.place.Place;
import tradepackhelper.tradepack.JuicersBox;
import tradepackhelper.tradepack.RavencrestFinestWares;
import tradepackhelper.tradepack.SajechoFruitBasket;
import tradepackhelper.tradepack.SeabreezeRum;
import tradepackhelper.tradepack.TradepackCalculator;

public class Runner {

	static PriceLoader loader;
	static TradepackCalculator calc;

	// TODO: Load the config below from a config or settings file

	// modifiers for the starting, ending locations and how much profit
	static Place start = Place.MARGROVE;
	static Place end = Place.DEFIANCE;
	static int minProfit = 3000;

	// modifiers for the value of tradepacks
	static boolean warmode = false;
	static boolean perk1 = false;
	static boolean perk2 = false;

	public static void main(String[] args) {

		// initialize the utilities
		loader = new PriceLoader();
		calc = new TradepackCalculator();

		// update the prices based on the itemprices.xlsx spreadsheet
		try {
			loader.reorderColumnsAlphabetically();
			Iterator<Item> items = Item.getIterator();
			while (items.hasNext()) {
				Item item = items.next();
				item.setPrice(loader.loadPrice(item.name()));
			}
		} catch (IOException e) {
			System.out.println("Error with loading prices from spreadsheet");
			e.printStackTrace();
		}

		// For each of the packs, calculate the min demand needed to reach the
		// desired profit from the start to the end and print the results out
		System.out.println("Juicers Box: \t\t\t" + calc.calculateMinDemandForProfit(minProfit, new JuicersBox(), start, end));
		System.out.println("Ravencrest Finest Wares: \t" + calc.calculateMinDemandForProfit(minProfit, new RavencrestFinestWares(), start, end));
		System.out.println("Sajecho Fruit Basket: \t\t" + calc.calculateMinDemandForProfit(minProfit, new SajechoFruitBasket(), start, end));
		System.out.println("Seabreeze Rum: \t\t\t" + calc.calculateMinDemandForProfit(minProfit, new SeabreezeRum(), start, end));
		System.out.println();
		
		// Output how much profit if demand is 88 for juicers box
		System.out.println("Juicers Box Profit: " + calc.calculateProfit(88, new JuicersBox(), start, end));
		
	}
}
