package com.rook12.stockexchange.controllers;

import com.rook12.stockexchange.config.StockExchangeConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/api")
public class StockMarketController {
    private StockExchangeConfigurationProperties configurationProperties ;

    @Autowired
    public StockMarketController(StockExchangeConfigurationProperties configurationProperties) {
        this.configurationProperties = configurationProperties;
    }

    @GetMapping(path="/name")
    public @ResponseBody String getName() {
        return "name - " + configurationProperties.getName();
    }
}
