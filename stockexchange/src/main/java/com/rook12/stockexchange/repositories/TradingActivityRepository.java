package com.rook12.stockexchange.repositories;

import com.rook12.stockexchange.model.Trade;

import java.time.Duration;

public interface TradingActivityRepository {
    void save(Trade trade);
    Iterable<Trade> findAll();
    Iterable<Trade> findByTimeDelta(Duration duration);
}
