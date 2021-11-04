package com.bairontapia.projects.cuidamed.medicalrecord.allergy;

import com.bairontapia.projects.cuidamed.mappings.allergytype.AllergyType;
import com.bairontapia.projects.cuidamed.mappings.allergytype.AllergyTypeConverter;
import com.bairontapia.projects.cuidamed.medicalrecord.MedicalRecord;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(schema = "residence", name = "allergy")
@Getter
@Setter
public class Allergy {
  @EmbeddedId
  @Setter(AccessLevel.PROTECTED)
  private AllergyId id;

  @Column(name = "allergy_type", nullable = false)
  @Convert(converter = AllergyTypeConverter.class)
  private AllergyType allergyType;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "elder_rut", insertable = false, nullable = false, updatable = false)
  @Setter(AccessLevel.PROTECTED)
  private MedicalRecord medicalRecord;

  @Override
  public boolean equals(final Object object) {
    if (this == object) return true;
    if (object instanceof final Allergy allergy){
      return Objects.equals(id, allergy.id);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}

