package com.vtxlab.bootcamp.bccryptocoingecko.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bccryptocoingecko.dto.response.CoinsMKDataDTO;
import com.vtxlab.bootcamp.bccryptocoingecko.infra.ParamConverter;
import com.vtxlab.bootcamp.bccryptocoingecko.infra.RedisHelper;
import com.vtxlab.bootcamp.bccryptocoingecko.model.VsCurrency;
import com.vtxlab.bootcamp.bccryptocoingecko.service.CoingeckoService;
import com.vtxlab.bootcamp.bccryptocoingecko.service.RedisService;

@Service
public class RedisServiceimpl implements RedisService{
  
  @Autowired
  RedisHelper redisHelper;

  @Autowired
  CoingeckoService coingeckoService;

  public CoinsMKDataDTO createCoinsMkData(String key, CoinsMKDataDTO coinsMKData) throws JsonProcessingException {
    redisHelper.set(key,coinsMKData);
    return coinsMKData;
  };

  public List<CoinsMKDataDTO> bulkCreateCoinsMKData(List<CoinsMKDataDTO> coinsMKDataDTOs, VsCurrency currency) throws JsonProcessingException{
    Iterator<CoinsMKDataDTO> iterator = coinsMKDataDTOs.iterator();
    while (iterator.hasNext()) {
      CoinsMKDataDTO d = iterator.next();
      this.createCoinsMkData("crypto:coingecko:coins-markets:" + currency.getId() + ":" + d.getId() , d);
      redisHelper.set("crypto:coingecko:coins-markets:" + currency.getId() + ":" + d.getId() + ":updatedDate" , LocalDateTime.now().toString());
    }
    return coinsMKDataDTOs;
  }

  public CoinsMKDataDTO getCoinsMkData(String key) throws JsonProcessingException {
    System.out.println("ddd");
    List<String> idsList = redisHelper.getKeys("crypto:coingecko:coins-markets:*");
    if (idsList.contains(key) && idsList.contains(key + ":updatedDate")) {
      if (LocalDateTime.parse(redisHelper.get(key + ":updatedDate",String.class)).plusSeconds(61).isAfter(LocalDateTime.now()))
        return redisHelper.get(key, CoinsMKDataDTO.class);
    }
    throw new RestClientException("RestClientException - Coingecko Service is unavailable");
  };

  public List<CoinsMKDataDTO> bulkGetCoinsMKDataDTOs(VsCurrency currency, String ids) throws JsonProcessingException {
    List<String> idsList = new ArrayList<>();
    if (ids != null) {
      idsList = ParamConverter.csvToList(ids);
      List<CoinsMKDataDTO>  coinsMKDataDTOs = idsList.stream()
      .map(e -> {
        try {
          return this.getCoinsMkData("crypto:coingecko:coins-markets:" + currency.getId() + ":" + e);
        } catch (JsonProcessingException e1) {
          throw new RuntimeException("JsonProcessingException");
        }
      })
      .collect(Collectors.toList());
      return coinsMKDataDTOs;
    } else {
      idsList = redisHelper.getKeys("crypto:coingecko:coins-markets:*");
      List<CoinsMKDataDTO>  coinsMKDataDTOs = idsList.stream()
      .map(e -> {
        try {
          return this.getCoinsMkData(e);
        } catch (JsonProcessingException e1) {
          throw new RuntimeException("JsonProcessingException");
        }
      })
      .collect(Collectors.toList());
      return coinsMKDataDTOs;
    }
  };

  public List<String> getRedisKeys(){
    return redisHelper.getKeys("crypto:coingecko:coins-markets:*").stream()
      .map(e -> {
        return e.replace("crypto:coingecko:coins-markets:","");
      }).collect(Collectors.toList());
  }

}
