package com.rook12.stockexchange.services;

import com.rook12.stockexchange.dto.CalculateAllShareIndexResponse;
import com.rook12.stockexchange.dto.CalculateVwspResponse;
import com.rook12.stockexchange.model.Trade;
import com.rook12.stockexchange.model.TradingAction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface TradingService {
    Trade executeOrder(int orderId, String stockSymbol, TradingAction action, int quantity, int tradePrice);
    Trade executeOrder(int orderId, String stockSymbol, TradingAction action, int quantity, int tradePrice, LocalDateTime timeStamp);
    CalculateVwspResponse calculateVwsp(String stockSymbol);
    CalculateAllShareIndexResponse calculateAllShareIndex();
    void simulateTradingActivity();
}
