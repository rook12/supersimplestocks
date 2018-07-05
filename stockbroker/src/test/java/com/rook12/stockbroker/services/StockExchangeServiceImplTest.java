package com.rook12.stockbroker.services;

import com.rook12.stockbroker.dto.*;
import com.rook12.stockbroker.model.OrderAction;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class StockExchangeServiceImplTest {


    private RestTemplate restTemplate = new RestTemplate();
    private MockRestServiceServer mockRestServiceServer= MockRestServiceServer.bindTo(restTemplate).build();

    StockExchangeServiceImpl stockExchangeService = new StockExchangeServiceImpl(restTemplate);

    @Test
    public void getDividendYield() {
        String dividendJson = "{\"dividendYield\":12.34000,\n" +
                "  \"stockType\":\"COMMON\",\n" +
                "  \"stockSymbol\":\"POP\",\n" +
                "  \"marketPrice\":100" +
                "}";
        mockRestServiceServer.expect(once(), requestTo("http://localhost:8080/api/calculateDividendYield?stockSymbol=POP&marketPrice=100"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(dividendJson, MediaType.APPLICATION_JSON_UTF8));

        DividendYieldResponse response = stockExchangeService.getDividendYield("POP", 100);

        assertEquals(new BigDecimal("12.34000"), response.getDividendYield());
    }

    @Test
    public void getPeRatio() {
        String peRatioResponseJson = "{\"peRatio\":12.50000,\"stockType\":\"COMMON\",\"stockSymbol\":\"POP\",\"marketPrice\":100}";

        mockRestServiceServer.expect(once(), requestTo("http://localhost:8080/api/calculatePeRatio?stockSymbol=POP&marketPrice=100"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(peRatioResponseJson, MediaType.APPLICATION_JSON_UTF8));

        PeRatioResponse response = stockExchangeService.getPeRatio("POP", 100);

        assertEquals(new BigDecimal("12.50000"), response.getPeRatio());
    }

    @Test
    public void executeTrade() {
        String executeTradeResponseJson = "{\"timestamp\":\"2018-07-05T20:20:24.199\",\"exchangeTradeId\":\"5612c52f-b071-43ce-a42b-7cea75a11237\",\"stockSymbol\":\"TEA\",\"brokerOrderId\":2,\"tradingAction\":\"BUY\",\"quantity\":100,\"tradePrice\":150}";

        mockRestServiceServer.expect(once(), requestTo("http://localhost:8080/api/executeOrder"))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(executeTradeResponseJson, MediaType.APPLICATION_JSON_UTF8));

        TradeResponse response = stockExchangeService.executeTrade(2, "TEA", OrderAction.BUY, 100, 150);
        assertEquals("TEA", response.getStockSymbol());
    }

    @Test
    public void simulateTrades() {
        fail();
    }

    @Test
    public void getVwsp() {
        String vwspResponseJson = "{\"stockSymbol\":\"TEA\",\"earliestTimeForTrade\":\"2018-07-05T19:25:03.765\",\"vwsp\":204.18175,\"tradeCount\":13}";

        mockRestServiceServer.expect(once(), requestTo("http://localhost:8080/api/calculateVwsp?stockSymbol=TEA"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(vwspResponseJson, MediaType.APPLICATION_JSON_UTF8));

        VwspResponse vwspResponse = stockExchangeService.getVwsp("TEA");
        assertEquals(new BigDecimal("204.18175"), vwspResponse.getVwsp());
    }

    @Test
    public void getAllShareIndex() {
        String allShareIndexResponseJson = "{\"allShareIndex\":143.68102,\"tradeCount\":100,\"indexComputationTimestamp\":\"2018-07-05T20:05:45.4\"}";

        System.out.println(allShareIndexResponseJson);
        mockRestServiceServer.expect(once(), requestTo("http://localhost:8080/api/calculateAllShareIndex"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(allShareIndexResponseJson, MediaType.APPLICATION_JSON_UTF8));

        AllShareIndexResponse response = stockExchangeService.getAllShareIndex();
        assertEquals(new BigDecimal("143.68102"), response.getAllShareIndex());
    }
}