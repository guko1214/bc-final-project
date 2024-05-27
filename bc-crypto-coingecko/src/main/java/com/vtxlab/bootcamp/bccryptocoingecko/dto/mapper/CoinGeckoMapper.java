package com.vtxlab.bootcamp.bccryptocoingecko.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;
import com.vtxlab.bootcamp.bccryptocoingecko.dto.response.CoinsMKDataDTO;
import com.vtxlab.bootcamp.bccryptocoingecko.model.CoinsMKData;

public class CoinGeckoMapper {
  
  public static List<CoinsMKDataDTO> coinsMKDataDTOMap(List<CoinsMKData> c) {

    List<CoinsMKDataDTO> coinsMKDataDTOs = c.stream()
      .map(e -> {
        
        CoinsMKData.Roi roi = e.getRoi();
        CoinsMKDataDTO.Roi roiDTO = null;
        if (roi != null) {
          roiDTO = CoinsMKDataDTO.Roi.builder()
                              .times(roi.getTimes())
                              .currency(roi.getCurrency())
                              .percentage(roi.getPercentage())
                              .build();
        }

        return CoinsMKDataDTO.builder()
        .id(e.getId())
        .symbol(e.getSymbol())
        .name(e.getName())
        .image(e.getImage())
        .currentPrice(e.getCurrent_price())
        .marketCap(e.getMarket_cap())
        .marketCapRank(e.getMarket_cap_rank())
        .fullyDilutedValuation(e.getFully_diluted_valuation())
        .totalVolume(e.getTotal_volume())
        .high24h(e.getHigh_24h())
        .low24h(e.getLow_24h())
        .priceChange24h(e.getPrice_change_24h())
        .priceChangePercentage24h(e.getPrice_change_percentage_24h())
        .marketCapChange24h(e.getMarket_cap_change_24h())
        .marketCapChangePercentage24h(e.getMarket_cap_change_percentage_24h())
        .circulatingSupply(e.getCirculating_supply())
        .totalSupply(e.getTotal_supply())
        .maxSupply(e.getMax_supply())
        .ath(e.getAth())
        .athChangePercentage(e.getAth_change_percentage())
        .athDate(e.getAth_date())
        .atl(e.getAtl())
        .atlChangePercentage(e.getAtl_change_percentage())
        .atlDate(e.getAtl_date())
        .roi(roiDTO)
        .lastUpdated(e.getLast_updated())
        .build();
      })
      .collect(Collectors.toList())
      ;
      return coinsMKDataDTOs;
  }

}
