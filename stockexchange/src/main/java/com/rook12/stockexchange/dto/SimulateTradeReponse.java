package com.rook12.stockexchange.dto;

import com.rook12.stockexchange.model.Trade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SimulateTradeReponse {
    private LocalDateTime simulationComplete;
    private List<Trade> trades = new ArrayList<>();

    public LocalDateTime getSimulationComplete() {
        return simulationComplete;
    }

    public void setSimulationComplete(LocalDateTime simulationComplete) {
        this.simulationComplete = simulationComplete;
    }

    public List<Trade> getTrades() {
        return trades;
    }

    public void addTrade(Trade trade) {
        this.trades.add(trade);
    }

    public void setTrades(List<Trade> trades) {
        this.trades = trades;
    }

    public int getTradeCount() {
        return trades.size();
    }

}
