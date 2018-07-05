package com.rook12.stockexchange.repositories;

import com.rook12.stockexchange.model.Stock;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StockRepositoryInMemoryImplTest {

    private StockRepositoryInMemoryImpl repository = new StockRepositoryInMemoryImpl();

    @Test
    public void findBySymbol() {
        Stock result = repository.findBySymbol("TEA");
        assertEquals("TEA", result.getSymbol());
        assertEquals(0, result.getLastDividend());
        assertEquals(100, result.getParValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void findInvalidSymbol() {
        repository.findBySymbol("CIDER");
    }
}