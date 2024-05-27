package com.vtxlab.bootcamp.bcstockfinnhub.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bcstockfinnhub.dto.response.StockProfile2DTO;
import com.vtxlab.bootcamp.bcstockfinnhub.dto.response.StockQuoteDTO;
import com.vtxlab.bootcamp.bcstockfinnhub.infra.RedisHelper;
import com.vtxlab.bootcamp.bcstockfinnhub.service.RedisService;

@Service
public class RedisServiceHolder implements RedisService {
  
  @Autowired
  RedisHelper redisHelper;

  public StockQuoteDTO createStockQuoteDTO(String key, StockQuoteDTO stockQuoteDTO) throws JsonProcessingException {
    redisHelper.set("stock:finnhub:quote:" + key,stockQuoteDTO);
    redisHelper.set("stock:finnhub:quote:" + key+ ":updatedDate" , LocalDateTime.now().toString());
    return stockQuoteDTO;
  };

  public StockProfile2DTO createStockProfile2DTO(String key, StockProfile2DTO stockProfile2DTO) throws JsonProcessingException {
    redisHelper.set("stock:finnhub:profile2:" + key,stockProfile2DTO);
    redisHelper.set("stock:finnhub:profile2:" + key+ ":updatedDate" , LocalDateTime.now().toString());    
    return stockProfile2DTO;
  };

  public StockQuoteDTO getStockQuoteDTO(String key) throws JsonProcessingException {
    List<String> idsList = redisHelper.getKeys("stock:finnhub:quote:*");
    if (idsList.contains("stock:finnhub:quote:" + key) && idsList.contains("stock:finnhub:quote:" + key+ ":updatedDate")) {
      if (LocalDateTime.parse(redisHelper.get("stock:finnhub:quote:" + key + ":updatedDate",String.class))
                                          .plusSeconds(61).isAfter(LocalDateTime.now()))
        return redisHelper.get("stock:finnhub:quote:" + key,StockQuoteDTO.class);
    }
    throw new RestClientException("RestClientException - finnhub Service is unavailable");
  };

  public StockProfile2DTO getStockProfile2DTO(String key) throws JsonProcessingException {
    List<String> idsList = redisHelper.getKeys("stock:finnhub:quote:*");
    if (idsList.contains("stock:finnhub:profile2:" + key) && idsList.contains("stock:finnhub:profile2:" + key+ ":updatedDate")) {
      if (LocalDateTime.parse(redisHelper.get("stock:finnhub:profile2:" + key + ":updatedDate",String.class))
                                          .plusSeconds(61).isAfter(LocalDateTime.now()))
      redisHelper.get("stock:finnhub:profile2:" + key,StockProfile2DTO.class);
    }
    throw new RestClientException("RestClientException - finnhub Service is unavailable");
  };

  public List<String> getRedisKeys(){
    return redisHelper.getKeys("stock:finnhub:quote:*").stream()
      .map(e -> {
        return e.replace("stock:finnhub:quote:","");
      }).collect(Collectors.toList());
  }

}
