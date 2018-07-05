package com.rook12.stockbroker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PeRatioResponse {
    private BigDecimal peRatio;
    private String stockType;
    private String stockSymbol;
    private int marketPrice;

    public BigDecimal getPeRatio() {
        return peRatio;
    }

    public void setPeRatio(BigDecimal peRatio) {
        this.peRatio = peRatio;
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
}
