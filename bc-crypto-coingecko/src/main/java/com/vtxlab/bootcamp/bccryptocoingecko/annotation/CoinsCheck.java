package com.vtxlab.bootcamp.bccryptocoingecko.annotation;

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
@Constraint(validatedBy = CoinsValidator.class)
public @interface CoinsCheck {
  public String message()

  default "Invalid coin id. Please use a valid coin id to try again. ${validatedValue}";

  public Class<?>[] groups() default {};

  public Class<? extends Payload>[] payload() default {};  
}
