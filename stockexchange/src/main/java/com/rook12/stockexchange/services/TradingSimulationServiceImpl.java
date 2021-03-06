package com.rook12.stockexchange.services;

import com.rook12.stockexchange.dto.SimulateTradeReponse;
import com.rook12.stockexchange.model.Stock;
import com.rook12.stockexchange.model.TradingAction;
import com.rook12.stockexchange.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Component
public class TradingSimulationServiceImpl implements TradingSimulationService {

    private StockRepository stockRepository;
    private TradingService tradingService;

    //Start at a high number so the simulated trade order IDs don't interfere with what the user is likely to be doing on front end
    private int simulateTradingActivityBrokerOrderId = 2000;

    private static int LOWER_QUANT = 80;
    private static int UPPER_QUANTITY = 130;
    private static int LOWER_TIME = 1;
    private static int UPPER_TIME = 30;
    private static int LOWER_TRADE_PRICE = 50;
    private static int UPPER_TRADE_PRICE = 260;

    @Autowired
    public TradingSimulationServiceImpl(StockRepository stockRepository, TradingService tradingService) {
        this.stockRepository = stockRepository;
        this.tradingService = tradingService;
    }

    /**
     * Generates 100 trades with pseudo random parameters, giving a range of trades within the past 30 minutes
     */
    public SimulateTradeReponse simulate100PseudoRandomTrades() {
        //Simulate 100 trades
        List<Stock> stockList = stockRepository.findAll();

        Random rand = new Random();

        int startPoint = simulateTradingActivityBrokerOrderId;
        int endPoint = simulateTradingActivityBrokerOrderId + 100;

        SimulateTradeReponse response = new SimulateTradeReponse();


        for (int i = startPoint; i < endPoint; i++) {
            Stock randomStock = stockList.get(rand.nextInt(stockList.size() - 1));

            response.addTrade(tradingService.executeOrder(i,
                    randomStock.getSymbol(),
                    TradingAction.values()[rand.nextInt(1)],
                    rand.nextInt(UPPER_QUANTITY - LOWER_QUANT) + LOWER_QUANT
                    , rand.nextInt(UPPER_TRADE_PRICE - LOWER_TRADE_PRICE) + LOWER_TRADE_PRICE,
                    LocalDateTime.now().minusMinutes(rand.nextInt(UPPER_TIME - LOWER_TIME) + LOWER_TIME)));
        }

        simulateTradingActivityBrokerOrderId += 100;

        response.setSimulationComplete(LocalDateTime.now());
        return response;
    }

    /**
     * Generates the same 10 trades every time
     * @return SimulateTradeReponse
     */
    public SimulateTradeReponse simulate10ConsistentTrades() {
        LocalDateTime now = LocalDateTime.now();

        SimulateTradeReponse response = new SimulateTradeReponse();
        response.addTrade(tradingService.executeOrder(simulateTradingActivityBrokerOrderId, "TEA", TradingAction.BUY, 88, 120, now));
        simulateTradingActivityBrokerOrderId += 1;
        response.addTrade(tradingService.executeOrder(simulateTradingActivityBrokerOrderId, "POP", TradingAction.BUY, 100, 130, now));
        simulateTradingActivityBrokerOrderId += 1;
        response.addTrade(tradingService.executeOrder(simulateTradingActivityBrokerOrderId, "POP", TradingAction.BUY, 130, 140, now));
        simulateTradingActivityBrokerOrderId += 1;
        response.addTrade(tradingService.executeOrder(simulateTradingActivityBrokerOrderId, "ALE", TradingAction.BUY, 124, 75, now));
        simulateTradingActivityBrokerOrderId += 1;
        response.addTrade(tradingService.executeOrder(simulateTradingActivityBrokerOrderId, "GIN", TradingAction.BUY, 168, 105, now));
        simulateTradingActivityBrokerOrderId += 1;
        response.addTrade(tradingService.executeOrder(simulateTradingActivityBrokerOrderId, "JOE", TradingAction.BUY, 351, 260, now));
        simulateTradingActivityBrokerOrderId += 1;
        response.addTrade(tradingService.executeOrder(simulateTradingActivityBrokerOrderId, "JOE", TradingAction.BUY, 687, 285, now));
        simulateTradingActivityBrokerOrderId += 1;
        //These trades below should not factor into calculation VWSP as they are beyond 15 minutes
        response.addTrade(tradingService.executeOrder(simulateTradingActivityBrokerOrderId, "TEA", TradingAction.BUY, 504, 120, now.minusMinutes(30)));
        simulateTradingActivityBrokerOrderId += 1;
        response.addTrade(tradingService.executeOrder(simulateTradingActivityBrokerOrderId, "GIN", TradingAction.BUY, 80, 120, now.minusMinutes(30)));
        simulateTradingActivityBrokerOrderId += 1;
        response.addTrade(tradingService.executeOrder(simulateTradingActivityBrokerOrderId, "JOE", TradingAction.BUY, 345, 290, now.minusMinutes(30)));
        simulateTradingActivityBrokerOrderId += 1;

        response.setSimulationComplete(LocalDateTime.now());
        return response;
    }

    @Override
    public SimulateTradeReponse simulateTrades(String mode) {
        if(mode.equals("random")) {
            return simulate100PseudoRandomTrades();
        }
        return simulate10ConsistentTrades();
    }
}
