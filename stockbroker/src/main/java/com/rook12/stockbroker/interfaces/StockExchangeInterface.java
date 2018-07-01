package com.rook12.stockbroker.interfaces;

import com.rook12.stockbroker.dto.DividendYieldResponse;

public interface StockExchangeInterface {
    DividendYieldResponse getDividendYield(String stockSymbol, int marketPrice);
}
