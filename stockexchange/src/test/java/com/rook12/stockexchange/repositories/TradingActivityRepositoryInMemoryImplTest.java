package com.rook12.stockexchange.repositories;

import com.rook12.stockexchange.model.Trade;
import com.rook12.stockexchange.model.TradeBuilder;
import com.rook12.stockexchange.model.TradingAction;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.Assert.*;

public class TradingActivityRepositoryInMemoryImplTest {

    private LocalDateTime now = LocalDateTime.now();

    private Trade exampleTradeNow = new TradeBuilder()
            .setTradingAction(TradingAction.BUY)
            .setTimestamp(now)
            .setStockSymbol("TEST")
            .setQuantity(100)
            .setBrokerOrderId(12334)
            .setTradePrice(110)
            .setExchangeTradeId(UUID.randomUUID())
            .createTrade();

    private Trade exampleTradeInPast = new TradeBuilder()
            .setTradingAction(TradingAction.BUY)
            .setTimestamp(now.minusMinutes(30))
            .setStockSymbol("TEST")
            .setQuantity(100)
            .setBrokerOrderId(12334)
            .setTradePrice(110)
            .setExchangeTradeId(UUID.randomUUID())
            .createTrade();

    @Test
    public void save() {
        TradingActivityRepositoryInMemoryImpl repository = new TradingActivityRepositoryInMemoryImpl();
        repository.save(exampleTradeNow);
        repository.save(exampleTradeNow);
        assertEquals(2, repository.findAll().size());
    }

    /**
     * findAll test is pretty much the same as save. Save a couple of trades, then get the count back
     */
    @Test
    public void findAll() {
        TradingActivityRepositoryInMemoryImpl repository = new TradingActivityRepositoryInMemoryImpl();
        repository.save(exampleTradeNow);
        repository.save(exampleTradeNow);
        assertEquals(2, repository.findAll().size());
    }

    @Test
    public void findTradesAfterTime() {
        TradingActivityRepositoryInMemoryImpl repository = new TradingActivityRepositoryInMemoryImpl();
        repository.save(exampleTradeNow);
        repository.save(exampleTradeNow);
        repository.save(exampleTradeNow);
        repository.save(exampleTradeNow);
        repository.save(exampleTradeInPast);
        repository.save(exampleTradeInPast);
        repository.save(exampleTradeInPast);
        assertEquals(4, repository.findTradesAfterTime(now.minusMinutes(15)).size());
    }
}