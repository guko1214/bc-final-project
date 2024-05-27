package com.vtxlab.bootcamp.bcstockfinnhub.infra;

import lombok.Getter;

@Getter
public class ApiResponse<T> {
  private String code;
  private String message;
  private T data;

  public ApiResponse (String code, String message, T data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  public static <T> ApiResponseBuilder<T> builder() {
    return new ApiResponseBuilder();
  }

  public static class ApiResponseBuilder<T> {

    private String code;
    private String message;
    private T data;

    ApiResponseBuilder() {
    }

    public ApiResponseBuilder<T> status(Syscode syscode) {
      this.code = syscode.getCode();
      this.message = syscode.getMessage();
      return this;
    }

    public ApiResponseBuilder<T> data(T data) {
      this.data = data;
      return this;
    }

    public ApiResponse<T> build() {
      return new ApiResponse<T>(this.code, this.message, this.data);
    }

  }
  
}

