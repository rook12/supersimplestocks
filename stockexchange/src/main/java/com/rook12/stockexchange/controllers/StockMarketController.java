package com.rook12.stockexchange.controllers;

import com.rook12.stockexchange.DividendYieldResponse;
import com.rook12.stockexchange.config.StockExchangeConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

@Controller
@RequestMapping(path="/api")
public class StockMarketController {
    private StockExchangeConfigurationProperties configurationProperties ;

    @Autowired
    public StockMarketController(StockExchangeConfigurationProperties configurationProperties) {
        this.configurationProperties = configurationProperties;
    }

    @GetMapping(path="/calculateDividendYield")
    public @ResponseBody DividendYieldResponse calculateDividendYield() {
        DividendYieldResponse response = new DividendYieldResponse();
        response.setMarketPrice(100);
        response.setDividendYield(new BigDecimal("2.3"));
        response.setStockSymbol("TEST");
        response.setStockType("PREFERRED");
        return response;
        //return "name - " + configurationProperties.getName();

    }
}
