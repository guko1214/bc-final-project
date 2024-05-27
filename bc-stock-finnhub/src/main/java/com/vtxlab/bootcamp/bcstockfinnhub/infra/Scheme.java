package com.vtxlab.bootcamp.bcstockfinnhub.infra;

public enum Scheme {
  HTTPS, HTTP,;

  public String toLowercase() {

    return this.toString().toLowerCase();
  }
}

