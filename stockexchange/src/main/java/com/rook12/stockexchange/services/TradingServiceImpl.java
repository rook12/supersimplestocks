package com.rook12.stockexchange.services;

import com.rook12.stockexchange.dto.TradingActivityResponse;
import com.rook12.stockexchange.model.Trade;
import com.rook12.stockexchange.model.TradeBuilder;
import com.rook12.stockexchange.model.TradingAction;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class TradingServiceImpl implements TradingService {
    @Override
    public TradingActivityResponse executeOrder(int orderId, String stockSymbol, TradingAction action, int quantity, int tradePrice) {
        return this.executeOrder(orderId, stockSymbol, action, quantity, tradePrice, new Date());
    }

    /**
     * Execute a trade. Note that this works blindly, it does not validate whether there are stocks available to buy.
     *
     * @param orderId
     * @param stockSymbol
     * @param action
     * @param quantity
     * @param tradePrice
     * @param timeStamp Expose extra timeStamp param for allowing trades to be simulated in the past.
     * @return
     */
    @Override
    public TradingActivityResponse executeOrder(int orderId, String stockSymbol, TradingAction action, int quantity, int tradePrice, Date timeStamp) {
        UUID tradeReference = UUID.randomUUID();
        Trade trade = new TradeBuilder().setExchangeTradeId(tradeReference)
                .setTradePrice(tradePrice)
                .setBrokerOrderId(orderId)
                .setQuantity(quantity)
                .setStockSymbol(stockSymbol)
                .setTimestamp(timeStamp)
                .setTradingAction(action)
                .createTrade();
    }
}
