package com.rook12.stockexchange;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DividendYieldResponse {
    private BigDecimal dividendYield;
    private String stockType;
    private String stockSymbol;
    private int marketPrice;

    public BigDecimal getDividendYield() {
        return dividendYield;
    }

    public void setDividendYield(BigDecimal dividendYield) {
        this.dividendYield = dividendYield;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public int getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(int marketPrice) {
        this.marketPrice = marketPrice;
    }

    @Override
    public String toString() {
        return "DividendYieldResponse{" +
                "dividendYield=" + dividendYield +
                ", stockType='" + stockType + '\'' +
                ", stockSymbol='" + stockSymbol + '\'' +
                ", marketPrice=" + marketPrice +
                '}';
    }
}
