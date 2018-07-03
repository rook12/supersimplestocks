package com.rook12.stockexchange.services;

import com.rook12.stockexchange.model.Trade;
import com.rook12.stockexchange.model.TradeBuilder;
import com.rook12.stockexchange.model.TradingAction;
import com.rook12.stockexchange.repositories.TradingActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TradingServiceImpl implements TradingService {
    private TradingActivityRepository tradingActivityRepository;

    private static final int VWSP_MINUTES = 15;

    @Autowired
    public TradingServiceImpl(TradingActivityRepository tradingActivityRepository) {
        this.tradingActivityRepository = tradingActivityRepository;
    }

    @Override
    public Trade executeOrder(int orderId, String stockSymbol, TradingAction action, int quantity, int tradePrice) {
        return this.executeOrder(orderId, stockSymbol, action, quantity, tradePrice, LocalDateTime.now());
    }

    /**
     * Execute a trade.
     *
     * @param orderId Broker order ID - this should be unique
     * @param stockSymbol Symbol
     * @param action BUY or SELL
     * @param quantity Quantity
     * @param tradePrice Trade price
     * @param timeStamp Expose extra timeStamp param for allowing trades to be simulated in the past.
     * @return A Trade
     */
    @Override
    public Trade executeOrder(int orderId, String stockSymbol, TradingAction action, int quantity, int tradePrice, LocalDateTime timeStamp) {
        UUID tradeReference = UUID.randomUUID();
        Trade trade = new TradeBuilder().setExchangeTradeId(tradeReference)
                .setTradePrice(tradePrice)
                .setBrokerOrderId(orderId)
                .setQuantity(quantity)
                .setStockSymbol(stockSymbol)
                .setTimestamp(timeStamp)
                .setTradingAction(action)
                .createTrade();
        tradingActivityRepository.save(trade);
        return tradingActivityRepository.findByBrokerOrderId(orderId);
    }

    @Override
    public BigDecimal calculateVwsp() {
        //Σ = Sigma = which means sum
        LocalDateTime oldestTradeTime = LocalDateTime.now().minusMinutes(VWSP_MINUTES);
        List<Trade> trades = tradingActivityRepository.findTradesAfterTime(oldestTradeTime);

        int totalSumOfTradesTimesQuantity = trades.stream().mapToInt(trade -> (trade.getTradePrice() * trade.getQuantity())).sum();
        int totalQuantity = trades.stream().mapToInt(trade -> (trade.getQuantity())).sum();

        return BigDecimal.valueOf(totalSumOfTradesTimesQuantity)
                .divide(BigDecimal.valueOf(totalQuantity), 5, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calculateAllShareIndex() {
        return null;
    }
}
