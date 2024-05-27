package com.vtxlab.bootcamp.bcstockfinnhub.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StockSymbolValidator.class)
public @interface StockSymbolCheck {
  public String message()

  default "Invalid stock symbol. Please use a valid stock symbol to try again. ${validatedValue}";

  public Class<?>[] groups() default {};

  public Class<? extends Payload>[] payload() default {};    
}
