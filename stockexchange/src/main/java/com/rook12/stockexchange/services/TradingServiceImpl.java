package com.rook12.stockexchange.services;

import com.rook12.stockexchange.dto.CalculateAllShareIndexResponse;
import com.rook12.stockexchange.dto.CalculateVwspResponse;
import com.rook12.stockexchange.model.Stock;
import com.rook12.stockexchange.model.Trade;
import com.rook12.stockexchange.model.TradeBuilder;
import com.rook12.stockexchange.model.TradingAction;
import com.rook12.stockexchange.repositories.StockRepository;
import com.rook12.stockexchange.repositories.TradingActivityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TradingServiceImpl implements TradingService {
    private TradingActivityRepository tradingActivityRepository;
    private StockRepository stockRepository;

    private static final int VWSP_MINUTES = 15;
    private static final Logger logger = LoggerFactory.getLogger(TradingServiceImpl.class);

    @Autowired
    public TradingServiceImpl(TradingActivityRepository tradingActivityRepository, StockRepository stockRepository) {
        this.tradingActivityRepository = tradingActivityRepository;
        this.stockRepository = stockRepository;
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
    public CalculateVwspResponse calculateVwsp(String stockSymbol) {
        logger.info("calculateVwsp");

        LocalDateTime oldestTradeTime = LocalDateTime.now().minusMinutes(VWSP_MINUTES);
        List<Trade> trades = tradingActivityRepository.findTradesAfterTime(oldestTradeTime)
                .stream()
                .filter(trade -> trade.getStockSymbol().equals(stockSymbol))
                .collect(Collectors.toList());
        logger.info(String.format("trades in last %s minutes for stock %s - %s", VWSP_MINUTES, stockSymbol, trades.size()));

        if(trades.size() == 0) {
            return new CalculateVwspResponse(stockSymbol, oldestTradeTime, new BigDecimal(0), 0);
        }

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

        return new CalculateVwspResponse(stockSymbol, oldestTradeTime, vwsp, trades.size());
    }

    /**
     * Calculate all share index using geometric mean formula
     *
     * NOTE: Based on the test data im using for trade price (in the region of 100), this method starts to fall over after around 100-150
     * trades. Need to look into better way. Making the product of the trades results in very large numbers. Tried using bigdecimal, this holds
     * the product but I can't use the pow() function on BigDecimal on a fraction (only full int)
     * @return CalculateAllShareIndexResponse
     */
    @Override
    public CalculateAllShareIndexResponse calculateAllShareIndex() {
        logger.info("calculateAllShareIndex");
        List<Trade> trades = tradingActivityRepository.findAll();
        if(trades.size() == 0) {
            return new CalculateAllShareIndexResponse(new BigDecimal("0"), 0, LocalDateTime.now());
        }
        //TODO: Probably some nicer way of doing this through a reduce function, come back to
        double productOfTradePrice = 1;
        for (int tradePrice : trades.stream()
                     .map(Trade::getTradePrice)
                     .collect(Collectors.toList())) {
            productOfTradePrice*=tradePrice;
        }
        logger.info("productOfTradePrice - " + Double.toString(productOfTradePrice));

        double geometricMean = Math.pow(productOfTradePrice, 1.0 / trades.size());
        BigDecimal bd = new BigDecimal(Double.toString(geometricMean)).setScale(5, BigDecimal.ROUND_HALF_UP);
        logger.info("geometricMean - " + bd.toString());
        return new CalculateAllShareIndexResponse(bd, trades.size(), LocalDateTime.now());
    }

    @Override
    public void simulateTradingActivity() {
        //Simulate 100 trades
        List<Stock> stockList =  stockRepository.findAll();

        Random rand = new Random();

        int lowQuantity = 80;
        int highQuantity = 130;

        int lowTime = 1;
        int highTime = 30;

        int lowTradePrice = 50;
        int highTradePrice = 260;

        //Start at a high number so the simulated trade order IDs don't interfere with what the user is likely to be doing on front end
        for (int i = 10000; i < 10100; i++) {
            Stock randomStock = stockList.get(rand.nextInt(stockList.size()-1));

            executeOrder(i,
                    randomStock.getSymbol(),
                    TradingAction.values()[rand.nextInt(1)],
                    rand.nextInt(highQuantity-lowQuantity) + lowQuantity
                    , rand.nextInt(highTradePrice-lowTradePrice) + lowTradePrice,
                    LocalDateTime.now().minusMinutes(rand.nextInt(highTime-lowTime) + lowTime) );
        }
    }
}
