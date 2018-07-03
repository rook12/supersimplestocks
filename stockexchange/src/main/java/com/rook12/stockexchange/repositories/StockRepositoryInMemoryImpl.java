package com.rook12.stockexchange.repositories;

import com.rook12.stockexchange.model.CommonStock;
import com.rook12.stockexchange.model.PreferredStock;
import com.rook12.stockexchange.model.Stock;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;

@Repository
public class StockRepositoryInMemoryImpl implements StockRepository {
    private ArrayList<Stock> stockList = new ArrayList<>();

    public StockRepositoryInMemoryImpl() {
        this.stockList.add(new CommonStock("TEA", 0, 100));
        this.stockList.add(new CommonStock("POP", 8, 100));
        this.stockList.add(new CommonStock("ALE", 23, 60));
        this.stockList.add(new PreferredStock("GIN", 8, 100, new BigDecimal("0.02")));
        this.stockList.add(new CommonStock("JOE", 13, 250));
    }

    public Stock findBySymbol(String symbol) {
        return this.stockList.stream()
                .filter(x -> x.getSymbol().equals(symbol))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Could not find stock with symbol " + symbol));
    }
}
