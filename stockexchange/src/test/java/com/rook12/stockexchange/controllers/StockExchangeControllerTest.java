package com.rook12.stockexchange.controllers;

import com.rook12.stockexchange.config.StockExchangeConfigurationProperties;
import com.rook12.stockexchange.services.DividendService;
import com.rook12.stockexchange.services.TradingService;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class StockExchangeControllerTest {

    StockExchangeConfigurationProperties configurationProperties;
    @Mock
    DividendService dividendService;
    @Mock
    TradingService tradingService;

    StockExchangeController stockExchangeController = new StockExchangeController(configurationProperties,
            dividendService,
            tradingService);

    @Test
    public void calculateDividendYield() {
        fail();
    }

    @Test
    public void calculatePeRatio() {
        fail();
    }

    @Test
    public void calculateVwsp() {
        fail();
    }

    @Test
    public void calculateAllShareIndex() {
        fail();
    }


    @Test
    public void simulateTradingActivity() {
        fail();
    }

    @Test
    public void executeOrder() {
        fail();
    }
}