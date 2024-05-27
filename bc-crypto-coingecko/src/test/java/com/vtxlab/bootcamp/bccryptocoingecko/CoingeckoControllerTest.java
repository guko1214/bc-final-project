package com.vtxlab.bootcamp.bccryptocoingecko;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vtxlab.bootcamp.bccryptocoingecko.controller.impl.CoingeckoController;
import com.vtxlab.bootcamp.bccryptocoingecko.dto.mapper.CoinGeckoMapper;
import com.vtxlab.bootcamp.bccryptocoingecko.dto.response.CoinsMKDataDTO;
import com.vtxlab.bootcamp.bccryptocoingecko.infra.ApiResponse;
import com.vtxlab.bootcamp.bccryptocoingecko.infra.Syscode;
import com.vtxlab.bootcamp.bccryptocoingecko.model.CoinsMKData;
import com.vtxlab.bootcamp.bccryptocoingecko.service.CoingeckoService;
import com.vtxlab.bootcamp.bccryptocoingecko.service.RedisService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@WebMvcTest(CoingeckoController.class)
class CoingeckoControllerTest {
  
  @MockBean
  CoingeckoService coingeckoService;

  @MockBean
  RedisService redisService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testGetCoinsQuote() throws Exception {
    List<CoinsMKData> coins1 =  new ArrayList<>();
    CoinsMKData.Roi roi1 = CoinsMKData.Roi.builder()
    .times(73.15992253164991d).currency("btc").percentage(7315.992253164992).build();
   
    CoinsMKData coin1 = CoinsMKData.builder()
    .id("ethereum")
        .symbol("eth")
        .name("Ethereum")        
        .image("https://assets.coingecko.com/coins/images/279/large/ethereum.png?1696501628")
        .current_price(3185.35d)
       .market_cap(383201160518d)
       .market_cap_rank(2)
       .fully_diluted_valuation(383201160518d)
       .total_volume(17115235653d)
       .high_24h(3191.57d)
       .low_24h(3103.91d)
       .price_change_24h(67.67d)
       .price_change_percentage_24h(2.17044d)
       .market_cap_change_24h(8078886478d)
       .market_cap_change_percentage_24h(2.15367)
       .circulating_supply(120142083.888996)
       .total_supply(120142083.888996)
       //.max_supply(null)
       .ath(4228.93)
       .ath_change_percentage(-24.55971)
       .ath_date("2021-12-01T08:38:24.623Z")
       .atl(0.381455)
       .atl_change_percentage(836253.98542)
       .atl_date("2015-10-20T00:00:00.000Z")
       .roi(roi1)       
       .last_updated("2024-03-02T01:05:31.794Z")
       .build();

       coins1.add(coin1);
      
       ApiResponse<List<CoinsMKDataDTO>> apiResponse = ApiResponse.<List<CoinsMKDataDTO>>builder()
          .status(Syscode.OK)
          .data(CoinGeckoMapper.coinsMKDataDTOMap(coins1))
          .build();

      HashMap<String, String> parm = new HashMap<>();
      parm.put("vs_currency","usd");
      parm.put("ids","ethereum");
      List<String> coinsList = List.of("ethereum");
      List<String> currencyList = List.of("usd");
      Mockito.when(coingeckoService.getCoinsQuote(parm)).thenReturn(coins1);
      Mockito.when(coingeckoService.getSupportedVsCurrencies()).thenReturn(currencyList);
      Mockito.when(coingeckoService.getConinsList()).thenReturn(coinsList);      
      String contentStr = new ObjectMapper().writeValueAsString(apiResponse);

      mockMvc.perform(get("/crypto/coingecko/api/v1/coins?currency=usd&ids=ethereum"))
      .andExpect(content().json(contentStr))
      // .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      // .andExpect(jsonPath("$.id").value("ethereum"))
      // .andExpect(jsonPath("$.symbol").value("eth"))
      // .andExpect(jsonPath("$.name").value("Ethereum"))
      // .andExpect(jsonPath("$.image").value("https://assets.coingecko.com/coins/images/279/large/ethereum.png?1696501628"))
      // .andExpect(jsonPath("$.current_price").value(3185.35d))
      // .andExpect(jsonPath("$.market_cap").value(383201160518d))
      // .andExpect(jsonPath("$.market_cap_rank").value(2))
      // .andExpect(jsonPath("$.fully_diluted_valuation").value(383201160518d))
      // .andExpect(jsonPath("$.total_volume").value(17115235653d))
      // .andExpect(jsonPath("$.high_24h").value(3191.57d))
      // .andExpect(jsonPath("$.low_24h").value(3103.91d))
      // .andExpect(jsonPath("$.price_change_24h").value(67.67d))
      // .andExpect(jsonPath("$.price_change_percentage_24h").value(2.17044d))
      // .andExpect(jsonPath("$.market_cap_change_24h").value(8078886478d))
      // .andExpect(jsonPath("$.market_cap_change_percentage_24h").value(2.15367))
      // .andExpect(jsonPath("$.circulating_supply").value(120142083.888996))
      // .andExpect(jsonPath("$.total_supply").value(120142083.888996))
      // .andExpect(jsonPath("$.max_supply").value(null))
      // .andExpect(jsonPath("$.ath").value(4228.93))
      // .andExpect(jsonPath("$.ath_change_percentage").value(-24.55971))
      // .andExpect(jsonPath("$.ath_date").value("2021-12-01T08:38:24.623Z"))
      // .andExpect(jsonPath("$.atl").value(0.381455))
      // .andExpect(jsonPath("$.atl_change_percentage").value(836253.98542))
      // .andExpect(jsonPath("$.atl_date").value("2015-10-20T00:00:00.000Z"))
      // .andExpect(jsonPath("$.roi").value(roi1))
      // .andExpect(jsonPath("$.last_updated").value("2024-03-02T01:05:31.794Z"))
      .andDo(print());

  }


}
