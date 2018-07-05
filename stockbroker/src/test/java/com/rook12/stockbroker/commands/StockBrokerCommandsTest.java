package com.rook12.stockbroker.commands;

import com.rook12.stockbroker.dto.*;
import com.rook12.stockbroker.model.OrderAction;
import com.rook12.stockbroker.services.StockExchangeService;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
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
        PeRatioResponse mockResponse = new PeRatioResponse();
        mockResponse.setMarketPrice(100);
        mockResponse.setPeRatio(new BigDecimal("12.34"));
        mockResponse.setStockSymbol("POP");
        mockResponse.setStockType("COMMON");
        when(stockExchangeService.getPeRatio("POP", 100))
                .thenReturn(mockResponse);

        //In addition to logging, the pe ratio gets returned to the shell as a string, so test for that
        assertEquals("12.34", stockBrokerCommands.calculatePERatio("POP", 100));
    }

    @Test
    public void executeTrade() {
        TradeResponse mockResponse = new TradeResponse();
        mockResponse.setStockSymbol("POP");
        mockResponse.setBrokerOrderId(123);
        mockResponse.setExchangeTradeId(UUID.randomUUID());
        mockResponse.setQuantity(100);
        mockResponse.setTradePrice(100);
        mockResponse.setTradingAction(OrderAction.BUY);

        when(stockExchangeService.executeTrade(123, "POP", OrderAction.BUY, 100, 100))
                .thenReturn(mockResponse);

        int brokerOrderId = stockBrokerCommands.executeTrade("POP", 100, OrderAction.BUY, 100, 123);

        assertEquals(123, brokerOrderId);
    }

    @Test
    public void calculateVWSP() {
        VwspResponse mockResponse = new VwspResponse();
        mockResponse.setTradeCount(100);
        mockResponse.setVwsp(new BigDecimal("12.34"));
        mockResponse.setTradeCount(100);
        mockResponse.setEarliestTimeForTrade(LocalDateTime.now());
        when(stockExchangeService.getVwsp("POP"))
                .thenReturn(mockResponse);
        //In addition to logging, the VWSP gets returned to the shell as a string, so test for that
        assertEquals("12.34", stockBrokerCommands.calculateVWSP("POP"));
    }

    @Test
    public void calculateAllShareIndex() {
        AllShareIndexResponse mockResponse = new AllShareIndexResponse(new BigDecimal("12.34"), 100, LocalDateTime.now());

        when(stockExchangeService.getAllShareIndex())
                .thenReturn(mockResponse);
        assertEquals("12.34", stockBrokerCommands.calculateAllShareIndex());
    }
}