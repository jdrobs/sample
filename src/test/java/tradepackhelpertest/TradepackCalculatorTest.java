package tradepackhelpertest;

import org.junit.Test;

import tradepackhelper.Place;
import tradepackhelper.TradepackCalculator;

import static org.junit.Assert.*;

public class TradepackCalculatorTest {

    @Test
    public void testCalculateBaseValue_SamePlace() {
        TradepackCalculator calc = new TradepackCalculator();
        assertEquals(0.0, calc.calculateBaseValue(Place.MARGROVE, Place.MARGROVE), 0.01);
    }

    @Test
    public void testCalculateBaseValue_DifferentPlaces() {
        TradepackCalculator calc = new TradepackCalculator();
        assertEquals(14790.4, calc.calculateBaseValue(Place.MARGROVE, Place.DARZUAC), 0.01);
    }

    @Test
    public void testCalculateValue_NoModifiers() {
        TradepackCalculator calc = new TradepackCalculator();
        assertEquals(14790.4, calc.calculateValue(Place.MARGROVE, Place.DARZUAC), 0.01);
    }

    @Test
    public void testCalculateValue_Warmode() {
        TradepackCalculator calc = new TradepackCalculator();
        calc.setWarmode(true);
        assertEquals(17748.48, calc.calculateValue(Place.MARGROVE, Place.DARZUAC), 0.01);
    }

    @Test
    public void testCalculateValue_Perk1() {
        TradepackCalculator calc = new TradepackCalculator();
        calc.setPerk1(true);
        assertEquals(15529.92, calc.calculateValue(Place.MARGROVE, Place.DARZUAC), 0.01);
    }

    @Test
    public void testCalculateValue_Perk2() {
        TradepackCalculator calc = new TradepackCalculator();
        calc.setPerk2(true);
        assertEquals(16269.44, calc.calculateValue(Place.MARGROVE, Place.DARZUAC), 0.01);
    }

    @Test
    public void testCalculateValue_MultipleModifiers() {
        TradepackCalculator calc = new TradepackCalculator();
        calc.setWarmode(true);
        calc.setPerk1(true);
        assertEquals(18488.0, calc.calculateValue(Place.MARGROVE, Place.DARZUAC), 0.01);
    }

}
