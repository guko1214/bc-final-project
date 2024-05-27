package com.vtxlab.bootcamp.bcstockfinnhub.service;

import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bcstockfinnhub.dto.response.StockProfile2DTO;
import com.vtxlab.bootcamp.bcstockfinnhub.dto.response.StockQuoteDTO;


public interface RedisService {
  
  StockQuoteDTO createStockQuoteDTO(String key, StockQuoteDTO stockQuoteDTO) throws JsonProcessingException;

  StockProfile2DTO createStockProfile2DTO(String key, StockProfile2DTO stockProfile2DTO) throws JsonProcessingException;

  StockQuoteDTO getStockQuoteDTO(String key) throws JsonProcessingException;

  StockProfile2DTO getStockProfile2DTO(String key) throws JsonProcessingException;

  List<String> getRedisKeys();

}
