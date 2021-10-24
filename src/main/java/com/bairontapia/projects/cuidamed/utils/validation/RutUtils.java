package com.bairontapia.projects.cuidamed.utils.validation;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

public final class RutUtils {

  public static final String PATTERN_WITH_NO_POINTS;
  public static final String PATTERN_WITH_POINTS;

  static {
    PATTERN_WITH_NO_POINTS = "^[1-9][\\d]{6,7}-?[\\dkK]$";
    PATTERN_WITH_POINTS = "^[1-9][\\d]?(?:\\.[\\d]{3}){2}-[\\dkK]$";
  }

  private RutUtils() {
    throw new UnsupportedOperationException();
  }

  public static boolean isValid(final String rut) {
    return rut.matches(PATTERN_WITH_POINTS) || rut.matches(PATTERN_WITH_NO_POINTS);
  }

  public static boolean isFormatted(final String rut) {
    return (rut.matches(PATTERN_WITH_POINTS));
  }

  public static String format(final String rut) {
    if (!isValid(rut)) {
      throw new IllegalArgumentException();
    }
    if (isFormatted(rut)) {
      return (rut);
    }
    var string = rut;
    string = StringUtils.upperCase(string);
    string = StringUtils.replace(string, "-", "");
    final int n = string.length();
    int count = 0;
    final var builder = new StringBuilder().append(string.charAt(n - 1)).append("-");
    for (int i = (n - 2); i >= 0; i--) {
      count += 1;
      builder.append(string.charAt(i));
      if (count % 3 == 0) {
        count = 0;
        builder.append(".");
      }
    }
    return builder.reverse().toString();
  }

  public static boolean isLastDigitValid(final String rut) {
    if (!isValid(rut)) {
      throw new IllegalArgumentException();
    }
    final var string = RegExUtils.replaceAll(rut, "[.-]", "");
    final int n = string.length();
    int sum = 0;
    int count = 2;
    for (int i = (n - 2); i >= 0; i--) {
      final char digitChar = string.charAt(i);
      final int digit = Character.getNumericValue(digitChar);
      sum += digit * count;
      count += 1;
      if (count == 8) {
        count = 2;
      }
    }
    final char lastDigitChar = string.charAt(n - 1);
    final int digit = 11 - (sum % 11);
    switch (digit) {
      case 11:
        return (lastDigitChar == '0');
      case 10:
        return (lastDigitChar == 'K' || lastDigitChar == 'k');
      default:
        final int lastDigit = Character.getNumericValue(lastDigitChar);
        return (digit == lastDigit);
    }
  }
}
