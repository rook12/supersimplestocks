package com.rook12.stockexchange.model;

import java.math.BigDecimal;

public interface Stock {
    BigDecimal getDividendYield(int marketPrice);
}
