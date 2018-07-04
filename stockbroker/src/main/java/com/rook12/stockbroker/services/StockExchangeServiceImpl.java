package com.rook12.stockbroker.services;

import com.rook12.stockbroker.model.OrderAction;
import com.rook12.stockbroker.dto.DividendYieldRequest;
import com.rook12.stockbroker.dto.DividendYieldResponse;
import com.rook12.stockbroker.dto.TradeRequest;
import com.rook12.stockbroker.dto.TradeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class StockExchangeServiceImpl implements StockExchangeService {
    private static String apiBaseUrl = "http://localhost:8080/api";

    private static final Logger logger = LoggerFactory.getLogger(StockExchangeServiceImpl.class);

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public DividendYieldResponse getDividendYield(String stockSymbol, int marketPrice) {
        logger.info(String.format("calculating dividend yield for %s at price %s", stockSymbol, marketPrice));
        DividendYieldRequest yieldRequest = new DividendYieldRequest();
        yieldRequest.setMarketPrice(marketPrice);
        yieldRequest.setStockSymbol(stockSymbol);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiBaseUrl + "/calculateDividendYield")
                .queryParam("stockSymbol", stockSymbol)
                .queryParam("marketPrice", marketPrice);

        //RestTemplate will quietly not send data in request body in the case of a GET
        HttpEntity<DividendYieldResponse> response = restTemplate.getForEntity(builder.toUriString(), DividendYieldResponse.class);

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

   /* private RestTemplate restTemplate;

    @Autowired
    public StockExchangeImpl(*//*RestTemplate restTemplate*//*) {

        this.restTemplate = new RestTemplate();
    }

    @Override
    public DividendYieldResponse getDividendYield(String stockSymbol, int marketPrice) {
        DividendYieldRequest yieldRequest = new DividendYieldRequest();
        yieldRequest.setMarketPrice(marketPrice);
        yieldRequest.setStockSymbol(stockSymbol);


        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/api/calculateDividendYield")
                .queryParam("stockSymbol", stockSymbol)
                .queryParam("marketPrice", marketPrice);

        //RestTemplate will quietly not send data in request body in the case of a GET
        HttpEntity<DividendYieldResponse> response = restTemplate.getForEntity(builder.toUriString(), DividendYieldResponse.class);

        return response.getBody();
    }*/
}
