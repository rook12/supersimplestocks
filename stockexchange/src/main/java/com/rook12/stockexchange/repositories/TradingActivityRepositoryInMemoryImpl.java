package com.rook12.stockexchange.repositories;

import com.rook12.stockexchange.model.Trade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TradingActivityRepositoryInMemoryImpl implements TradingActivityRepository {
    ArrayList<Trade> tradingActivity = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(TradingActivityRepositoryInMemoryImpl.class);

    /**
     * Save to repository. The broker order Id will be used as the primary key. If multiple attempts are made to insert with same key, an
     * @param trade - Trade object
     */
    @Override
    public void save(Trade trade) {
        boolean tradeAlreadyExists = tradingActivity.stream()
                .anyMatch(x -> x.getBrokerOrderId() == trade.getBrokerOrderId());
        if (tradeAlreadyExists) {
            throw new IllegalArgumentException("Trade with broker order ID already exists - "  + Integer.toString(trade.getBrokerOrderId()));
        }

        tradingActivity.add(trade);
        logger.info(String.format("added trade to in memory repository %s", trade.toString()));
    }

    @Override
    public List<Trade> findAll() {
        return tradingActivity;
    }

    @Override
    public Trade findByBrokerOrderId(int orderId) {
        return tradingActivity.stream()
                .filter(x -> x.getBrokerOrderId() == orderId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Could not find order with id " + Integer.toString(orderId)));
    }

    @Override
    public List<Trade> findTradesAfterTime(LocalDateTime localDateTime) {
        return tradingActivity.stream()
                .filter(x -> x.getTimestamp().isAfter(localDateTime))
                .collect(Collectors.toList());

    }

}
