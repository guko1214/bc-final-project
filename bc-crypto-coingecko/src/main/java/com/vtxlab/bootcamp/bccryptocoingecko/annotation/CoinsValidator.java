package com.vtxlab.bootcamp.bccryptocoingecko.annotation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestClientException;
import com.vtxlab.bootcamp.bccryptocoingecko.service.CoingeckoService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CoinsValidator implements ConstraintValidator<CoinsCheck, String> {

  @Autowired
  CoingeckoService coingeckoService;

  @Override
  //public boolean isValid(String coins, ConstraintValidatorContext context) throws RuntimeException {
  public boolean isValid(String coins, ConstraintValidatorContext context) {
    if (coins == null)
      return true;
    try {
      List<String> coinsList = coingeckoService.getConinsList();  
      // System.out.println(coinsList); 
      int startIdx = 0;
      StringBuilder coinId = new StringBuilder();
      char[] coinsCharArr = (coins + " ").toCharArray();
      for (int i = 0; i < coinsCharArr.length; i++) {
        if (coinsCharArr[i] == ',' || i == coinsCharArr.length - 1) {
          coinId = new StringBuilder();
          for (int j = startIdx; j < i; j++) {          
            coinId = coinId.append(coinsCharArr[j]);
          }
          if (!(coinsList.contains(coinId.toString()))) {
            return false;
          }
          startIdx = i + 1;        
        }
      }
    } catch (RestClientException e) {
      //throw new RuntimeException();
      return true;
    }
    return true;
  }


}
