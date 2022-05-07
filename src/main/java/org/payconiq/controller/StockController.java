package org.payconiq.controller;

import org.payconiq.entity.Stock;
import org.payconiq.request.StockPageResponse;
import org.payconiq.request.StockPriceRequest;
import org.payconiq.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StockController {

    private static final Logger log = LoggerFactory.getLogger(StockController.class);

    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/stocks/page/{pageNo}")
    public ResponseEntity<StockPageResponse> fetchAllStocks(@PathVariable Integer pageNo) {
        log.info("Request Received to fetch all stocks");
        StockPageResponse response = this.stockService.fetchAllStocks(pageNo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/stocks")
    public ResponseEntity<String> createStock(@RequestBody Stock stock) {
        log.info("Request Received to create stock {}", stock);
        this.stockService.createStock(stock);
        return ResponseEntity.ok("Stock Created Successfully.");
    }

    @GetMapping("/stocks/{stockId}")
    public ResponseEntity<Stock> fetchStockById(@PathVariable Long stockId) {
        log.info("Request Received to fetch stock of ID {}", stockId);
        Stock stock = this.stockService.fetchStockById(stockId);
        return new ResponseEntity<>(stock, HttpStatus.OK);
    }

    @PatchMapping("/stocks/{stockId}")
    public ResponseEntity<String> updatePriceofStock(@RequestBody StockPriceRequest request, @PathVariable Long stockId) {
        log.info("Request Received to update the price {} of stock of ID {}", request.getCurrentPrice(), stockId);
        this.stockService.updateStockPrice(request, stockId);
        return ResponseEntity.ok("Stock Price Updated Successfully.");
    }

    @DeleteMapping("/stocks/{stockId}")
    public ResponseEntity<String> deleteStockById(@PathVariable Long stockId) {
        log.info("Request Received to delete stock of ID {}", stockId);
        this.stockService.deleteStockById(stockId);
        return ResponseEntity.ok("Stock Deleted Successfully.");
    }
}
