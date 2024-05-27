package com.vtxlab.bootcamp.bccryptocoingecko.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vtxlab.bootcamp.bccryptocoingecko.infra.RedisHelper;
import com.vtxlab.bootcamp.bccryptocoingecko.model.VsCurrency;
import com.vtxlab.bootcamp.bccryptocoingecko.service.CoingeckoService;
import com.vtxlab.bootcamp.bccryptocoingecko.service.RedisService;
import com.vtxlab.bootcamp.bccryptocoingecko.service.impl.CoingeckoServiceHolder;

@Configuration
public class AppConfig {

  // @Autowired
  // private CoingeckoService coingeckoService;

  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }

  // @Bean
  // List<String> supportedVsCurrencies() {
  //   CoingeckoService coingeckoService = new CoingeckoServiceHolder();
  //   return coingeckoService.getSupportedVsCurrencies();
  // }

  @Bean
  RedisHelper redisHelper(RedisConnectionFactory factory, ObjectMapper objectMapper) {
    return new RedisHelper(factory, objectMapper);
  }

  @Bean
  ObjectMapper objectMapper() {
    return new ObjectMapper();
  };

}
