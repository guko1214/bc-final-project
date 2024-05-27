package com.vtxlab.bootcamp.bccryptocoingecko.infra;

public enum Scheme {
  HTTPS, HTTP,;

  public String toLowercase() {

    return this.toString().toLowerCase();
  }
}
