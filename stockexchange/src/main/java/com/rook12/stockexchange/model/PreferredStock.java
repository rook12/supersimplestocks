package com.rook12.stockexchange.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PreferredStock extends CommonStock implements Stock {
    private BigDecimal fixedDividend;

    @Override
    public String toString() {
        return "PreferredStock{" +
                "fixedDividend=" + fixedDividend +
                ", parValue=" + parValue +
                ", stockSymbol='" + stockSymbol + '\'' +
                ", lastDividend=" + lastDividend +
                '}';
    }

    public PreferredStock(String stockSymbol, int lastDividend, int parValue, BigDecimal fixedDividend) {
        super(stockSymbol, lastDividend, parValue);
        this.fixedDividend = fixedDividend;
    }

    @Override
    public BigDecimal getDividendYield(int marketPrice) {
        BigDecimal dividend = fixedDividend.multiply(BigDecimal.valueOf(parValue));
        return dividend.divide(BigDecimal.valueOf(marketPrice), 5, RoundingMode.HALF_UP);
    }

    @Override
    public String getStockType() {
        return "PREFERRED";
    }
}
