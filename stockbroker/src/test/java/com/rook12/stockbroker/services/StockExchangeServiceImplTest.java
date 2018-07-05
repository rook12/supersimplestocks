package com.rook12.stockbroker.services;

import com.rook12.stockbroker.dto.DividendYieldResponse;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static com.sun.javaws.JnlpxArgs.verify;
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
        String dividendJson = "{\"dividendYield\":12.34,\n" +
                "  \"stockType\":\"COMMON\",\n" +
                "  \"stockSymbol\":\"POP\",\n" +
                "  \"marketPrice\":100" +
                "}";
        mockRestServiceServer.expect(manyTimes(), requestTo("http://localhost:8080/api/calculateDividendYield?stockSymbol=POP&marketPrice=100"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(dividendJson, MediaType.APPLICATION_JSON_UTF8));

        DividendYieldResponse response = stockExchangeService.getDividendYield("POP", 100);

        assertEquals(new BigDecimal("12.34"), response.getDividendYield());
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