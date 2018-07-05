package com.rook12.stockexchange.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class TradeBuilder {
    private LocalDateTime timestamp;
    private UUID exchangeTradeId;
    private String stockSymbol;
    private int brokerOrderId;
    private TradingAction tradingAction;
    private int quantity;
    private int tradePrice;

    public TradeBuilder setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public TradeBuilder setExchangeTradeId(UUID exchangeTradeId) {
        this.exchangeTradeId = exchangeTradeId;
        return this;
    }

    public TradeBuilder setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
        return this;
    }

    public TradeBuilder setBrokerOrderId(int brokerOrderId) {
        this.brokerOrderId = brokerOrderId;
        return this;
    }

    public TradeBuilder setTradingAction(TradingAction tradingAction) {
        this.tradingAction = tradingAction;
        return this;
    }

    public TradeBuilder setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public TradeBuilder setTradePrice(int tradePrice) {
        this.tradePrice = tradePrice;
        return this;
    }

    public Trade createTrade() {
        return new Trade(timestamp, exchangeTradeId, stockSymbol, brokerOrderId, tradingAction, quantity, tradePrice);
    }
}