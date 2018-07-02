package com.rook12.stockexchange.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PeRatioResponse {
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

    private BigDecimal peRatio;
    private String stockType;
    private String stockSymbol;
    private int marketPrice;

    @Override
    public String toString() {
        return "PeRatioResponse{" +
                "peRatio=" + peRatio +
                ", stockType='" + stockType + '\'' +
                ", stockSymbol='" + stockSymbol + '\'' +
                ", marketPrice=" + marketPrice +
                '}';
    }
}
