package com.rook12.stockbroker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AllShareIndexResponse {
    private BigDecimal allShareIndex;
    private int tradeCount;
    private LocalDateTime indexComputationTimestamp;

    /*
    Having a constructor breaks jackson mapper

    public AllShareIndexResponse(BigDecimal allShareIndex, int tradeCount, LocalDateTime indexComputationTimestamp) {

        this.allShareIndex = allShareIndex;
        this.tradeCount = tradeCount;
        this.indexComputationTimestamp = indexComputationTimestamp;
    }*/

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
