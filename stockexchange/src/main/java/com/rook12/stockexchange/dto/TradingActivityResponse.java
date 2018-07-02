package com.rook12.stockexchange.dto;

import com.rook12.stockexchange.model.TradingAction;

import java.util.Date;
import java.util.UUID;

public class TradingActivityResponse {
    private Date timestamp;
    private UUID exchangeTradeId = UUID.randomUUID();
    private String stockSymbol;
    private int brokerOrderId;
    private TradingAction tradingAction;
    private int quantity;
    private int tradePrice;

    public TradingActivityResponse(Date timestamp, UUID exchangeTradeId, String stockSymbol, int brokerOrderId, TradingAction tradingAction, int quantity, int tradePrice) {
        this.timestamp = timestamp;
        this.exchangeTradeId = exchangeTradeId;
        this.stockSymbol = stockSymbol;
        this.brokerOrderId = brokerOrderId;
        this.tradingAction = tradingAction;
        this.quantity = quantity;
        this.tradePrice = tradePrice;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public UUID getExchangeTradeId() {
        return exchangeTradeId;
    }

    @Override
    public String toString() {
        return "TradingActivityResponse{" +
                "timestamp=" + timestamp +
                ", exchangeTradeId=" + exchangeTradeId +
                ", stockSymbol='" + stockSymbol + '\'' +
                ", brokerOrderId=" + brokerOrderId +
                ", tradingAction=" + tradingAction +
                ", quantity=" + quantity +
                ", tradePrice=" + tradePrice +
                '}';
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getBrokerOrderId() {
        return brokerOrderId;
    }

    public void setBrokerOrderId(int brokerOrderId) {
        this.brokerOrderId = brokerOrderId;
    }

    public TradingAction getTradingAction() {
        return tradingAction;
    }

    public void setTradingAction(TradingAction tradingAction) {
        this.tradingAction = tradingAction;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(int tradePrice) {
        this.tradePrice = tradePrice;
    }

}
