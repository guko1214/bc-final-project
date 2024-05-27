package com.vtxlab.bootcamp.bccryptocoingecko.service;

import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bccryptocoingecko.dto.response.CoinsMKDataDTO;
import com.vtxlab.bootcamp.bccryptocoingecko.model.VsCurrency;

public interface RedisService {
  
  CoinsMKDataDTO createCoinsMkData(String key, CoinsMKDataDTO coinsMKDatas) throws JsonProcessingException;

  List<CoinsMKDataDTO> bulkCreateCoinsMKData(List<CoinsMKDataDTO> coinsMKDataDTOs, VsCurrency currency) throws JsonProcessingException;

  CoinsMKDataDTO getCoinsMkData(String key) throws JsonProcessingException;

  List<CoinsMKDataDTO> bulkGetCoinsMKDataDTOs(VsCurrency currency, String ids) throws JsonProcessingException;

  List<String> getRedisKeys();

}
