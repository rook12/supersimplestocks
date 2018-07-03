package com.rook12.stockexchange.repositories;

import com.rook12.stockexchange.model.Trade;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TradingActivityRepositoryInMemoryImpl implements TradingActivityRepository {
    ArrayList<Trade> tradingActivity = new ArrayList<>();

    @Override
    public void save(Trade trade) {
        tradingActivity.add(trade);
    }

    @Override
    public List<Trade> findAll() {
        return tradingActivity;
    }

    @Override
    public List<Trade> findTradesAfterTime(LocalDateTime localDateTime) {
        return tradingActivity.stream()
                .filter(x -> x.getTimestamp().isAfter(localDateTime))
                .collect(Collectors.toList());

    }

}
