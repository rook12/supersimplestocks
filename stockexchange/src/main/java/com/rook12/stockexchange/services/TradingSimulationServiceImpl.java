package com.rook12.stockexchange.services;

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

    private int simulateTradingActivityBrokerOrderId;

    @Autowired
    public TradingSimulationServiceImpl(StockRepository stockRepository, TradingService tradingService) {
        this.stockRepository = stockRepository;
        this.tradingService = tradingService;
    }

    /**
     * Generates 100 trades with pseudo random parameters, giving a range of trades within the past 30 minutes
     */
    @Override
    public void simulateTrades() {
        //Simulate 100 trades
        List<Stock> stockList = stockRepository.findAll();

        Random rand = new Random();

        int lowQuantity = 80;
        int highQuantity = 130;

        int lowTime = 1;
        int highTime = 30;

        int lowTradePrice = 50;
        int highTradePrice = 260;

        int startPoint = simulateTradingActivityBrokerOrderId;
        int endPoint = simulateTradingActivityBrokerOrderId + 100;

        //Start at a high number so the simulated trade order IDs don't interfere with what the user is likely to be doing on front end
        for (int i = startPoint; i < endPoint; i++) {
            Stock randomStock = stockList.get(rand.nextInt(stockList.size() - 1));

            tradingService.executeOrder(i,
                    randomStock.getSymbol(),
                    TradingAction.values()[rand.nextInt(1)],
                    rand.nextInt(highQuantity - lowQuantity) + lowQuantity
                    , rand.nextInt(highTradePrice - lowTradePrice) + lowTradePrice,
                    LocalDateTime.now().minusMinutes(rand.nextInt(highTime - lowTime) + lowTime));
        }

        simulateTradingActivityBrokerOrderId += 100;
    }
}
