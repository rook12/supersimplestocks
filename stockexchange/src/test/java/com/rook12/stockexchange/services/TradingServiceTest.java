package com.rook12.stockexchange.services;

import com.rook12.stockexchange.dto.TradingActivityResponse;
import com.rook12.stockexchange.model.Trade;
import com.rook12.stockexchange.model.TradeBuilder;
import com.rook12.stockexchange.model.TradingAction;
import com.rook12.stockexchange.services.TradingService;
import org.apache.tomcat.jni.Local;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/*
Interface test
Need this runwith otherwise thenReturn will always return null
 */
@RunWith(MockitoJUnitRunner.class)
public class TradingServiceTest {

    @Mock
    private TradingService tradingActivityRepository;

    private LocalDateTime now = LocalDateTime.now();

    Trade mockResponse = new TradeBuilder()
            .setBrokerOrderId(12345)
            .setExchangeTradeId(UUID.randomUUID())
            .setQuantity(100)
            .setStockSymbol("TEA")
            .setTimestamp(now)
            .setTradePrice(120)
            .setTradingAction(TradingAction.BUY)
            .createTrade();

    @Test
    public void executeOrder() {
        when(tradingActivityRepository.executeOrder(12345, "TEA", TradingAction.BUY, 100, 120))
                .thenReturn(mockResponse);

        Trade trade = tradingActivityRepository.executeOrder(12345,
                "TEA",
                TradingAction.BUY,
                100,
                120);

        assertEquals("TEA", trade.getStockSymbol());
    }

    @Test
    public void executeOrder1() {
        when(tradingActivityRepository.executeOrder(12345, "TEA", TradingAction.BUY, 100, 120, now))
                .thenReturn(mockResponse);

        Trade trade = tradingActivityRepository.executeOrder(12345,
                "TEA",
                TradingAction.BUY,
                100,
                120,
                now);

        assertEquals(now.getMinute(), now.getMinute());
    }
}