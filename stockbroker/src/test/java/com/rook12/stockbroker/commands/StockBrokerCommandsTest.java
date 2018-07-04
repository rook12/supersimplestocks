package com.rook12.stockbroker.commands;

import com.rook12.stockbroker.dto.DividendYieldResponse;
import com.rook12.stockbroker.dto.TradeResponse;
import com.rook12.stockbroker.model.OrderAction;
import com.rook12.stockbroker.services.StockExchangeService;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StockBrokerCommandsTest {

    @Mock
    StockExchangeService stockExchangeService = Mockito.mock(StockExchangeService.class);

    private StockBrokerCommands stockBrokerCommands = new StockBrokerCommands(stockExchangeService);

    @Test
    public void calculateDividendYield() {
        DividendYieldResponse yieldResponse = new DividendYieldResponse();
        yieldResponse.setDividendYield(new BigDecimal("12.34"));

        when(stockExchangeService.getDividendYield("POP",100))
                .thenReturn(yieldResponse);
        //In addition to logging, the yield gets returned to the shell as a string, so test for that
        assertEquals("12.34", stockBrokerCommands.calculateDividendYield("POP", 100));
    }

    @Test
    public void calculatePERatio() {
        fail();
    }

    @Test
    public void executeTrade() {
        TradeResponse mockResponse = new TradeResponse();
                mockResponse.setStockSymbol("POP");
        when(stockExchangeService.executeTrade(anyInt(), "POP", OrderAction.BUY, 100, 100))
                .thenReturn(mockResponse);

        /*assertThat(org.mockito.Mockito.eq("POP"),
                containsString("POP"));*/
        //assertThat(anyObject(), eq("String by matcher"));
        fail();
    }

    @Test
    public void calculateVWSP() {

        assertEquals("12.34", stockBrokerCommands.calculateVWSP("POP"));
    }

    @Test
    public void calculateAllShareIndex() {
        fail();
    }
}