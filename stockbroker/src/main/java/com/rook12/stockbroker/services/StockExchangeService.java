package com.rook12.stockbroker.services;

import com.rook12.stockbroker.model.OrderAction;
import com.rook12.stockbroker.dto.DividendYieldResponse;
import com.rook12.stockbroker.dto.TradeResponse;

public interface StockExchangeService {
    DividendYieldResponse getDividendYield(String stockSymbol, int marketPrice);
    TradeResponse executeTrade(int orderId, String stockSymbol, OrderAction action, int quantity, int tradePrice);
    boolean simulateTrades();
}
