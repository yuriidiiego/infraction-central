package br.com.grupodagostini.infractioncentral.config.validation;

import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LicensePlateValidator
  implements ConstraintValidator<LicensePlate, String> {

  private static final Pattern PATTERN = Pattern.compile(
    "^[A-Z]{3}\\d[A-Z0-9]\\d{2}$"
  );

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    return PATTERN.matcher(value).matches();
  }
}
