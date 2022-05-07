package org.payconiq.request;

import java.math.BigDecimal;

public class StockPriceRequest {

    private Long id;

    private BigDecimal currentPrice;

    public StockPriceRequest() {   }

    public StockPriceRequest(Long id, BigDecimal currentPrice) {
        this.id = id;
        this.currentPrice = currentPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", currentPrice=" + currentPrice + '}';
    }
}
