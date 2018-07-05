package com.rook12.stockbroker.dto;

//import com.rook12.stockexchange.model.TradingAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rook12.stockbroker.model.OrderAction;

import java.util.Date;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TradeResponse {
    private Date timestamp;
    private UUID exchangeTradeId = UUID.randomUUID();
    private String stockSymbol;
    private int brokerOrderId;

    @Override
    public String toString() {
        return "Trade Executed with properties - {" +
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

    public UUID getExchangeTradeId() {
        return exchangeTradeId;
    }

    public void setExchangeTradeId(UUID exchangeTradeId) {
        this.exchangeTradeId = exchangeTradeId;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public int getBrokerOrderId() {
        return brokerOrderId;
    }

    public void setBrokerOrderId(int brokerOrderId) {
        this.brokerOrderId = brokerOrderId;
    }

    public OrderAction getTradingAction() {
        return tradingAction;
    }

    public void setTradingAction(OrderAction tradingAction) {
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

    private OrderAction tradingAction;
    private int quantity;
    private int tradePrice;



}
