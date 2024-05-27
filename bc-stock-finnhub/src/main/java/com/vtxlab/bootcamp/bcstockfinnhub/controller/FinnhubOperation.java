package com.vtxlab.bootcamp.bcstockfinnhub.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bcstockfinnhub.annotation.StockSymbolCheck;
import com.vtxlab.bootcamp.bcstockfinnhub.dto.response.StockProfile2DTO;
import com.vtxlab.bootcamp.bcstockfinnhub.dto.response.StockQuoteDTO;
import com.vtxlab.bootcamp.bcstockfinnhub.infra.ApiResponse;
import com.vtxlab.bootcamp.bcstockfinnhub.model.StockSymbol;

@Validated
public interface FinnhubOperation {
  
  @GetMapping(value = "/quote")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResponse<List<StockQuoteDTO>> getStockQuotes(@StockSymbolCheck @RequestParam(name = "symbol", required=true)  
                                                    String symbol) throws JsonProcessingException;

  @GetMapping(value = "/profile2")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResponse<List<StockProfile2DTO>> getStockProfile2(@StockSymbolCheck @RequestParam(name = "symbol", required=true)
                                                    String symbol) throws JsonProcessingException;

  @GetMapping(value = "/symbol")
  ApiResponse<List<StockSymbol>> getStockSymbol(@RequestParam(name = "symbol", required=false) String symbol);

}
