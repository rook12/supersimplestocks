package com.rook12.stockexchange.repositories;

import com.rook12.stockexchange.dto.TradingActivityResponse;
import com.rook12.stockexchange.model.TradingAction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/*
Interface test
Need this runwith otherwise thenReturn will always return null
 */
@RunWith(MockitoJUnitRunner.class)
public class TradingActivityRepositoryTest {

    @Mock
    private TradingActivityRepository tradingActivityRepository;

    private Date now = new Date();

    TradingActivityResponse mockResponse = new TradingActivityResponse(now,
            UUID.randomUUID(),
            "TEA",
            12345,
            TradingAction.BUY,
            100,
            120
            );

    @Test
    public void executeOrder() {
        when(tradingActivityRepository.executeOrder(12345, "TEA", TradingAction.BUY, 100, 120))
                .thenReturn(mockResponse);

        TradingActivityResponse activityResponse = tradingActivityRepository.executeOrder(12345,
                "TEA",
                TradingAction.BUY,
                100,
                120);

        assertEquals("TEA", activityResponse.getStockSymbol());
    }

    @Test
    public void executeOrder1() {
        when(tradingActivityRepository.executeOrder(12345, "TEA", TradingAction.BUY, 100, 120, now))
                .thenReturn(mockResponse);

        TradingActivityResponse activityResponse = tradingActivityRepository.executeOrder(12345,
                "TEA",
                TradingAction.BUY,
                100,
                120,
                now);

        assertEquals(now.getTime(), now.getTime());
    }
}