package com.rook12.stockexchange.model;

import java.math.BigDecimal;

public class CommonStock implements Stock {
    private String stockSymbol;
    private int lastDividend;
    protected int parValue;

    public CommonStock(String stockSymbol, int lastDividend, int parValue) {
        this.stockSymbol = stockSymbol;
        this.lastDividend = lastDividend;
        this.parValue = parValue;
    }

    @Override
    public BigDecimal getDividendYield(int marketPrice) {
        return BigDecimal.valueOf(lastDividend).divide(BigDecimal.valueOf(marketPrice), 5, BigDecimal.ROUND_HALF_UP);
    }
}
