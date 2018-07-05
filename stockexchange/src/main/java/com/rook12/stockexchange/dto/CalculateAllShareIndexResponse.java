package com.rook12.stockexchange.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CalculateAllShareIndexResponse {
    private BigDecimal allShareIndex;
    private int tradeCount;
    private LocalDateTime indexComputationTimestamp;

    public CalculateAllShareIndexResponse(BigDecimal allShareIndex, int tradeCount, LocalDateTime indexComputationTimestamp) {

        this.allShareIndex = allShareIndex;
        this.tradeCount = tradeCount;
        this.indexComputationTimestamp = indexComputationTimestamp;
    }

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

    public LocalDateTime getIndexComputationTimestamp() {
        return indexComputationTimestamp;
    }

    public void setIndexComputationTimestamp(LocalDateTime indexComputationTimestamp) {
        this.indexComputationTimestamp = indexComputationTimestamp;
    }
}
