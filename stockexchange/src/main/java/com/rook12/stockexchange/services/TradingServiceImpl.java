package com.rook12.stockexchange.services;

import com.rook12.stockexchange.model.Trade;
import com.rook12.stockexchange.model.TradeBuilder;
import com.rook12.stockexchange.model.TradingAction;
import com.rook12.stockexchange.repositories.TradingActivityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.Bidi;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TradingServiceImpl implements TradingService {
    private TradingActivityRepository tradingActivityRepository;

    private static final int VWSP_MINUTES = 15;
    private static final Logger logger = LoggerFactory.getLogger(TradingServiceImpl.class);

    @Autowired
    public TradingServiceImpl(TradingActivityRepository tradingActivityRepository) {
        this.tradingActivityRepository = tradingActivityRepository;
    }

    @Override
    public Trade executeOrder(int orderId, String stockSymbol, TradingAction action, int quantity, int tradePrice) {
        return this.executeOrder(orderId, stockSymbol, action, quantity, tradePrice, LocalDateTime.now());
    }

    /**
     * Execute a trade.
     *
     * @param orderId Broker order ID - this should be unique
     * @param stockSymbol Symbol
     * @param action BUY or SELL
     * @param quantity Quantity
     * @param tradePrice Trade price
     * @param timeStamp Expose extra timeStamp param for allowing trades to be simulated in the past.
     * @return A Trade
     */
    @Override
    public Trade executeOrder(int orderId, String stockSymbol, TradingAction action, int quantity, int tradePrice, LocalDateTime timeStamp) {
        Trade trade = new TradeBuilder().setExchangeTradeId(UUID.randomUUID())
                .setTradePrice(tradePrice)
                .setBrokerOrderId(orderId)
                .setQuantity(quantity)
                .setStockSymbol(stockSymbol)
                .setTimestamp(timeStamp)
                .setTradingAction(action)
                .createTrade();
        tradingActivityRepository.save(trade);
        logger.info("order executed - " + trade.getBrokerOrderId());
        return tradingActivityRepository.findByBrokerOrderId(orderId);
    }

    @Override
    public BigDecimal calculateVwsp(String stockSymbol) {
        logger.info("calculateVwsp");

        LocalDateTime oldestTradeTime = LocalDateTime.now().minusMinutes(VWSP_MINUTES);
        List<Trade> trades = tradingActivityRepository.findTradesAfterTime(oldestTradeTime)
                .stream()
                .filter(trade -> trade.getStockSymbol().equals(stockSymbol))
                .collect(Collectors.toList());
        logger.info(String.format("trades in last %s minutes for stock %s - %s", VWSP_MINUTES, stockSymbol, trades.size()));

        //VWSP
        //Σ = Sigma = which means sum
        //For each trade - trade price x quantity, then add them up to get the total. -- A
        //For each trade - add the quantities together --B
        //A / B is the VWSP
        int totalSumOfTradesTimesQuantity = trades.stream().mapToInt(trade -> (trade.getTradePrice() * trade.getQuantity())).sum();
        logger.info("totalProductOfTradesTimesQuantity - " + Integer.toString(totalSumOfTradesTimesQuantity));

        int totalQuantity = trades.stream().mapToInt(trade -> (trade.getQuantity())).sum();
        logger.info("totalQuantity - " + Integer.toString(totalQuantity));

        BigDecimal vwsp = BigDecimal.valueOf(totalSumOfTradesTimesQuantity)
                .divide(BigDecimal.valueOf(totalQuantity), 5, RoundingMode.HALF_UP);
        logger.info("vwsp - " + vwsp.toString());
        return vwsp;
    }

    @Override
    public BigDecimal calculateAllShareIndex() {
        logger.info("calculateAllShareIndex");
        List<Trade> trades = tradingActivityRepository.findAll();


        //TODO: Probably some nicer way of doing this through a reduce function, come back to
        BigDecimal productOfTradePrice = new BigDecimal("1");
        for (int tradePrice : trades.stream()
                     .map(Trade::getTradePrice)
                     .collect(Collectors.toList())) {
            //logger.debug("trade price - " + Integer.toString(tradePrice));
            //logger.debug("product before - " + productOfTradePrice.toString());
            productOfTradePrice = productOfTradePrice.multiply(BigDecimal.valueOf(tradePrice));
            //logger.debug("product after - " + productOfTradePrice.toString());
        }
        //MathContext.
        logger.info("productOfTradePrice - " + productOfTradePrice.toString());

        logger.info("productOfTradePrice - " + productOfTradePrice.doubleValue());

        /*logger.info("double max - " + Double.MAX_VALUE);
        BigDecimal x = productOfTradePrice.pow(11);
        logger.info("x - " + x.toString());*/
        double x = Math.pow(productOfTradePrice.doubleValue(), 1.0 / trades.size());

        //productOfTradePrice.pow


        logger.info("x - " + Double.toString(x));

      /*  double squareRootOfTradeCount = Math.sqrt(trades.size());
        logger.info(String.format("trade count - %s square root - %s",
                Integer.toString(trades.size()),
                Double.toString(squareRootOfTradeCount)));*/

        //Geometric mean
        //

        return null;
    }
}
