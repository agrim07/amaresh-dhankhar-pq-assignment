package org.payconiq.service;

import org.payconiq.entity.Stock;
import org.payconiq.exception.StockNotFoundException;
import org.payconiq.repository.StockRepository;
import org.payconiq.request.StockPageResponse;
import org.payconiq.request.StockPriceRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Value( "${records.per.page}" )
    private int recordsPerPage;

    private StockRepository repository;

    public StockService(StockRepository repository) {
        this.repository = repository;
    }

    public StockPageResponse fetchAllStocks(Integer pageNo) {
        Pageable page = PageRequest.of(pageNo-1, recordsPerPage);
        Slice<Stock> stocks = repository.findAll(page);
        return new StockPageResponse(stocks.getContent(), stocks.hasPrevious(), stocks.hasNext());
    }

    public Stock createStock(Stock stock) {
        return repository.save(stock);
    }

    public Stock fetchStockById(Long stockId) {
        Stock stock = this.repository.findById(stockId)
                .orElseThrow(() -> new StockNotFoundException("Stock not found for this id :: " + stockId));
        return stock;
    }

    public Stock updateStockPrice(StockPriceRequest request, Long stockId) {
        Stock stock = this.repository.findById(stockId)
                .orElseThrow(() -> new StockNotFoundException("Stock not found for this id :: " + stockId));
        stock.setCurrentPrice(request.getCurrentPrice());
        return this.repository.save(stock);
    }

    public void deleteStockById(Long stockId) {
        Stock stock = this.repository.findById(stockId)
                .orElseThrow(() -> new StockNotFoundException("Stock not found for this id :: " + stockId));
        this.repository.delete(stock);
    }
}

