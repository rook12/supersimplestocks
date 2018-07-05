package com.rook12.stockexchange.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Trade {
    private LocalDateTime timestamp;
    private UUID exchangeTradeId;
    private String stockSymbol;
    private int brokerOrderId;
    private TradingAction tradingAction;
    private int quantity;
    private int tradePrice;

    public Trade(LocalDateTime timestamp, UUID exchangeTradeId, String stockSymbol, int brokerOrderId, TradingAction tradingAction, int quantity, int tradePrice) {
        this.timestamp = timestamp;
        this.exchangeTradeId = exchangeTradeId;
        this.stockSymbol = stockSymbol;
        this.brokerOrderId = brokerOrderId;
        this.tradingAction = tradingAction;
        this.quantity = quantity;
        this.tradePrice = tradePrice;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public UUID getExchangeTradeId() {
        return exchangeTradeId;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public int getBrokerOrderId() {
        return brokerOrderId;
    }

    public TradingAction getTradingAction() {
        return tradingAction;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTradePrice() {
        return tradePrice;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "timestamp=" + timestamp +
                ", exchangeTradeId=" + exchangeTradeId +
                ", stockSymbol='" + stockSymbol + '\'' +
                ", brokerOrderId=" + brokerOrderId +
                ", tradingAction=" + tradingAction +
                ", quantity=" + quantity +
                ", tradePrice=" + tradePrice +
                '}';
    }


}
