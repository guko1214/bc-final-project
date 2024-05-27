package com.vtxlab.bootcamp.bccryptocoingecko.annotation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestClientException;
import com.vtxlab.bootcamp.bccryptocoingecko.model.VsCurrency;
import com.vtxlab.bootcamp.bccryptocoingecko.service.CoingeckoService;
import com.vtxlab.bootcamp.bccryptocoingecko.service.impl.CoingeckoServiceHolder;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class VsCurrencyValidator implements ConstraintValidator<VsCurrencyCheck, VsCurrency> {
  
  // @Autowired
  // List<VsCurrency> supportedVsCurrencies;
  @Autowired
  CoingeckoService coingeckoService;

  @Override
  public boolean isValid(VsCurrency cur, ConstraintValidatorContext context) throws RuntimeException{
    try {
       return coingeckoService.getSupportedVsCurrencies().contains(cur.getId());
      //return supportedVsCurrencies.contains(cur);
      
    } catch (RestClientException e) {
      //System.out.println("end");
      //throw new RuntimeException();
      return true;
      
    }
  }


}
