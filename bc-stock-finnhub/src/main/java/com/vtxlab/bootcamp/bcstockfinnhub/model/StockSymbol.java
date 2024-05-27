package com.vtxlab.bootcamp.bcstockfinnhub.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class StockSymbol {
  
  private String currency;
  private String description;
  private String displaySymbol;
  private String figi;
  private String isin;
  private String mic;
  private String shareClassFIGI;
  private String symbol;
  private String symbol2;
  private String type;

}
