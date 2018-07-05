package com.rook12.stockbroker.services;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;
import static org.springframework.test.web.client.ExpectedCount.manyTimes;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class StockExchangeServiceImplTest {


    private RestTemplate restTemplate = new RestTemplate();
    private MockRestServiceServer mockRestServiceServer= MockRestServiceServer.bindTo(restTemplate).build();

    StockExchangeServiceImpl stockExchangeService = new StockExchangeServiceImpl(restTemplate);

    @Test
    public void getDividendYield() {
        mockRestServiceServer.expect(manyTimes(), requestTo(""))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("", MediaType.APPLICATION_JSON));

        stockExchangeService.getDividendYield("POP", 100);
    }

    @Test
    public void getPeRatio() {
        fail();
    }

    @Test
    public void executeTrade() {
        fail();
    }

    @Test
    public void simulateTrades() {
        fail();
    }

    @Test
    public void getVwsp() {
        fail();
    }

    @Test
    public void getAllShareIndex() {
        fail();
    }
}