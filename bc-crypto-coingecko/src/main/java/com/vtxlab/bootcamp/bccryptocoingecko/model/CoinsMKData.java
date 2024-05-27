package com.vtxlab.bootcamp.bccryptocoingecko.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CoinsMKData {
  
  private String id;
  private String symbol;
  private String name;
  private String image;
  private Double current_price; 
  private Double market_cap;
  private Integer market_cap_rank;
  private Double fully_diluted_valuation;
  private Double total_volume;
  private Double high_24h;
  private Double low_24h;
  private Double price_change_24h;
  private Double price_change_percentage_24h;
  private Double market_cap_change_24h;
  private Double market_cap_change_percentage_24h;
  private Double circulating_supply;
  private Double total_supply;
  private Double max_supply;
  private Double ath;
  private Double ath_change_percentage;
  private String ath_date;
  private Double atl; 
  private Double atl_change_percentage;
  private String atl_date;
  private Roi roi; 
  private String last_updated;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  @ToString
  public static class Roi {
    private Double times;
    private String currency;
    private Double percentage;
  }



}
