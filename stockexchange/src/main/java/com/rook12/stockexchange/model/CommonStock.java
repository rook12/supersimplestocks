package com.rook12.stockexchange.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CommonStock implements Stock {
    protected int parValue;
    protected String stockSymbol;
    protected int lastDividend;

    public CommonStock(String stockSymbol, int lastDividend, int parValue) {
        this.stockSymbol = stockSymbol;
        this.lastDividend = lastDividend;
        this.parValue = parValue;
    }

    @Override
    public String toString() {
        return "CommonStock{" +
                "parValue=" + parValue +
                ", stockSymbol='" + stockSymbol + '\'' +
                ", lastDividend=" + lastDividend +
                '}';
    }

    @Override
    public BigDecimal getDividendYield(int marketPrice) {
        return BigDecimal.valueOf(lastDividend).divide(BigDecimal.valueOf(marketPrice), 5, BigDecimal.ROUND_HALF_UP);
    }

    /*
    Dividend is dividend yield times market price
    Should probably be private, public to help with testing

    Deprecated - I can get this from lastDividend value, no need to calculate
     */
    @Deprecated
    private BigDecimal getDividend(int marketPrice) {
        return getDividendYield(marketPrice).multiply(BigDecimal.valueOf(marketPrice)).setScale(5, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public BigDecimal getPeRatio(int marketPrice) {
        BigDecimal marketPriceBd =BigDecimal.valueOf(marketPrice).setScale(5, BigDecimal.ROUND_HALF_UP);
        return marketPriceBd.divide(BigDecimal.valueOf(lastDividend), 5, RoundingMode.HALF_UP);

    }

    @Override
    public String getSymbol() {
        return stockSymbol;
    }

    public int getParValue() {
        return parValue;
    }

    public int getLastDividend() {
        return lastDividend;
    }

    @Override
    public String getStockType() {
        return "COMMON";
    }
}
