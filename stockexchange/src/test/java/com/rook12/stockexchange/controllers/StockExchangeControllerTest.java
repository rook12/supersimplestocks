package com.rook12.stockexchange.controllers;

import com.rook12.stockexchange.config.StockExchangeConfigurationProperties;
import com.rook12.stockexchange.dto.*;
import com.rook12.stockexchange.model.Trade;
import com.rook12.stockexchange.model.TradeBuilder;
import com.rook12.stockexchange.services.DividendService;
import com.rook12.stockexchange.services.TradingService;
import com.rook12.stockexchange.services.TradingSimulationService;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StockExchangeControllerTest {

    private StockExchangeConfigurationProperties configurationProperties;
    @Mock
    private DividendService dividendService = Mockito.mock(DividendService.class);
    @Mock
    private TradingService tradingService = Mockito.mock(TradingService.class);

    @Mock
    private TradingSimulationService tradingSimulationService = Mockito.mock(TradingSimulationService.class);

    private StockExchangeController stockExchangeController = new StockExchangeController(configurationProperties,
            dividendService,
            tradingService,
            tradingSimulationService);

    @Test
    public void calculateDividendYield() {
        DividendYieldResponse mockResponse = new DividendYieldResponse();
        mockResponse.setDividendYield(new BigDecimal("123"));
        mockResponse.setMarketPrice(100);
        mockResponse.setStockSymbol("POP");
        mockResponse.setStockType("COMMON");

        when(dividendService.getDividendYield("POP", 100))
                .thenReturn(mockResponse);
        assertEquals("POP", stockExchangeController.calculateDividendYield("POP", 100).getStockSymbol());
        verify(dividendService).getDividendYield("POP", 100);
    }

    @Test
    public void calculatePeRatio() {
        PeRatioResponse mockResponse = new PeRatioResponse();
        mockResponse.setStockType("COMMON");
        mockResponse.setStockSymbol("POP");
        mockResponse.setPeRatio(new BigDecimal("12345"));
        mockResponse.setMarketPrice(100);

        when(dividendService.getPeRatio("POP", 100))
                .thenReturn(mockResponse);
        assertEquals(new BigDecimal("12345"), stockExchangeController.calculatePeRatio("POP", 100).getPeRatio());
        verify(dividendService).getPeRatio("POP", 100);
    }

    @Test
    public void calculateVwsp() {
        CalculateVwspResponse mockResponse = new CalculateVwspResponse("POP",
                LocalDateTime.now(),
                new BigDecimal("12345"),
                40);

        when(tradingService.calculateVwsp("POP"))
                .thenReturn(mockResponse);

        assertEquals(40, stockExchangeController.calculateVwsp("POP").getTradeCount());
        verify(tradingService).calculateVwsp("POP");
    }

    @Test
    public void calculateAllShareIndex() {
        CalculateAllShareIndexResponse mockResponse = new CalculateAllShareIndexResponse(new BigDecimal("12345"),
                40,
                LocalDateTime.now());

        when(tradingService.calculateAllShareIndex())
                .thenReturn(mockResponse);

        assertEquals(40, stockExchangeController.calculateAllShareIndex().getTradeCount());
        verify(tradingService).calculateAllShareIndex();
    }


    @Test
    public void executeOrder() {
        OrderRequest request = new OrderRequest();
        request.setOrderId(12345);

        Trade mockTradeResponse = new TradeBuilder().setBrokerOrderId(12345).createTrade();

        when(tradingService.executeOrder(12345, null, null, 0, 0))
                .thenReturn(mockTradeResponse);
        assertEquals(12345, stockExchangeController.executeOrder(request).getBrokerOrderId());
        verify(tradingService).executeOrder(12345, null, null, 0, 0);
    }
}