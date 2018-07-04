package com.rook12.stockexchange.controllers;

import com.rook12.stockexchange.dto.*;
import com.rook12.stockexchange.config.StockExchangeConfigurationProperties;
import com.rook12.stockexchange.model.Trade;
import com.rook12.stockexchange.services.DividendService;
import com.rook12.stockexchange.services.TradingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/api")
public class StockMarketController {
    private StockExchangeConfigurationProperties configurationProperties ;

    DividendService dividendService;
    TradingService tradingService;

    @Autowired
    public StockMarketController(StockExchangeConfigurationProperties configurationProperties,
                                 DividendService dividendService,
                                 TradingService tradingService) {
        this.configurationProperties = configurationProperties;
        this.dividendService = dividendService;
        this.tradingService = tradingService;
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

    @GetMapping(path="/calculateVwsp")
    public @ResponseBody
    CalculateVwspResponse calculateVwsp(
            @RequestParam(value="stockSymbol") String stockSymbol
    ) {
        return tradingService.calculateVwsp(stockSymbol);
    }

    @GetMapping(path="/calculateAllShareIndex")
    public @ResponseBody
    CalculateAllShareIndexResponse calculateAllShareIndex() {
        return tradingService.calculateAllShareIndex();
    }

    @PostMapping(path="/simulateTradingActivity")
    public @ResponseBody
    void simulateTradingActivity() {
        tradingService.simulateTradingActivity();
    }

    @PostMapping(path="/executeOrder")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Trade executeOrder(
            @RequestBody OrderRequest orderRequest
    ) {
        return tradingService.executeOrder(orderRequest.getOrderId(),
                orderRequest.getStockSymbol(),
                orderRequest.getAction(),
                orderRequest.getQuantity(),
                orderRequest.getTradePrice());
    }

}
