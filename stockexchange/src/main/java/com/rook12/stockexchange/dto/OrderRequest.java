package com.rook12.stockexchange.dto;

import com.rook12.stockexchange.model.TradingAction;

public class OrderRequest {
    private int orderId;
    private String stockSymbol;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public TradingAction getAction() {
        return action;
    }

    public void setAction(TradingAction action) {
        this.action = action;
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

    private TradingAction action;
    private int quantity;
    private int tradePrice;
}
