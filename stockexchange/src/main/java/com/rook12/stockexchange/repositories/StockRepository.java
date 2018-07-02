package com.rook12.stockexchange.repositories;

import com.rook12.stockexchange.model.Stock;

public interface StockRepository {
    Stock findBySymbol(String symbol);
}
