package com.rook12.stockexchange.repositories;

import com.rook12.stockexchange.model.TradeBuilder;
import com.rook12.stockexchange.model.TradingAction;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class TradingActivityRepositoryInMemoryImplTest {

    private LocalDateTime now = LocalDateTime.now();

    private TradeBuilder tradeBuilder = new TradeBuilder()
            .setTradingAction(TradingAction.BUY)
            .setTimestamp(now)
            .setStockSymbol("TEST")
            .setQuantity(100)
            .setTradePrice(110)
            .setExchangeTradeId(UUID.randomUUID());


    @Test
    public void save() {
        TradingActivityRepositoryInMemoryImpl repository = new TradingActivityRepositoryInMemoryImpl();
        repository.save(tradeBuilder.setBrokerOrderId(1).createTrade());
        repository.save(tradeBuilder.setBrokerOrderId(2).createTrade());
        assertEquals(2, repository.findAll().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveTwoTradesSameId() {
        TradingActivityRepositoryInMemoryImpl repository = new TradingActivityRepositoryInMemoryImpl();
        repository.save(tradeBuilder.setBrokerOrderId(1).createTrade());
        repository.save(tradeBuilder.setBrokerOrderId(1).createTrade());
    }

    /**
     * findAll test is pretty much the same as save. Save a couple of trades, then get the count back
     */
    @Test
    public void findAll() {
        TradingActivityRepositoryInMemoryImpl repository = new TradingActivityRepositoryInMemoryImpl();
        repository.save(tradeBuilder.setBrokerOrderId(1).createTrade());
        repository.save(tradeBuilder.setBrokerOrderId(2).createTrade());
        assertEquals(2, repository.findAll().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByBrokerOrderIdFail() {
        TradingActivityRepositoryInMemoryImpl repository = new TradingActivityRepositoryInMemoryImpl();
        repository.save(tradeBuilder.setBrokerOrderId(1).createTrade());
        repository.findByBrokerOrderId(12345);
    }

    @Test
    public void findByBrokerOrderId() {
        TradingActivityRepositoryInMemoryImpl repository = new TradingActivityRepositoryInMemoryImpl();
        repository.save(tradeBuilder.setBrokerOrderId(1).createTrade());
        assertEquals(1, repository.findByBrokerOrderId(1).getBrokerOrderId());
    }

    @Test
    public void findTradesAfterTime() {
        TradingActivityRepositoryInMemoryImpl repository = new TradingActivityRepositoryInMemoryImpl();
        repository.save(tradeBuilder.setBrokerOrderId(1).createTrade());
        repository.save(tradeBuilder.setBrokerOrderId(2).createTrade());
        repository.save(tradeBuilder.setTimestamp(now.minusMinutes(30)).setBrokerOrderId(3).createTrade());
        repository.save(tradeBuilder.setTimestamp(now.minusMinutes(30)).setBrokerOrderId(4).createTrade());
        assertEquals(2, repository.findTradesAfterTime(now.minusMinutes(15)).size());
    }
}