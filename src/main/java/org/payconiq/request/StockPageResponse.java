package org.payconiq.request;

import org.payconiq.entity.Stock;

import java.util.List;

public class StockPageResponse {

    private Boolean hasPreviousPage;
    private Boolean hasNextPage;
    private List<Stock> stocks;

    public StockPageResponse() {

    }

    public StockPageResponse(List<Stock> stocks, Boolean hasPreviousPage, Boolean hasNextPage) {
        this.stocks = stocks;
        this.hasPreviousPage = hasPreviousPage;
        this.hasNextPage = hasNextPage;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public Boolean getHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(Boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public Boolean getHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(Boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }
}
