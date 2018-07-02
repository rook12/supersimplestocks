package com.rook12.stockexchange.services;

import com.rook12.stockexchange.dto.DividendYieldResponse;
import com.rook12.stockexchange.dto.PeRatioResponse;

public interface DividendService {
    DividendYieldResponse getDividendYield(String stockSymbol, int marketPrice);
    PeRatioResponse getPeRatio(String stockSymbol, int marketPrice);
}
