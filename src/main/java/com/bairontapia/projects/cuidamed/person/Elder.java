package com.bairontapia.projects.cuidamed.person;

import com.bairontapia.projects.cuidamed.medicalrecord.MedicalRecord;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "residence", name = "elder")
@PrimaryKeyJoinColumn(name = "rut")
@Getter
@Setter
public class Elder extends Person {

  @Column(name = "is_active", nullable = false)
  private Boolean isActive;

  @Column(name = "admission_date", nullable = false)
  private LocalDate admissionDate;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(
      referencedColumnName = "rut",
      nullable = false,
      updatable = false)
  private Responsible responsible;

  @OneToOne(mappedBy = "elder", cascade = CascadeType.ALL)
  @Setter(AccessLevel.PROTECTED)
  private MedicalRecord medicalRecord;

  @Override
  public String toString() {
    return super.toString() +
        String.format
            ("""
                Activo:\t\t\t\t\t\t\t\t\t%s
                Fecha de admisión:\t\t%s
                """, isActive ? "Sí" : "No", admissionDate);
  }
}
