package com.rook12.stockbroker.services;

import com.rook12.stockbroker.dto.*;
import com.rook12.stockbroker.model.OrderAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class StockExchangeServiceImpl implements StockExchangeService {
    private static String apiBaseUrl = "http://localhost:8080/api";

    private static final Logger logger = LoggerFactory.getLogger(StockExchangeServiceImpl.class);

    private RestTemplate restTemplate;

    @Autowired
    public StockExchangeServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public DividendYieldResponse getDividendYield(String stockSymbol, int marketPrice) {
        logger.info(String.format("calculating dividend yield for %s at price %s", stockSymbol, marketPrice));

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiBaseUrl + "/calculateDividendYield")
                .queryParam("stockSymbol", stockSymbol)
                .queryParam("marketPrice", marketPrice);

        //RestTemplate will quietly not send data in request body in the case of a GET
        HttpEntity<DividendYieldResponse> response = restTemplate.getForEntity(builder.toUriString(), DividendYieldResponse.class);

        return response.getBody();
    }

    @Override
    public PeRatioResponse getPeRatio(String stockSymbol, int marketPrice) {
        logger.info(String.format("calculating pe ratio for %s at price %s", stockSymbol, marketPrice));

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiBaseUrl + "/calculatePeRatio")
                .queryParam("stockSymbol", stockSymbol)
                .queryParam("marketPrice", marketPrice);

        //RestTemplate will quietly not send data in request body in the case of a GET
        HttpEntity<PeRatioResponse> response = restTemplate.getForEntity(builder.toUriString(), PeRatioResponse.class);

        return response.getBody();
    }

    @Override
    public TradeResponse executeTrade(int orderId, String stockSymbol, OrderAction action, int quantity, int tradePrice) {
        TradeRequest request = new TradeRequest(orderId,
                stockSymbol,
                action,
                quantity,
                tradePrice);
        HttpEntity<TradeResponse> response = restTemplate.postForEntity(
                UriComponentsBuilder.fromHttpUrl(apiBaseUrl + "/executeOrder").toUriString(),
                request,
                TradeResponse.class);

        return response.getBody();
    }

    @Override
    public SimulateTradeResponse simulateTrades() {
        logger.info("simulate trades");
        HttpEntity<SimulateTradeResponse> response = restTemplate.getForEntity(UriComponentsBuilder.fromHttpUrl(
                apiBaseUrl + "/simulateTrades").toUriString(),
                SimulateTradeResponse.class);
        return response.getBody();
    }

    @Override
    public VwspResponse getVwsp(String stockSymbol) {
        logger.info("calculate volume weighted stock price for stock - "  +stockSymbol);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiBaseUrl + "/calculateVwsp").queryParam("stockSymbol", stockSymbol);
        HttpEntity<VwspResponse> response = restTemplate.getForEntity(builder.toUriString(), VwspResponse.class);

        return response.getBody();
    }

    @Override
    public AllShareIndexResponse getAllShareIndex() {
        logger.info("calculate all share index");
        HttpEntity<AllShareIndexResponse> response = restTemplate.getForEntity(
                UriComponentsBuilder.fromHttpUrl(apiBaseUrl + "/calculateAllShareIndex").toUriString(),
                AllShareIndexResponse.class);

        return response.getBody();
    }

}
