package com.tradepackhelpertest;

import org.junit.Before;
import org.junit.Test;

import com.tradepackhelper.item.PriceLoader;

import java.io.IOException;
import static org.junit.Assert.*;

public class PriceLoaderTest {

    private PriceLoader priceLoader;
    private String testFilePath = "src/test/resources/testdata.xlsx"; // Replace with your test data file path

    @Before
    public void setUp() throws IOException {
        priceLoader = new PriceLoader(testFilePath);
    }

    @Test
    public void testLoadPrice_ExistingItem() throws IOException {
        int price = priceLoader.loadPrice("Apple");
        assertEquals(1111, price); 
    }

    @Test
    public void testLoadPrice_NonExistingItem() throws IOException {
        int price = priceLoader.loadPrice("Wood");
        assertEquals(-1, price);
    }


    @Test(expected = IOException.class)
    public void testLoadPrice_InvalidFile() throws IOException {
        priceLoader.setFilePath("invalid_file.xlsx");
        priceLoader.loadPrice("fail");
    }

    
}
