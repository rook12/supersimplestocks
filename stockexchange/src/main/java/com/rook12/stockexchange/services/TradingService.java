package com.rook12.stockexchange.services;

import com.rook12.stockexchange.dto.TradingActivityResponse;
import com.rook12.stockexchange.model.TradingAction;

import java.util.Date;

public interface TradingService {
    TradingActivityResponse executeOrder(int orderId, String stockSymbol, TradingAction action, int quantity, int tradePrice);
    TradingActivityResponse executeOrder(int orderId, String stockSymbol, TradingAction action, int quantity, int tradePrice, Date timeStamp);
}
