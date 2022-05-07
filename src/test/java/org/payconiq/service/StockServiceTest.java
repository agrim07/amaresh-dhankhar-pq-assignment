package org.payconiq.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.payconiq.entity.Stock;
import org.payconiq.exception.StockNotFoundException;
import org.payconiq.repository.StockRepository;
import org.payconiq.request.StockPageResponse;
import org.payconiq.request.StockPriceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StockService.class)
public class StockServiceTest {

    @Autowired
    private StockService stockService;

    @MockBean
    private StockRepository stockRepository;

    @Before
    public void setup() {
        //stockService = new StockService(stockRepository);
    }

    @Test
    public void testFetchAllStocks() {
        Page<Stock> stockPage = new PageImpl<>(new ArrayList<Stock>());
        when(stockRepository.findAll((Pageable) Matchers.any())).thenReturn(stockPage);
        StockPageResponse response = stockService.fetchAllStocks(1);
        assertTrue(response.getStocks().isEmpty());
    }

    @Test()
    public void testFetchStockById() {
        Page<Stock> stockPage = new PageImpl<>(new ArrayList<Stock>());
        when(stockRepository.findById((Long) Matchers.any())).thenReturn(Optional.of(new Stock(1L, "NBC", new BigDecimal(99), String.valueOf(System.currentTimeMillis()))));
        Stock stock = stockService.fetchStockById(1L);
        assertEquals("NBC", stock.getName());
    }

    @Test(expected = StockNotFoundException.class)
    public void testFetchStockByIdThrowException() {
        Page<Stock> stockPage = new PageImpl<>(new ArrayList<Stock>());
        when(stockRepository.findById((Long) Matchers.any())).thenReturn(Optional.empty());
        Stock stock = stockService.fetchStockById(1L);
    }

    @Test()
    public void testCreateStock() {
        when(stockRepository.save((Stock) Matchers.any())).thenReturn(new Stock(1L, "NBC", new BigDecimal(45), String.valueOf(System.currentTimeMillis())));
        Stock stock = stockService.createStock((Stock) Matchers.any());
        assertEquals(new BigDecimal(45), stock.getCurrentPrice());
    }

    @Test()
    public void testUpdateStockPrice() {
        when(stockRepository.findById((Long) Matchers.any())).thenReturn(Optional.of(new Stock(1L, "NBC", new BigDecimal(99), String.valueOf(System.currentTimeMillis()))));
        when(stockRepository.save((Stock) Matchers.any())).thenReturn(new Stock(1L, "NBC", new BigDecimal(45), String.valueOf(System.currentTimeMillis())));
        Stock stock = stockService.updateStockPrice(new StockPriceRequest(1L, new BigDecimal(45)), 1L);
        assertEquals(new BigDecimal(45), stock.getCurrentPrice());
    }

    @Test(expected = StockNotFoundException.class)
    public void testUpdateStockPriceThrowException() {
        Page<Stock> stockPage = new PageImpl<>(new ArrayList<Stock>());
        when(stockRepository.findById((Long) Matchers.any())).thenReturn(Optional.empty());
        Stock stock = stockService.fetchStockById(1L);
    }
}
