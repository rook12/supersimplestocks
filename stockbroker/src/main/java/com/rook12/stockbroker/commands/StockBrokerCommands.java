package com.rook12.stockbroker.commands;

import com.rook12.stockbroker.OrderAction;
import com.rook12.stockbroker.interfaces.StockExchangeInterface;
import com.rook12.stockbroker.dto.DividendYieldResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.stereotype.Component;

@Component
public class StockBrokerCommands implements CommandMarker {

    private StockExchangeInterface exchangeInterface;

    private static final Logger logger = LoggerFactory.getLogger(StockBrokerCommands.class);

    /*@Autowired
    public StockBrokerCommands(StockExchangeInterface exchangeInterface) {
        this.exchangeInterface = exchangeInterface;
    }*/

    @CliAvailabilityIndicator(value="hello")
    public boolean isPrintCommandAvailable()
    {
        return Boolean.TRUE;
    }

    @CliCommand(value={"hello","hi"})
    public String hello() {

        logger.info("executing hello");
        return "hello";
    }

    //@ShellMethod("Calculate dividend yield")
    public String calculateDividendYield(String stockSymbol, int marketPrice) {
        logger.info(String.format("calculating dividend yield for %s at price %s", stockSymbol, marketPrice));
        DividendYieldResponse dividendYieldResponse = exchangeInterface.getDividendYield(stockSymbol, marketPrice);
        logger.info(dividendYieldResponse.toString());
        return dividendYieldResponse.getDividendYield().toString();
    }

   // @ShellMethod("Calculate P/E ratio")
    public String calculatePERatio(String stockSymbol, int marketPrice) {
        return "";
    }

    //@ShellMethod("Execute trade")
    public String executeTrade(String stockSymbol, int quantity, OrderAction orderAction, int tradePrice) {
        return orderAction.toString();
    }

   // @ShellMethod("Calculate Volume Weighted Stock Price")
    /*public String calculateVWSP(String stockSymbol, @ShellOption(defaultValue = "15") int timePeriod) {
        return Integer.toString(timePeriod);
    }*/

    //@ShellMethod("Calculate all share index")
    public String calculateAllShareIndex() {
        return "";
    }
}
