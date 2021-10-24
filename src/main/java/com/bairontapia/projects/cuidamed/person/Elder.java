package com.bairontapia.projects.cuidamed.person;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(schema = "residence", name = "elder")
@PrimaryKeyJoinColumn(name = "rut")
public class Elder extends Person {
  @Column(name = "is_active", nullable = false)
  private Boolean isActive;

  @Column(name = "admission_date", nullable = false)
  private LocalDate admissionDate;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(
      name = "responsible_rut",
      referencedColumnName = "rut",
      updatable = false,
      nullable = false)
  private Responsible responsible;

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  public LocalDate getAdmissionDate() {
    return admissionDate;
  }

  public void setAdmissionDate(LocalDate admissionDate) {
    this.admissionDate = admissionDate;
  }

  public Responsible getResponsible() {
    return responsible;
  }

  public void setResponsible(Responsible responsible) {
    this.responsible = responsible;
  }
}
