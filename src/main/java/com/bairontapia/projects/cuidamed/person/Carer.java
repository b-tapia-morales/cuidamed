package com.bairontapia.projects.cuidamed.person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(schema = "residence", name = "carer")
@PrimaryKeyJoinColumn(name = "rut")
public class Carer extends Person {
  @Column(name = "mobile_phone", unique = true, nullable = false)
  private String mobilePhone;

  @Column(name = "hire_date", nullable = false)
  private LocalDate hireDate;

  public String getMobilePhone() {
    return mobilePhone;
  }

  public void setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
  }

  public LocalDate getHireDate() {
    return hireDate;
  }

  public void setHireDate(LocalDate hireDate) {
    this.hireDate = hireDate;
  }
}
