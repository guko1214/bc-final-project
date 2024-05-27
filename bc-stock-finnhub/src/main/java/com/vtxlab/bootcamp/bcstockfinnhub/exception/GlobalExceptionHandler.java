package com.vtxlab.bootcamp.bcstockfinnhub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;
import com.vtxlab.bootcamp.bcstockfinnhub.infra.ApiResponse;
import com.vtxlab.bootcamp.bcstockfinnhub.infra.Syscode;
//import com.vtxlab.bootcamp.bccryptocoingecko.model.dto.jph.Post;

// 1. Create Bean (@Controller, @Service, @Configuration, etc), put into Context
// 2. Autowired from spring Context -> object -> use its instance method

@RestControllerAdvice // Bean
public class GlobalExceptionHandler {
  
  @ExceptionHandler(RestClientException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResponse<Void>  restclientExceptionalHandler() {
    return ApiResponse.<Void>builder()
      .status(Syscode.REST_CLIENT_EXCEPTION) //
      .data(null) //
      .build();
  }

  @ExceptionHandler(NullPointerException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResponse<Void>  npeExceptionalHandler() {
    return ApiResponse.<Void>builder()
      .status(Syscode.NULL_POINTER_EXCEPTION) //
      .data(null) //
      .build();
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResponse<String>  ExceptionalHandler(Exception e) {
    return ApiResponse.<String>builder() 
      .status(Syscode.GENERAL_EXCEPTION) //
      //.data(e.getMessage()) //return the message of current exception, instead of no message
      .data(e.toString())
      .build();
  }

  // @ExceptionHandler(RuntimeException.class)
  // @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  // public ApiResponse2<Void>  runtimeExceptionalHandler(Exception e) {
  //   return ApiResponse2.<Void>builder()
  //     .status(Syscode.RUNTIME_EXCEPTION) //
  //     .data(null) //
  //     .build();
  // }

}
