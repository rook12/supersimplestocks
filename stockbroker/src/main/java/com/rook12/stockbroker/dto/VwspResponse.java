package com.rook12.stockbroker.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VwspResponse {
    private String stockSymbol;
    private LocalDateTime earliestTimeForTrade;

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public LocalDateTime getEarliestTimeForTrade() {
        return earliestTimeForTrade;
    }

    public void setEarliestTimeForTrade(LocalDateTime earliestTimeForTrade) {
        this.earliestTimeForTrade = earliestTimeForTrade;
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

    private BigDecimal vwsp;
    private int tradeCount;
}
