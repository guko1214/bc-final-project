package com.vtxlab.bootcamp.bcstockfinnhub;

import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestClientException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vtxlab.bootcamp.bcstockfinnhub.controller.impl.FinnhubController;
import com.vtxlab.bootcamp.bcstockfinnhub.dto.mapper.StockDTOMapper;
import com.vtxlab.bootcamp.bcstockfinnhub.dto.response.StockProfile2DTO;
import com.vtxlab.bootcamp.bcstockfinnhub.dto.response.StockQuoteDTO;
import com.vtxlab.bootcamp.bcstockfinnhub.infra.ApiResponse;
import com.vtxlab.bootcamp.bcstockfinnhub.infra.Syscode;
import com.vtxlab.bootcamp.bcstockfinnhub.model.StockProfile2;
import com.vtxlab.bootcamp.bcstockfinnhub.model.StockQuote;
import com.vtxlab.bootcamp.bcstockfinnhub.service.FinnhubService;
import com.vtxlab.bootcamp.bcstockfinnhub.service.RedisService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
// import static org.hamcrest.Matchers.*;
// import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;


@WebMvcTest(FinnhubController.class)
public class FinnhubControllerTest {
  
  @MockBean
  FinnhubService finnhubService;

  @MockBean
  RedisService redisService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testGetStockQuote() throws Exception {

    StockQuote quote = StockQuote.builder()
                        .c(179.66d)
                        .d(-1.09d)
                        .dp(-0.603d)
                        .h(180.53d)
                        .l(177.38d)
                        .o(179.68d)
                        .pc(180.75d)
                        .t(1.709326801E9d)
                        .build();

    StockQuoteDTO quoteDTO= StockDTOMapper.stockQuoteDTOMapper(quote);
    List<StockQuoteDTO> quoteDTOs = List.of(quoteDTO);
    ApiResponse<List<StockQuoteDTO>> apiResponse = ApiResponse.<List<StockQuoteDTO>>builder()
                                                    .status(Syscode.OK)
                                                    .data(quoteDTOs)
                                                    .build();
    HashMap<String,String> parms = new HashMap<>();
    parms.put("symbol","AAPL");
    List<String> symboList = List.of("AAPL");
    Mockito.when(finnhubService.getStockQuote(parms)).thenReturn(quote);
    Mockito.when(finnhubService.getStockSymbolString()).thenReturn(symboList);
    String contentStr = new ObjectMapper().writeValueAsString(apiResponse);

    mockMvc.perform(get("/stock/finnhub/api/v1/quote?symbol=AAPL"))
    .andExpect(content().json(contentStr))
    .andDo(print());

    Mockito.when(finnhubService.getStockQuote(parms)).thenThrow(new RestClientException("d"));
    Mockito.when(redisService.getStockQuoteDTO("AAPL")).thenThrow(new RestClientException(""));
    mockMvc.perform(get("/stock/finnhub/api/v1/quote?symbol=AAPL"))
    .andExpect(result -> assertTrue(result.getResolvedException() instanceof RestClientException))
    .andDo(print());    
  }

  @Test
  void testGetStockProfile2() throws Exception {

    StockProfile2 profile2 = StockProfile2.builder()
                              .country("US")
                              .currency("USD")
                              .estimateCurrency("USD")
                              .exchange("NASDAQ NMS - GLOBAL MARKET")
                              .finnhubIndustry("Technology")
                              .ipo("1980-12-12")
                              .logo("https://static2.finnhub.io/file/publicdatany/finnhubimage/stock_logo/AAPL.svg")
                              .marketCapitalization(2774288.315195281d)
                              .name("Apple Inc")
                              .phone("14089961010")
                              .shareOutstanding(15441.88d)
                              .ticker("AAPL")
                              .weburl("https://www.apple.com/")
                              .build();

    StockProfile2DTO profile2DTO= StockDTOMapper.stockProfile2DTOMapper(profile2);
    List<StockProfile2DTO> profile2DTOs = List.of(profile2DTO);
    ApiResponse<List<StockProfile2DTO>> apiResponse = ApiResponse.<List<StockProfile2DTO>>builder()
                                                    .status(Syscode.OK)
                                                    .data(profile2DTOs)
                                                    .build();
    HashMap<String,String> parms = new HashMap<>();
    parms.put("symbol","AAPL");
    List<String> symboList = List.of("AAPL");
    Mockito.when(finnhubService.getStockSymbolString()).thenReturn(symboList);    
    Mockito.when(finnhubService.getStockProfile2(parms)).thenReturn(profile2);

    String contentStr = new ObjectMapper().writeValueAsString(apiResponse);

    mockMvc.perform(get("/stock/finnhub/api/v1/profile2?symbol=AAPL"))
    .andExpect(content().json(contentStr))
    .andDo(print());

    Mockito.when(finnhubService.getStockProfile2(parms)).thenThrow(new RestClientException("d"));
    Mockito.when(redisService.getStockProfile2DTO("AAPL")).thenThrow(new RestClientException(""));
    mockMvc.perform(get("/stock/finnhub/api/v1/profile2?symbol=AAPL"))
    .andExpect(result -> assertTrue(result.getResolvedException() instanceof RestClientException))
    .andDo(print());
  }

}
