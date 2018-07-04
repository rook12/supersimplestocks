package com.rook12.stockbroker.dto;

import com.rook12.stockbroker.model.OrderAction;

public class TradeRequest {
    int orderId;
    String stockSymbol;
    OrderAction action;
    int quantity;
    int tradePrice;

    public TradeRequest(int orderId, String stockSymbol, OrderAction action, int quantity, int tradePrice) {
        this.orderId = orderId;
        this.stockSymbol = stockSymbol;
        this.action = action;
        this.quantity = quantity;
        this.tradePrice = tradePrice;
    }

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

    public OrderAction getAction() {
        return action;
    }

    public void setAction(OrderAction action) {
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
}
