package com.vtxlab.bootcamp.bccryptocoingecko.controller.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bccryptocoingecko.controller.CoingeckoOperation;
import com.vtxlab.bootcamp.bccryptocoingecko.dto.mapper.CoinGeckoMapper;
import com.vtxlab.bootcamp.bccryptocoingecko.dto.response.CoinsMKDataDTO;
import com.vtxlab.bootcamp.bccryptocoingecko.infra.ApiResponse;
import com.vtxlab.bootcamp.bccryptocoingecko.infra.ParamConverter;
import com.vtxlab.bootcamp.bccryptocoingecko.infra.Syscode;
import com.vtxlab.bootcamp.bccryptocoingecko.model.CoinsMKData;
import com.vtxlab.bootcamp.bccryptocoingecko.model.VsCurrency;
import com.vtxlab.bootcamp.bccryptocoingecko.service.CoingeckoService;
import com.vtxlab.bootcamp.bccryptocoingecko.service.RedisService;

@RestController
@RequestMapping(value = "/crypto/coingecko/api/v1")
public class CoingeckoController implements CoingeckoOperation {
  
  @Autowired
  CoingeckoService coingeckoService;

  @Autowired
  RedisService redisService;
  
  @Override
  public ApiResponse<List<CoinsMKDataDTO>> getCoinsQuote(VsCurrency currency, String ids)  throws JsonProcessingException  {
    HashMap<String, String> parm = new HashMap<>();
    parm.put("vs_currency",currency.getId());
    parm.put("ids",ids);
    try {
      List<CoinsMKData> coinsMKDatas = coingeckoService.getCoinsQuote(parm);
      List<CoinsMKDataDTO> coinsMKDataDTOs = CoinGeckoMapper.coinsMKDataDTOMap(coinsMKDatas);
      redisService.bulkCreateCoinsMKData(coinsMKDataDTOs,currency);
      return ApiResponse.<List<CoinsMKDataDTO>>builder()
      .status(Syscode.OK)
      .data(coinsMKDataDTOs)
      .build();
    } catch (RestClientException e) {
      List<CoinsMKDataDTO> coinsMKDataDTOs = redisService.bulkGetCoinsMKDataDTOs(currency, ids);
      return ApiResponse.<List<CoinsMKDataDTO>>builder()
      .status(Syscode.OK)
      .data(coinsMKDataDTOs)
      .build();
    }
  }

  
}
