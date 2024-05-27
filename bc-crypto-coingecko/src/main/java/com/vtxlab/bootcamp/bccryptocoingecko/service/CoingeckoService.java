package com.vtxlab.bootcamp.bccryptocoingecko.service;

import java.util.HashMap;
import java.util.List;
import com.vtxlab.bootcamp.bccryptocoingecko.dto.response.CoinsMKDataDTO;
import com.vtxlab.bootcamp.bccryptocoingecko.model.CoinsMKData;
import com.vtxlab.bootcamp.bccryptocoingecko.model.VsCurrency;

public interface CoingeckoService {
  
  List<CoinsMKData> getCoinsQuote(HashMap<String,String> uris);

  List<String> getSupportedVsCurrencies();

  List<String> getConinsList();

}
