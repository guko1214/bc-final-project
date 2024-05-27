package com.vtxlab.bootcamp.bcstockfinnhub.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.vtxlab.bootcamp.bcstockfinnhub.infra.BcUtil;
import com.vtxlab.bootcamp.bcstockfinnhub.infra.Scheme;
import com.vtxlab.bootcamp.bcstockfinnhub.model.StockProfile2;
import com.vtxlab.bootcamp.bcstockfinnhub.model.StockQuote;
import com.vtxlab.bootcamp.bcstockfinnhub.model.StockSymbol;
import com.vtxlab.bootcamp.bcstockfinnhub.service.FinnhubService;

@Service
public class FinnhubServiceHolder implements FinnhubService {

  @Value("${api.finnhub.domain}")
  String domain;

  @Value("${api.finnhub.endpoints.quote}")
  String quote;

  @Value("${api.finnhub.endpoints.profile2}")
  String profile2;

  @Value("${api.finnhub.endpoints.stockSymbol}")
  String stockSymbol;

  @Value("${api.finnhub.apiKey}")
  String apikey;

  @Autowired
  RestTemplate restTemplate;

  @Override
  public StockQuote getStockQuote(HashMap<String, String> parms) {
    parms.put("token",apikey);
    String url = BcUtil.getUrl(Scheme.HTTPS,domain ,quote, parms);
    System.out.println(url);
    return restTemplate.getForObject(url,StockQuote.class);
  }
  
  @Override
  public StockProfile2 getStockProfile2(HashMap<String, String> parms) {
    parms.put("token",apikey);
    String url = BcUtil.getUrl(Scheme.HTTPS,domain ,profile2, parms);
    return restTemplate.getForObject(url,StockProfile2.class);
  }

  @Override
  public List<StockSymbol> getStockSymbol() {
    HashMap<String, String> parms = new HashMap<>();
    parms.put("exchange","US");
    parms.put("token",apikey);
    String url = BcUtil.getUrl(Scheme.HTTPS, domain, stockSymbol,parms);
    StockSymbol[] stocks = restTemplate.getForObject(url, StockSymbol[].class);
    //return Arrays.stream(stocks).map(e -> e.getSymbol())
    return Arrays.stream(stocks).collect(Collectors.toList());
  };

  @Override
  public List<String> getStockSymbolString() {
    List<StockSymbol> stocks = this.getStockSymbol();
    return stocks.stream().map(e -> e.getSymbol()).collect(Collectors.toList());
  };

}
