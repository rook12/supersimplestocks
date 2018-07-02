package com.rook12.stockexchange.model;

import java.math.BigDecimal;

public interface Stock {
    BigDecimal getDividendYield(int marketPrice);
    BigDecimal getPeRatio(int marketPrice);
    String getSymbol();
    int getParValue();
    int getLastDividend();
    String getStockType();

}
