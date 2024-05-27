package com.vtxlab.bootcamp.bcstockfinnhub.dto.mapper;

import com.vtxlab.bootcamp.bcstockfinnhub.model.StockProfile2;
import com.vtxlab.bootcamp.bcstockfinnhub.model.StockQuote;
import com.vtxlab.bootcamp.bcstockfinnhub.dto.response.StockProfile2DTO;
import com.vtxlab.bootcamp.bcstockfinnhub.dto.response.StockQuoteDTO;

public class StockDTOMapper {
  
  public static StockQuoteDTO stockQuoteDTOMapper(StockQuote quote) {
    return StockQuoteDTO.builder()
            .c(quote.getC())
            .d(quote.getD())
            .dp(quote.getDp())
            .h(quote.getH())
            .l(quote.getL())
            .o(quote.getO())
            .pc(quote.getPc())
            .t(quote.getT())
            .build();
  }

  public static StockProfile2DTO stockProfile2DTOMapper(StockProfile2 profile) {
    return StockProfile2DTO.builder()
            .country(profile.getCountry())
            .currency(profile.getCurrency())
            .estimateCurrency(profile.getEstimateCurrency())
            .exchange(profile.getExchange())
            .finnhubIndustry(profile.getFinnhubIndustry())
            .ipo(profile.getIpo())
            .logo(profile.getLogo())
            .marketCapitalization(profile.getMarketCapitalization())
            .name(profile.getName())
            .phone(profile.getPhone())
            .shareOutstanding(profile.getShareOutstanding())
            .ticker(profile.getTicker())
            .weburl(profile.getWeburl())
            .build();
  }

}
