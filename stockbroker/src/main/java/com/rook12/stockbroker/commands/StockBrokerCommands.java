package com.rook12.stockbroker.commands;

import com.rook12.stockbroker.dto.*;
import com.rook12.stockbroker.model.OrderAction;
import com.rook12.stockbroker.services.StockExchangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class StockBrokerCommands {

    private StockExchangeService exchangeService;

    private int orderId = 0;

    private static final Logger logger = LoggerFactory.getLogger(StockBrokerCommands.class);

    @Autowired
    public StockBrokerCommands(StockExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @ShellMethod("Calculate dividend yield")
    public String calculateDividendYield(String stockSymbol, int marketPrice) {
        DividendYieldResponse dividendYieldResponse = exchangeService.getDividendYield(stockSymbol, marketPrice);
        logger.info(dividendYieldResponse.toString());
        return dividendYieldResponse.getDividendYield().toString();
    }

    @ShellMethod("Calculate P/E ratio")
    public String calculatePERatio(String stockSymbol, int marketPrice) {
        logger.info(String.format("calculate P/E Ratio for - %s at price - %s", stockSymbol, marketPrice));
        PeRatioResponse response = exchangeService.getPeRatio(stockSymbol, marketPrice);
        logger.info("stock - " + response.getStockSymbol());
        logger.info("stock type - " + response.getStockType());
        logger.info("market price - " + response.getMarketPrice());
        logger.info("pe ratio - " +response.getPeRatio());
        return response.getPeRatio().toString();
    }

    @ShellMethod("Execute trade")
    public int executeTrade(String stockSymbol, int quantity, OrderAction orderAction, int tradePrice) {
        //Stock exchange will generate its own ID and return it, but we want to initiate an order with own ID as well (without forcing user to enter it)
        //Just increment it after each order and hold in memory. Will be lost on app restart, so if you've executed orders you'll want
        //to restart stock exchange app as well
        orderId += 1;
        return executeTrade(stockSymbol, quantity, orderAction, tradePrice, orderId);
    }

    public int executeTrade(String stockSymbol, int quantity, OrderAction orderAction, int tradePrice, int orderId) {
        //Stock exchange will generate its own ID and return it, but we want to initiate an order with own ID as well (without forcing user to enter it)
        //Just increment it after each order and hold in memory. Will be lost on app restart, so if you've executed orders you'll want
        //to restart stock exchange app as well
        TradeResponse response = exchangeService.executeTrade(orderId, stockSymbol, orderAction, quantity, tradePrice);
        logger.info("executed trade");
        logger.info("broker order id " + response.getBrokerOrderId());
        logger.info("exchange order id " + response.getExchangeTradeId());
        logger.info("stock " + response.getStockSymbol());
        logger.info("quantity " + response.getQuantity());
        logger.info("execution time " + response.getTimestamp());
        logger.info("trade action " + response.getTradingAction());
        return response.getBrokerOrderId();
    }

    @ShellMethod("Calculate Volume Weighted Stock Price")
    public String calculateVWSP(String stockSymbol) {
        logger.info("calculate vwsp for - " + stockSymbol);
        VwspResponse vwspResponse = exchangeService.getVwsp(stockSymbol);
        logger.info("earliest trade time - " + vwspResponse.getEarliestTimeForTrade().toString());
        logger.info("number of trades - " + vwspResponse.getTradeCount());
        logger.info("VWSP - " + vwspResponse.getVwsp());
        return vwspResponse.getVwsp().toString();
    }

    @ShellMethod("Instruct stock market to simulate 100 trades with pseudo-random values")
    public String simulateTrades() {
        exchangeService.simulateTrades();
        return "finished simulating trades";
    }

    @ShellMethod("Calculate all share index")
    public String calculateAllShareIndex() {
        logger.info("calculateAllShareIndex");
        AllShareIndexResponse response = exchangeService.getAllShareIndex();
        logger.info("index computation timestamp - " + response.getIndexComputationTimestamp());
        logger.info("trade count - " + response.getTradeCount());
        logger.info("all share index - " + response.getAllShareIndex());
        return response.getAllShareIndex().toString();
    }
}
