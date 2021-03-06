package com.rook12.stockexchange.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class PreferredStockTest {

    @Test
    public void getDividendYield() {
        PreferredStock preferredStock = new PreferredStock("TEST", 100, 200, new BigDecimal("0.02"));
        BigDecimal dividendYield = preferredStock.getDividendYield(300);
        assertEquals(new BigDecimal("0.01333"), dividendYield);
    }

    @Test
    public void getDividendYieldGIN() {
        PreferredStock preferredStock = new PreferredStock("GIN", 8, 100, new BigDecimal("0.02"));
        BigDecimal dividendYield = preferredStock.getDividendYield(120);
        assertEquals(new BigDecimal("0.01667"), dividendYield);
    }

    @Test
    public void getPeRatioPreferredStock() {
        PreferredStock preferredStock = new PreferredStock("GIN", 8, 100, new BigDecimal("0.02"));
        int marketPrice = 110;
        assertEquals(new BigDecimal("0.01818"), preferredStock.getDividendYield(marketPrice));
        assertEquals(new BigDecimal("13.75000"), preferredStock.getPeRatio(marketPrice));
    }

    @Test
    public void getStockType() {
        PreferredStock preferredStock = new PreferredStock("GIN", 8, 100, new BigDecimal("0.02"));
        assertEquals("PREFERRED", preferredStock.getStockType());
    }
}