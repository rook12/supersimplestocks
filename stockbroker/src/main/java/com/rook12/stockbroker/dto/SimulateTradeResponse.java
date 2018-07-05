package com.rook12.stockbroker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SimulateTradeResponse {
    private LocalDateTime simulationComplete;
    private List<TradeResponse> trades;
    private int tradeCount;

    public int getTradeCount() {
        return tradeCount;
    }

    public void setTradeCount(int tradeCount) {
        this.tradeCount = tradeCount;
    }

    public LocalDateTime getSimulationComplete() {
        return simulationComplete;
    }

    public void setSimulationComplete(LocalDateTime simulationComplete) {
        this.simulationComplete = simulationComplete;
    }

    public List<TradeResponse> getTrades() {
        return trades;
    }

    public void setTrades(List<TradeResponse> trades) {
        this.trades = trades;
    }
}
