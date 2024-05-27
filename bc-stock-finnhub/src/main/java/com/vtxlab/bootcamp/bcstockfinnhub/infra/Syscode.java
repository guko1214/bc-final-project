package com.vtxlab.bootcamp.bcstockfinnhub.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Syscode {
  OK("000000","OK"),
  REST_CLIENT_EXCEPTION("900000","RestClientException - finnhub Service is unavailable"),
  NOTFOUND("000001","Resource Not Found."),
  NULL_POINTER_EXCEPTION("900002","NPE."),
  RUNTIME_EXCEPTION("900003","RuntimeException"),
  GENERAL_EXCEPTION("999999","General Other Exception."), //
  ;

  private String code;
  private String message;

}