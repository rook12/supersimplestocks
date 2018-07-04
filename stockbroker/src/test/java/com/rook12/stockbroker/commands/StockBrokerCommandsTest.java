package com.rook12.stockbroker.commands;

import com.rook12.stockbroker.services.StockExchangeService;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class StockBrokerCommandsTest {

    @Mock
    StockExchangeService stockExchangeService = Mockito.mock(StockExchangeService.class);

    private StockBrokerCommands stockBrokerCommands = new StockBrokerCommands(stockExchangeService);

    @Test
    public void calculateDividendYield() {
        fail();
    }

    @Test
    public void calculatePERatio() {
        fail();
    }

    @Test
    public void executeTrade() {
        fail();
    }

    @Test
    public void calculateVWSP() {
        fail();
    }

    @Test
    public void simulateTrades() {
        fail();
    }

    @Test
    public void calculateAllShareIndex() {
        fail();
    }
}