package br.com.project.infractioncentral.config.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = LicensePlateValidator.class)
@Documented
public @interface LicensePlate {
  String message() default "Invalid license plate number. Use the following format: ABC1D23.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
