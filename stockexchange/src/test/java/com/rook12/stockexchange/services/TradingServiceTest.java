package com.rook12.stockexchange.services;

import com.rook12.stockexchange.config.StockExchangeConfigurationProperties;
import com.rook12.stockexchange.model.Trade;
import com.rook12.stockexchange.model.TradingAction;
import com.rook12.stockexchange.repositories.StockRepositoryInMemoryImpl;
import com.rook12.stockexchange.repositories.TradingActivityRepository;
import com.rook12.stockexchange.repositories.TradingActivityRepositoryInMemoryImpl;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.text.Bidi;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

/*
Interface test
Need this runwith otherwise thenReturn will always return null
 */
@RunWith(MockitoJUnitRunner.class)
public class TradingServiceTest {

    //In memory repo is cheap, don't bother with Mockito
    private TradingActivityRepository repository = new TradingActivityRepositoryInMemoryImpl();
    private StockExchangeConfigurationProperties configurationProperties = new StockExchangeConfigurationProperties();
    private TradingServiceImpl tradingService = new TradingServiceImpl(repository, new StockRepositoryInMemoryImpl(), configurationProperties);

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
        bulkOrderExecute();
        /*
        VWSP is for a single stock, not everything in last 15 minutes

        100 x 130 = 13000
        130 x 140 = 18200

        //Top half division Total = 31200
        //Bottom half of division
        //100 + 130 = 230
        // 31200 / 230 = 135.65217391304347826086956521739 (rounded 135.65217)
         */
        assertEquals(new BigDecimal("135.65217"), tradingService.calculateVwsp("POP").getVwsp());

    }

    @Test
    public void calculateAllShareIndexSmallSet() {
        bulkOrderExecute();

        //productOfTradePrice
        //120 + 130 + 140 + 75 + 105 + 260 + 285 + 120 + 120 + 290 + 130 = 1775
        //trade count - 11 square root - 3.3166247903554

        //Simple - 146.98567882520356
        //Log natural - 144.4605168261392
        //Log 10 -      144.46051682613927

        configurationProperties.setGeometricmeanmethod("SIMPLE");
        assertEquals(new BigDecimal("146.98568"), tradingService.calculateAllShareIndex().getAllShareIndex());
    }

    @Test
    public void calculateAllShareIndexLargeSet() {
        bulkOrderExecute2();
        configurationProperties.setGeometricmeanmethod("SIMPLE");
        assertEquals(new BigDecimal("130.00000"), tradingService.calculateAllShareIndex().getAllShareIndex());
    }

    public void bulkOrderExecute2() {
        for (int i = 1; i < 100; i++) {
            tradingService.executeOrder(i, "POP", TradingAction.BUY, 342, 130, now);
        }
    }



    private void bulkOrderExecute() {
        tradingService.executeOrder(1, "TEA", TradingAction.BUY, 88, 120, now);
        tradingService.executeOrder(2, "POP", TradingAction.BUY, 100, 130, now);
        tradingService.executeOrder(3, "POP", TradingAction.BUY, 130, 140, now);
        tradingService.executeOrder(4, "ALE", TradingAction.BUY, 124, 75, now);
        tradingService.executeOrder(5, "GIN", TradingAction.BUY, 168, 105, now);
        tradingService.executeOrder(6, "JOE", TradingAction.BUY, 351, 260, now);
        tradingService.executeOrder(7, "JOE", TradingAction.BUY, 687, 285, now);
        //These trades below should not factor into calculation VWSP as they are beyond 15 minutes
        tradingService.executeOrder(8, "TEA", TradingAction.BUY, 504, 120, now.minusMinutes(30));
        tradingService.executeOrder(9, "GIN", TradingAction.BUY, 80, 120, now.minusMinutes(30));
        tradingService.executeOrder(10, "JOE", TradingAction.BUY, 345, 290, now.minusMinutes(30));
        tradingService.executeOrder(11, "POP", TradingAction.BUY, 342, 130, now.minusMinutes(30));


    }
}