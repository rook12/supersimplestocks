package com.rook12.stockexchange.services;

import com.rook12.stockexchange.dto.DividendYieldResponse;
import com.rook12.stockexchange.dto.PeRatioResponse;
import com.rook12.stockexchange.model.Stock;
import com.rook12.stockexchange.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DividendServiceImpl implements DividendService {
    private StockRepository stockRepository;

    @Autowired
    public DividendServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }


    @Override
    public DividendYieldResponse getDividendYield(String stockSymbol, int marketPrice) {
        Stock stock = this.stockRepository.findBySymbol(stockSymbol);
        BigDecimal dividendYield = stock.getDividendYield(marketPrice);
        DividendYieldResponse response = new DividendYieldResponse();
        response.setMarketPrice(marketPrice);
        response.setDividendYield(dividendYield);
        response.setStockSymbol(stock.getSymbol());
        response.setStockType(stock.getStockType());
        return response;
    }

    @Override
    public PeRatioResponse getPeRatio(String stockSymbol, int marketPrice) {
        Stock stock = this.stockRepository.findBySymbol(stockSymbol);
        BigDecimal peRatio = stock.getPeRatio(marketPrice);
        PeRatioResponse response = new PeRatioResponse();
        response.setMarketPrice(marketPrice);
        response.setPeRatio(peRatio);
        response.setStockSymbol(stock.getSymbol());
        response.setStockType(stock.getStockType());
        return response;
    }
}
