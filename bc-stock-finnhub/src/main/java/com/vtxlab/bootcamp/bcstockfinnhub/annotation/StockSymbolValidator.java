package com.vtxlab.bootcamp.bcstockfinnhub.annotation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestClientException;
import com.vtxlab.bootcamp.bcstockfinnhub.service.FinnhubService;
import com.vtxlab.bootcamp.bcstockfinnhub.service.impl.FinnhubServiceHolder;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StockSymbolValidator implements ConstraintValidator<StockSymbolCheck, String>{
  
  @Autowired
  FinnhubService finnhubService;

  @Override
  public boolean isValid(String symbol, ConstraintValidatorContext context) {
    try {
      List<String> symbolList = finnhubService.getStockSymbolString();
      if (symbolList.contains(symbol))
        return true;
      return false;
    } catch (RestClientException e) {
      return true;
    }
    
  }


}
