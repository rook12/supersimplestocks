package com.rook12.stockbroker;

import com.rook12.stockbroker.commands.StockBrokerCommands;
import com.rook12.stockbroker.interfaces.StockExchangeInterface;
import com.rook12.stockbroker.dto.DividendYieldResponse;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;



//@RunWith(SpringRunner.class)
//@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class StockbrokerApplicationTests {

	@Ignore
	@Test
	public void contextLoads() {
	}

	@Mock
	private StockExchangeInterface exchangeInterface;

	/*@Test
	public void testGetDividendYield() {
		//Last dividend is 23. 23/120 - 0.192 rounded
        DividendYieldResponse yieldResponse = new DividendYieldResponse();
        yieldResponse.setDividendYield(new BigDecimal("0.192"));

		when(exchangeInterface.getDividendYield("TEST", 120)).thenReturn(yieldResponse);

		StockBrokerCommands stockBrokerCommands = new StockBrokerCommands(exchangeInterface);

		Assert.assertEquals("0.192", stockBrokerCommands.calculateDividendYield("TEST", 120));
	}*/

}
