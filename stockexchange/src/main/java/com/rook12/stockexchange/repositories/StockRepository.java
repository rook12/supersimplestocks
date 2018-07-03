package com.rook12.stockexchange.repositories;

import com.rook12.stockexchange.model.Stock;

import java.util.List;

public interface StockRepository {
    Stock findBySymbol(String symbol);
    List<Stock> findAll();
}
