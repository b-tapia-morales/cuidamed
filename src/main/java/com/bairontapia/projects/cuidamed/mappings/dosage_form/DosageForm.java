package com.bairontapia.projects.cuidamed.mappings.dosage_form;

import com.bairontapia.projects.cuidamed.mappings.dosage_status.DosageStatus;

public enum DosageForm {
  SOLID("Solidos"),
  SEMI_SOLID("Semi solidos"),
  LIQUID("Liquidos"),
  Gaseous("Gaseosos");

  private static final DosageForm[] VALUES = values();

  private final String form;

  DosageForm(final String dForms) {
    this.form = dForms;
  }

  public static DosageForm[] getValues() {
    return VALUES;
  }

  public static DosageForm getValueFromIndex(final int index) {
    if (index < 1 || index > VALUES.length) {
      throw new IllegalArgumentException();
    }
    return VALUES[index - 1];
  }

  @Override
  public String toString() {
    return form;
  }

  public String getForm() {
    return this.form;
  }
}
