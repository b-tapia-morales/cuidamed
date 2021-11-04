package com.bairontapia.projects.cuidamed.mappings.disesetype;

import com.bairontapia.projects.cuidamed.mappings.allergytype.AllergyType;
import lombok.Getter;

@Getter
public enum DiseaseType {
  ONCOLOGICAL("oncologica"),
  INFECTIOUS("infecciosa"),
  BLOOD("sangre"),
  INMUNE("inmune"),
  ENDOCRINE("endocrina"),
  MENTAL("mentales"),
  NERVOUSSYSTEM("sistema nervioso"),
  OPHTHALMOLOGICAL("oftalmologicas"),
  AUDITORY("auditivas"),
  CARDIOVASCULAR("cardiovascular"),
  RESPIRATORY("respiratoria"),
  DIGESTIVE("digestivo"),
  SKIN("piel"),
  SYSTEMGENOTPURINAARY("sistema genitourinario"),
  CONGENITAL(" congénitas y alteraciones cromosómicas");


  private static final DiseaseType[] VALUES = values();

  private final String name;

  DiseaseType(String name) {
    this.name = name;
  }

  public static DiseaseType[] getValues() {
    return VALUES;
  }

  public static DiseaseType getValueFromIndex(final int index) {
    if (index < 1 || index > VALUES.length) {
      throw new IllegalArgumentException();
    }
    return VALUES[index - 1];
  }

  @Override
  public String toString(){
    return name;
  }

  public int getIndex(){
    return ordinal() + 1;
  }

}
