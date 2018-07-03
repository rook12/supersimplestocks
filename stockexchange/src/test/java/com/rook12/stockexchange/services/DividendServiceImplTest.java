package com.rook12.stockexchange.services;

import com.rook12.stockexchange.dto.DividendYieldResponse;
import com.rook12.stockexchange.dto.PeRatioResponse;
import com.rook12.stockexchange.repositories.StockRepositoryInMemoryImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class DividendServiceImplTest {

    //Using real stock repository for test (instead of mock) as its in memory and cheap
    private StockRepositoryInMemoryImpl stockRepository = new StockRepositoryInMemoryImpl();
    private DividendService dividendService = new DividendServiceImpl(stockRepository);

    @Test
    public void getDividendYieldForCommonStockZero() {
        DividendYieldResponse dividendYieldResponse = dividendService.getDividendYield("TEA", 250);
        assertEquals(new BigDecimal("0.00000"), dividendYieldResponse.getDividendYield());
    }

    @Test
    public void getDividendYieldForCommonStockNonZero() {
        DividendYieldResponse dividendYieldResponse = dividendService.getDividendYield("ALE", 70);
        assertEquals(new BigDecimal("0.32857"), dividendYieldResponse.getDividendYield());
    }

    @Test
    public void getDividendYieldForPreferredStock() {
        DividendYieldResponse dividendYieldResponse = dividendService.getDividendYield("GIN", 110);
        assertEquals(new BigDecimal("0.01818"), dividendYieldResponse.getDividendYield());
    }

    @Test(expected = ArithmeticException.class)
    public void getPeRatioCommonStockZeroDividend() {
        PeRatioResponse peRatioResponse = dividendService.getPeRatio("TEA", 110);
        assertEquals(new BigDecimal("0.32857"), peRatioResponse.getPeRatio());
    }

    //0.20909
    @Test
    public void getPeRatioCommonStock() {
        PeRatioResponse peRatioResponse = dividendService.getPeRatio("ALE", 70);
        assertEquals(new BigDecimal("3.04348"), peRatioResponse.getPeRatio());
    }

    @Test
    public void getPeRatioPreferredStock() {
        PeRatioResponse peRatioResponse = dividendService.getPeRatio("GIN", 110);
        assertEquals(new BigDecimal("13.75000"), peRatioResponse.getPeRatio());
    }
}