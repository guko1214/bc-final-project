package com.vtxlab.bootcamp.bccryptocoingecko.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.vtxlab.bootcamp.bccryptocoingecko.dto.response.CoinsMKDataDTO;
import com.vtxlab.bootcamp.bccryptocoingecko.infra.BcUtil;
import com.vtxlab.bootcamp.bccryptocoingecko.infra.Scheme;
import com.vtxlab.bootcamp.bccryptocoingecko.model.Coins;
import com.vtxlab.bootcamp.bccryptocoingecko.model.CoinsMKData;
import com.vtxlab.bootcamp.bccryptocoingecko.model.VsCurrency;
import com.vtxlab.bootcamp.bccryptocoingecko.service.CoingeckoService;

@Service
public class CoingeckoServiceHolder implements CoingeckoService {
 
  @Value("${api.coinGecko.domain}")
  private String domain;

  @Value("${api.coinGecko.endpoints.coinsList}")
  private String coinsList;

  @Value("${api.coinGecko.endpoints.coinsMarketData}")
  private String coinsMarketData;

  @Value("${api.coinGecko.apiKey}")
  private String apiKey;

  @Value("${api.coinGecko.endpoints.supportedVsCurrencies}")
  private String supportedVsCurrencies;

  @Autowired
  private RestTemplate restTemplate;

  @Override
  public List<CoinsMKData> getCoinsQuote(HashMap<String,String> uris) {
    uris.put("x_cg_demo_api_key",apiKey);
    String url = BcUtil.getUrl(Scheme.HTTPS,domain,coinsMarketData,uris);
    CoinsMKData[] cryptos = restTemplate.getForObject(url, CoinsMKData[].class);
    return Arrays.stream(cryptos).collect(Collectors.toList());
  }

  @Override
  public List<String> getSupportedVsCurrencies() {
    String url = BcUtil.getUrl(Scheme.HTTPS,domain, supportedVsCurrencies);
    VsCurrency[] vsCurrencies = restTemplate.getForObject(url,VsCurrency[].class);
    return Arrays.stream(vsCurrencies)
          .map( e ->  e.getId())
          .collect(Collectors.toList());
  }

  @Override
  public List<String> getConinsList() throws RestClientException {
    String url = BcUtil.getUrl(Scheme.HTTPS,domain, coinsList);
    Coins[] coins = restTemplate.getForObject(url,Coins[].class);
    return Arrays.stream(coins)
          .map( e ->  e.getId())
          .collect(Collectors.toList());
  };


}
