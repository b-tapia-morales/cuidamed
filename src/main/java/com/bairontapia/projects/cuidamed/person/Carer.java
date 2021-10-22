package com.bairontapia.projects.cuidamed.person;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(schema = "residence", name = "carer")
@PrimaryKeyJoinColumn(name = "rut")
public class Carer extends Person {
  @Column(name = "mobile_phone", unique = true, updatable = true, nullable = false)
  private String mobilePhone;

  @Column(name = "hire_date", unique = false, updatable = true, nullable = false)
  private Date hireDate;

  @Override
  public String toString() {
    return "Carer{" + "mobilePhone='" + mobilePhone + '\'' + ", hireDate=" + hireDate + '}';
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof Carer carer) {
      return Objects.equals(getRut(), carer.getRut());
    }
    return false;
  }

  public Date getHireDate() {
    return hireDate;
  }

  public void setHireDate(Date hireDate) {
    this.hireDate = hireDate;
  }
}
