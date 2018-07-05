package com.rook12.stockexchange.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CommonStockTest {

    @Test
    public void getDividendYieldWholeNumber() {
        CommonStock commonStock = new CommonStock("TEST", 100, 100);
        assertEquals(new BigDecimal("2.00000"), commonStock.getDividendYield(50));
    }

    @Test
    public void getDividendYieldOneDecimalPlace() {
        CommonStock commonStockOne = new CommonStock("TEST", 100, 100);
        assertEquals(new BigDecimal("0.50000"), commonStockOne.getDividendYield(200));
    }

    @Test
    public void getDividendYieldFivePlaces() {
        CommonStock commonStockOne = new CommonStock("TEST", 100, 100);
        assertEquals(new BigDecimal("0.00810"), commonStockOne.getDividendYield(12345));
    }

    @Test
    public void getDividendYieldZeroDivided() {
        CommonStock commonStockOne = new CommonStock("TEST", 0, 100);
        assertEquals(new BigDecimal("0.00000"), commonStockOne.getDividendYield(200));
    }

    @Test(expected = ArithmeticException.class)
    public void getDividendYieldDivideByZero() {
        CommonStock commonStockOne = new CommonStock("TEST", 100, 100);
        assertEquals(new BigDecimal("0.0000"), commonStockOne.getDividendYield(0));
    }

    @Test
    public void getPeRatioCommonStock() {
        int marketPrice = 123;
        CommonStock commonStockOne = new CommonStock("TEST", 100, 100);
        assertEquals(new BigDecimal("0.81301"), commonStockOne.getDividendYield(marketPrice));
        //1.229997171006507
        assertEquals(new BigDecimal("1.23000"), commonStockOne.getPeRatio(marketPrice));
    }
}