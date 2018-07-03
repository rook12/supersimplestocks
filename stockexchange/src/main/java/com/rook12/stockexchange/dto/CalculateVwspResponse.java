package com.rook12.stockexchange.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CalculateVwspResponse {
    private String stockSymbol;
    private LocalDateTime localDateTime;
    private BigDecimal vwsp;
    private int tradeCount;

    public CalculateVwspResponse(String stockSymbol, LocalDateTime localDateTime, BigDecimal vwsp, int tradeCount) {
        this.stockSymbol = stockSymbol;
        this.localDateTime = localDateTime;
        this.vwsp = vwsp;
        this.tradeCount = tradeCount;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public BigDecimal getVwsp() {
        return vwsp;
    }

    public void setVwsp(BigDecimal vwsp) {
        this.vwsp = vwsp;
    }

    public int getTradeCount() {
        return tradeCount;
    }

    public void setTradeCount(int tradeCount) {
        this.tradeCount = tradeCount;
    }
}
