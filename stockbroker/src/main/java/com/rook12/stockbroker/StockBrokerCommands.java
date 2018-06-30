package com.rook12.stockbroker;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class StockBrokerCommands {

    @ShellMethod("Say hello")
    public String hello() {
        return "hello";
    }

    @ShellMethod("Calculate dividend yield")
    public String calculateDividendYield(String stockSymbol, int marketPrice) {
        return "test - " + stockSymbol + " -- " + Integer.toString(marketPrice);
    }

    @ShellMethod("Calculate P/E ratio")
    public String calculatePERatio(String stockSymbol, int marketPrice) {
        return "";
    }

    @ShellMethod("Execute trade")
    public String executeTrade(String stockSymbol, int quantity, OrderAction orderAction, int tradePrice) {
        return orderAction.toString();
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
