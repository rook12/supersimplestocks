package com.rook12.stockexchange.repositories;

import com.rook12.stockexchange.model.Trade;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface TradingActivityRepository {
    void save(Trade trade);
    List<Trade> findAll();
    Trade findByBrokerOrderId(int orderId);
    List<Trade> findTradesAfterTime(LocalDateTime localDateTime);
}
