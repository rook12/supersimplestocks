package com.rook12.stockexchange.controllers;

import com.rook12.stockexchange.dto.DividendYieldResponse;
import com.rook12.stockexchange.config.StockExchangeConfigurationProperties;
import com.rook12.stockexchange.dto.PeRatioResponse;
import com.rook12.stockexchange.services.DividendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/api")
public class StockMarketController {
    private StockExchangeConfigurationProperties configurationProperties ;

    DividendService dividendService;

    @Autowired
    public StockMarketController(StockExchangeConfigurationProperties configurationProperties, DividendService dividendService) {
        this.configurationProperties = configurationProperties;
        this.dividendService = dividendService;
    }


    @GetMapping(path="/calculateDividendYield")
    public @ResponseBody DividendYieldResponse calculateDividendYield(
            @RequestParam(value="stockSymbol") String stockSymbol,
            @RequestParam(value="marketPrice") int marketPrice
    ) {
        return dividendService.getDividendYield(stockSymbol, marketPrice);

    }

    @GetMapping(path="/calculatePeRatio")
    public @ResponseBody PeRatioResponse calculatePeRatio(
            @RequestParam(value="stockSymbol") String stockSymbol,
            @RequestParam(value="marketPrice") int marketPrice
    ) {
        return dividendService.getPeRatio(stockSymbol, marketPrice);

    }
}
