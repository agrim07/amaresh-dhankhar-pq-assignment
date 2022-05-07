package org.payconiq.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.payconiq.entity.Stock;
import org.payconiq.request.StockPriceRequest;
import org.payconiq.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StockController.class)
@WebAppConfiguration
@EnableWebMvc
public class StockControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @MockBean
    private StockService stockService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testLoadPageOne() throws Exception {
        String uri = "/api/stocks/page/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void testLoadStocks() throws Exception {
        String uri = "/api/stocks/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void testStockCreation() throws Exception {
        String uri = "/api/stocks";
        Stock stock = new Stock(null, "IBM", new BigDecimal(123), Long.toString(System.currentTimeMillis()));
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .characterEncoding("UTF-8").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(stock))
                .accept(MediaType.ALL_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void testStockPartialUpdate() throws Exception {
        String uri = "/api/stocks/1";
        StockPriceRequest stock = new StockPriceRequest(1L, new BigDecimal(123));
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.patch(uri)
                .characterEncoding("UTF-8").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(stock))
                .accept(MediaType.ALL_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void testDeleteStocks() throws Exception {
        String uri = "/api/stocks/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
