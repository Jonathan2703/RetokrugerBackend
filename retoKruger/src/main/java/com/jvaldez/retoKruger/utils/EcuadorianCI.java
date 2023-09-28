package com.jvaldez.retoKruger.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidationEcuadorianCI.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface  EcuadorianCI {
    String message() default "Numero de cedula incorrecto";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
