package tqs_96145;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio {
    private List<Stock> stocks;
    private IStockmarketService stockmarket;

    public StocksPortfolio(IStockmarketService stockmarket) {
        this.stockmarket = stockmarket;
        stocks = new ArrayList<Stock>();
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public double getTotalValue() {
        double result = 0;
        for (Stock s: stocks) {
            result += stockmarket.lookUpPrice(s.getLabel())*s.getQuantity();
        }
        return new BigDecimal(result).setScale(1, RoundingMode.HALF_UP).doubleValue();
    }
}