package com.rook12.stockbroker.services;

import com.rook12.stockbroker.dto.*;
import com.rook12.stockbroker.model.OrderAction;
import com.rook12.stockbroker.model.SimulateTradeModes;

public interface StockExchangeService {
    DividendYieldResponse getDividendYield(String stockSymbol, int marketPrice);
    PeRatioResponse getPeRatio(String stockSymbol, int marketPrice);
    TradeResponse executeTrade(int orderId, String stockSymbol, OrderAction action, int quantity, int tradePrice);
    SimulateTradeResponse simulateTrades(SimulateTradeModes mode);
    VwspResponse getVwsp(String stockSymbol);
    AllShareIndexResponse getAllShareIndex();
}
