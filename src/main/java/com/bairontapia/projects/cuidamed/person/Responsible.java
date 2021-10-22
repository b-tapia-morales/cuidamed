package com.bairontapia.projects.cuidamed.person;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(schema = "residence", name = "responsible")
@PrimaryKeyJoinColumn(name = "rut")
public class Responsible extends Person {
  @Column(name = "mobile_phone", unique = true, updatable = true, nullable = false)
  private String mobilePhone;

  // conexion con clase Elder
  @OneToOne(mappedBy = "responsible")
  private Elder elder;

  @Override
  public String toString() {
    return "Responsible{" + "mobilePhone='" + mobilePhone + '\'' + ", elder=" + elder + '}';
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof Responsible responsible) {
      return Objects.equals(getRut(), responsible.getRut());
    }
    return false;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public void setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
  }
}
