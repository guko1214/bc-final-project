package com.vtxlab.bootcamp.bcstockfinnhub.service;

import java.util.HashMap;
import java.util.List;
import com.vtxlab.bootcamp.bcstockfinnhub.model.StockProfile2;
import com.vtxlab.bootcamp.bcstockfinnhub.model.StockQuote;
import com.vtxlab.bootcamp.bcstockfinnhub.model.StockSymbol;

public interface FinnhubService {
  
  StockQuote getStockQuote(HashMap<String, String> parms);

  StockProfile2 getStockProfile2(HashMap<String, String> parms);

  List<StockSymbol> getStockSymbol();

  List<String> getStockSymbolString();  

}
