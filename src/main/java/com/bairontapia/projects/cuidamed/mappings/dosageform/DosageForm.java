package com.bairontapia.projects.cuidamed.mappings.dosageform;

public enum DosageForm {
  BARS("Barras"),
  CAPSULES("Cápsulas"),
  TABLETS("Comprimidos"),
  CHEWING_GUMS("Gomas de mascar"),
  GRANULATED("Granulados"),
  PATCHES("Parches"),
  POWDERS("Polvos"),
  DROPS("Gotas"),
  SYRUP("Jarabe"),
  SUPPOSITORIES("Supositorios"),
  ENEMAS("Enemas"),
  OVULES("Óvulos"),
  LOTIONS("Lociones"),
  SPRAYS("Aerosol"),
  GELS("Gel"),
  OINTMENTS("Ungüentos"),
  SUSPENSIONS("Suspensiones"),
  MOUTHWASH("Colutorio"),
  SYRINGES("Jeringas"),
  DISSOLUTION("Disolución");

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
