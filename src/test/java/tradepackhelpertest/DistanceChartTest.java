package tradepackhelpertest;

import org.junit.Test;

import tradepackhelper.DistanceChart;
import tradepackhelper.Place;

import static org.junit.Assert.*;

import java.util.EnumMap;

public class DistanceChartTest {

    @Test
    public void testInitialization() {
        DistanceChart chart = new DistanceChart();
        assertNotNull(chart.getDistances()); // Check if the distances map is initialized
        for (Place from : Place.values()) {
            EnumMap<Place, Integer> innerMap = chart.getDistances().get(from);
            assertNotNull(innerMap); // Check if each inner map is initialized
            
        }
    }

    @Test
    public void testGetDistance() {
        DistanceChart chart = new DistanceChart();

        // Test specific distance values
        assertEquals(234, chart.getDistance(Place.MARGROVE, Place.RAVENCREST));
        assertEquals(412, chart.getDistance(Place.RAVENCREST, Place.RIVERREND));
        assertEquals(945, chart.getDistance(Place.MARGROVE, Place.DEFIANCE));
        assertEquals(486, chart.getDistance(Place.DARZUAC, Place.DEFIANCE));
        assertEquals(584, chart.getDistance(Place.MARGROVE, Place.RIVERREND)); // Test reverse direction
    }

    @Test
    public void testGetDistanceInvalidPlace() {
        DistanceChart chart = new DistanceChart();
        assertEquals(0, chart.getDistance(null, Place.RAVENCREST)); // Test with null
    }
}