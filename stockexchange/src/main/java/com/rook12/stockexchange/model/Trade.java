package com.rook12.stockexchange.model;

import java.util.Date;
import java.util.UUID;

public class Trade {
    private Date timestamp;
    private UUID exchangeTradeId;
    private String stockSymbol;

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

    public Trade(Date timestamp, UUID exchangeTradeId, String stockSymbol, int brokerOrderId, TradingAction tradingAction, int quantity, int tradePrice) {
        this.timestamp = timestamp;
        this.exchangeTradeId = exchangeTradeId;
        this.stockSymbol = stockSymbol;
        this.brokerOrderId = brokerOrderId;
        this.tradingAction = tradingAction;
        this.quantity = quantity;
        this.tradePrice = tradePrice;
    }

    private int brokerOrderId;
    private TradingAction tradingAction;
    private int quantity;
    private int tradePrice;
}
