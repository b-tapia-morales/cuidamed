package com.bairontapia.projects.cuidamed.medicalrecord.allergy;

import com.bairontapia.projects.cuidamed.mappings.allergytype.AllergyType;
import com.bairontapia.projects.cuidamed.mappings.allergytype.AllergyTypeConverter;
import com.bairontapia.projects.cuidamed.medicalrecord.MedicalRecord;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

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
  private AllergyType type;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "elder_rut", insertable = false, nullable = false, updatable = false)
  @MapsId("rut")
  @Setter(AccessLevel.PROTECTED)
  private MedicalRecord medicalRecord;

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof final Allergy allergy) {
      return Objects.equals(id, allergy.id);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}

