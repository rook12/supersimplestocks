package com.rook12.stockexchange.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CalculateAllShareIndexResponse {
    private BigDecimal allShareIndex;
    private int tradeCount;
    private LocalDateTime timestamp;

    public BigDecimal getAllShareIndex() {
        return allShareIndex;
    }

    public void setAllShareIndex(BigDecimal allShareIndex) {
        this.allShareIndex = allShareIndex;
    }

    public int getTradeCount() {
        return tradeCount;
    }

    public void setTradeCount(int tradeCount) {
        this.tradeCount = tradeCount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public CalculateAllShareIndexResponse(BigDecimal allShareIndex, int tradeCount, LocalDateTime timestamp) {

        this.allShareIndex = allShareIndex;
        this.tradeCount = tradeCount;
        this.timestamp = timestamp;
    }
}
