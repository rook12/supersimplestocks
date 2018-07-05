package com.rook12.stockexchange.services;

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
    private TradingService tradingService = new TradingServiceImpl(tradingActivityRepository, stockRepository);

    private TradingSimulationServiceImpl simulationService = new TradingSimulationServiceImpl(stockRepository, tradingService);

    @Test
    public void simulateTrades() {
        SimulateTradeReponse response = simulationService.simulateTrades();
        assertEquals(100, response.getTradeCount());
    }
}