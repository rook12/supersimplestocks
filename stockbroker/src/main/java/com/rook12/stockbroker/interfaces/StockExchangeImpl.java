package com.rook12.stockbroker.interfaces;

import com.rook12.stockbroker.dto.DividendYieldRequest;
import com.rook12.stockbroker.dto.DividendYieldResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class StockExchangeImpl implements StockExchangeInterface {
    @Override
    public DividendYieldResponse getDividendYield(String stockSymbol, int marketPrice) {
        return null;
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
