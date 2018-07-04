package com.rook12.stockbroker.commands;

import com.rook12.stockbroker.model.OrderAction;
import com.rook12.stockbroker.dto.TradeResponse;
import com.rook12.stockbroker.services.StockExchangeService;
import com.rook12.stockbroker.dto.DividendYieldResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class StockBrokerCommands {

    private StockExchangeService exchangeInterface;

    private int orderId = 0;

    private static final Logger logger = LoggerFactory.getLogger(StockBrokerCommands.class);

    @Autowired
    public StockBrokerCommands(StockExchangeService exchangeInterface) {
        this.exchangeInterface = exchangeInterface;
    }

    @ShellMethod("Calculate dividend yield")
    public String calculateDividendYield(String stockSymbol, int marketPrice) {
        DividendYieldResponse dividendYieldResponse = exchangeInterface.getDividendYield(stockSymbol, marketPrice);
        logger.info(dividendYieldResponse.toString());
        return dividendYieldResponse.getDividendYield().toString();
    }

    @ShellMethod("Calculate P/E ratio")
    public String calculatePERatio(String stockSymbol, int marketPrice) {
        return "";
    }

    @ShellMethod("Execute trade")
    public String executeTrade(String stockSymbol, int quantity, OrderAction orderAction, int tradePrice) {
        //Stock exchange will generate its own ID and return it, but we want to initiate an order with own ID as well (without forcing user to enter it)
        //Just increment it after each order and hold in memory. Will be lost on app restart, so if you've executed orders you'll want
        //to restart stock exchange app as well
        orderId += 1;
        TradeResponse response = exchangeInterface.executeTrade(orderId, stockSymbol, orderAction, quantity, tradePrice);
        return response.toString();
    }

    @ShellMethod("Calculate Volume Weighted Stock Price")
    public String calculateVWSP(String stockSymbol, @ShellOption(defaultValue = "15") int timePeriod) {
        return Integer.toString(timePeriod);
    }

    @ShellMethod("Calculate all share index")
    public String calculateAllShareIndex() {
        return "";
    }
}
