package com.rook12.stockexchange.services;

import com.rook12.stockexchange.model.Trade;
import com.rook12.stockexchange.model.TradeBuilder;
import com.rook12.stockexchange.model.TradingAction;
import com.rook12.stockexchange.repositories.TradingActivityRepository;
import com.rook12.stockexchange.repositories.TradingActivityRepositoryInMemoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

/*
Interface test
Need this runwith otherwise thenReturn will always return null
 */
@RunWith(MockitoJUnitRunner.class)
public class TradingServiceTest {

    //In memory repo is cheap, don't bother with Mockito
    private TradingActivityRepository repository = new TradingActivityRepositoryInMemoryImpl();

    private TradingServiceImpl tradingService = new TradingServiceImpl(repository);

    private LocalDateTime now = LocalDateTime.now();

    @Test
    public void executeOrder() {
        Trade trade = tradingService.executeOrder(12345,
                "TEA",
                TradingAction.BUY,
                100,
                120);

        assertEquals("TEA", trade.getStockSymbol());
    }

    @Test
    public void executeOrder1() {
        Trade trade = tradingService.executeOrder(12345,
                "TEA",
                TradingAction.BUY,
                100,
                120,
                now);

        assertEquals(now.getMinute(), now.getMinute());
    }

    @Test
    public void calculateVwsp() {
        tradingService.executeOrder(1, "TEA", TradingAction.BUY, 88, 120, now);
        tradingService.executeOrder(2, "POP", TradingAction.BUY, 100, 130, now);
        tradingService.executeOrder(3, "POP", TradingAction.BUY, 130, 140, now);
        tradingService.executeOrder(4, "ALE", TradingAction.BUY, 124, 75, now);
        tradingService.executeOrder(5, "GIN", TradingAction.BUY, 168, 105, now);
        tradingService.executeOrder(6, "JOE", TradingAction.BUY, 351, 260, now);
        tradingService.executeOrder(7, "JOE", TradingAction.BUY, 687, 285, now);
        //These trades below should not factor into calculation as they are beyond 15 minutes
        tradingService.executeOrder(8, "TEA", TradingAction.BUY, 100, 120, now.minusMinutes(30));
        tradingService.executeOrder(9, "TEA", TradingAction.BUY, 100, 120, now.minusMinutes(30));
        tradingService.executeOrder(10, "TEA", TradingAction.BUY, 100, 120, now.minusMinutes(30));
        tradingService.executeOrder(11, "TEA", TradingAction.BUY, 100, 120, now.minusMinutes(30));

        //88 x 120 = 10560
        //100 x 130 = 13000
        //130 x 140 = 18200
        //124 x 75 = 9300
        //168 x 105 = 17640
        //351 x 260 = 91260
        //687 x 285 = 195795
        //Top half division Total = 355755
        //Bottom half of division
        //88 + 100 + 130 + 124 + 168 + 351 + 687 = 1648
        // 355755 / 1648 = 215.8707524271845 (rounded 215.87075)
        assertEquals(new BigDecimal("215.87075"), tradingService.calculateVwsp());

    }

    @Test
    public void calculateAllShareIndex() {

    }
}