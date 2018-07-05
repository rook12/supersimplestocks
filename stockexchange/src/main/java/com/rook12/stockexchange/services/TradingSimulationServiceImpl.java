package com.rook12.stockexchange.services;

import com.rook12.stockexchange.dto.SimulateTradeReponse;
import com.rook12.stockexchange.model.Stock;
import com.rook12.stockexchange.model.TradingAction;
import com.rook12.stockexchange.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    @Override
    public SimulateTradeReponse simulateTrades() {
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
}
