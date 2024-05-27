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
@Constraint(validatedBy = VsCurrencyValidator.class)
public @interface VsCurrencyCheck {
  
  public String message()

  default "Invalid Currency id. Please use a valid currency id to try again.";

  public Class<?>[] groups() default {};

  public Class<? extends Payload>[] payload() default {};  

}
