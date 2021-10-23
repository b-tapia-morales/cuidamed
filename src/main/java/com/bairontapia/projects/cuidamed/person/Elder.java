package com.bairontapia.projects.cuidamed.person;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(schema = "residence", name = "elder")
@PrimaryKeyJoinColumn(name = "rut")
public class Elder extends Person {
  @Column(name = "is_active", nullable = false)
  private Boolean isActive;

  @Temporal(TemporalType.DATE)
  @Column(name = "admission_date", nullable = false)
  private Date admissionDate;

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

  public Date getAdmissionDate() {
    return admissionDate;
  }

  public void setAdmissionDate(Date admissionDate) {
    this.admissionDate = admissionDate;
  }

  public Responsible getResponsible() {
    return responsible;
  }

  public void setResponsible(Responsible responsible) {
    this.responsible = responsible;
  }
}
