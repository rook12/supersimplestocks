package com.rook12.stockexchange.services;

import com.rook12.stockexchange.dto.TradingActivityResponse;
import com.rook12.stockexchange.model.Trade;
import com.rook12.stockexchange.model.TradeBuilder;
import com.rook12.stockexchange.model.TradingAction;
import com.rook12.stockexchange.repositories.TradingActivityRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
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
     * Execute a trade. Note that this works blindly, it does not validate whether there are stocks available to buy.
     *
     * @param orderId Broker order ID
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
        return trade;
    }

    @Override
    public BigDecimal calculateVwsp() {
        LocalDateTime oldestTradeTime = LocalDateTime.now().minusMinutes(VWSP_MINUTES);
        List<Trade> trades = tradingActivityRepository.findTradesAfterTime(oldestTradeTime);
        return null;
    }

    @Override
    public BigDecimal calculateAllShareIndex() {
        return null;
    }
}
