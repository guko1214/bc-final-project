package com.vtxlab.bootcamp.bcstockfinnhub.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bcstockfinnhub.controller.FinnhubOperation;
import com.vtxlab.bootcamp.bcstockfinnhub.dto.mapper.StockDTOMapper;
import com.vtxlab.bootcamp.bcstockfinnhub.dto.response.StockProfile2DTO;
import com.vtxlab.bootcamp.bcstockfinnhub.dto.response.StockQuoteDTO;
import com.vtxlab.bootcamp.bcstockfinnhub.infra.ApiResponse;
import com.vtxlab.bootcamp.bcstockfinnhub.infra.Syscode;
import com.vtxlab.bootcamp.bcstockfinnhub.model.StockSymbol;
import com.vtxlab.bootcamp.bcstockfinnhub.service.FinnhubService;
import com.vtxlab.bootcamp.bcstockfinnhub.service.RedisService;

@RestController
@RequestMapping(value = "/stock/finnhub/api/v1")
public class FinnhubController implements FinnhubOperation {
  
  @Autowired
  FinnhubService finnhubService;

  @Autowired
  RedisService redisService;

  @Override
  public ApiResponse<List<StockQuoteDTO>> getStockQuotes(String symbol) throws JsonProcessingException {
    HashMap<String, String> parm = new HashMap<>();
    parm.put("symbol",symbol);
    try {
      StockQuoteDTO quote = StockDTOMapper.stockQuoteDTOMapper(finnhubService.getStockQuote(parm));
      List<StockQuoteDTO> quotes = List.of(quote);
      redisService.createStockQuoteDTO(symbol, quote);
      return ApiResponse.<List<StockQuoteDTO>>builder()
              .status(Syscode.OK)
              .data(quotes)
              .build();
    } catch (RestClientException e) {      
      List<StockQuoteDTO> quotes = List.of(redisService.getStockQuoteDTO(symbol));   
      return ApiResponse.<List<StockQuoteDTO>>builder()
              .status(Syscode.OK)
              .data(quotes)
              .build();
    }
  }

  @Override
  public ApiResponse<List<StockProfile2DTO>> getStockProfile2(String symbol) throws JsonProcessingException {
    HashMap<String, String> parm = new HashMap<>();
    parm.put("symbol",symbol);
    try {
      StockProfile2DTO profile2 = StockDTOMapper.stockProfile2DTOMapper(finnhubService.getStockProfile2(parm));
      List<StockProfile2DTO> profile2s = List.of(profile2);
      return ApiResponse.<List<StockProfile2DTO>>builder()
              .status(Syscode.OK)
              .data(profile2s)
              .build();
    } catch (RestClientException e) {
      List<StockProfile2DTO> profile2s = List.of(redisService.getStockProfile2DTO(symbol));    
      return ApiResponse.<List<StockProfile2DTO>>builder()
              .status(Syscode.OK)
              .data(profile2s)
              .build();      
    }

  }

  @Override
  public ApiResponse<List<StockSymbol>> getStockSymbol(String symbol) {
    List<StockSymbol> symbols = finnhubService.getStockSymbol();
    if (symbol == null) {
      return ApiResponse.<List<StockSymbol>>builder()
              .status(Syscode.OK)
              .data(symbols)
              .build();
    }
    List<String> symbolStrings = symbols.stream()
                  .map(e -> e.getSymbol())
                  .collect(Collectors.toList());
    if (symbolStrings.contains(symbol))
      return ApiResponse.<List<StockSymbol>>builder()
              .status(Syscode.OK)
              .data(List.of(symbols.get(symbolStrings.indexOf(symbol))))
              .build();
    throw new NullPointerException();
  };

}
