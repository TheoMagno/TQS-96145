package tqs_96145;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.hamcrest.core.Is;

import static org.hamcrest.MatcherAssert.assertThat; 
import org.junit.jupiter.api.Test;

public class StockTest {
    @Test
    public void testTotalValue() {
        //Create mock
        IStockmarketService stockmarket = mock(IStockmarketService.class);
        //Create StocksPortfolio instance
        StocksPortfolio stocks = new StocksPortfolio(stockmarket);
        //Load mock with expectations
        when(stockmarket.lookUpPrice("SONY")).thenReturn(104.62);
        when(stockmarket.lookUpPrice("MICROSOFT")).thenReturn(295.22);
        //Execute the test
        stocks.addStock(new Stock("SONY", 3));
        stocks.addStock(new Stock("MICROSOFT", 2));
        //Verify the result
        assertThat(stocks.getTotalValue(), Is.is(904.3));
    }
}
