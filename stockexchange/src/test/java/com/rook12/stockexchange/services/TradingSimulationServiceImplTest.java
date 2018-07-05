package com.rook12.stockexchange.services;

import com.rook12.stockexchange.config.StockExchangeConfigurationProperties;
import com.rook12.stockexchange.dto.SimulateTradeReponse;
import com.rook12.stockexchange.repositories.StockRepository;
import com.rook12.stockexchange.repositories.StockRepositoryInMemoryImpl;
import com.rook12.stockexchange.repositories.TradingActivityRepository;
import com.rook12.stockexchange.repositories.TradingActivityRepositoryInMemoryImpl;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class TradingSimulationServiceImplTest {
    //In memory objects are cheap, just use the real ones for this last test
    private StockRepository stockRepository = new StockRepositoryInMemoryImpl();
    private TradingActivityRepository tradingActivityRepository = new TradingActivityRepositoryInMemoryImpl();
    private StockExchangeConfigurationProperties configurationProperties ;
    private TradingService tradingService = new TradingServiceImpl(tradingActivityRepository, stockRepository, configurationProperties);

    private TradingSimulationServiceImpl simulationService = new TradingSimulationServiceImpl(stockRepository, tradingService);

    @Test
    public void simulateTradesRandom() {
        SimulateTradeReponse response = simulationService.simulateTrades("random");
        assertEquals(100, response.getTradeCount());
    }

    @Test
    public void simulateTradesConsistent() {
        SimulateTradeReponse response = simulationService.simulateTrades("consistent");
        assertEquals(10, response.getTradeCount());
    }
}