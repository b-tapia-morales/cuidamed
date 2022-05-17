package com.bairontapia.projects.cuidamed.pojo;

import com.bairontapia.projects.cuidamed.disease.Disease;
import lombok.Getter;
import org.bson.types.ObjectId;

public class DiseasePOJO {

  @Getter
  private final ObjectId id;
  @Getter
  private final String name;
  @Getter
  private final String diseaseType;
  @Getter
  private final Boolean isChronic;

  public DiseasePOJO(final Disease disease) {
    this.id = new ObjectId();
    this.name = disease.name();
    this.diseaseType = disease.diseaseType().toString();
    this.isChronic = disease.isChronic();
  }
}
