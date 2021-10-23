package com.bairontapia.projects.cuidamed.person;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(schema = "residence", name = "carer")
@PrimaryKeyJoinColumn(name = "rut")
public class Carer extends Person {
  @Column(name = "mobile_phone", unique = true, nullable = false)
  private String mobilePhone;

  @Temporal(TemporalType.DATE)
  @Column(name = "hire_date", nullable = false)
  private Date hireDate;

  public String getMobilePhone() {
    return mobilePhone;
  }

  public void setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
  }

  public Date getHireDate() {
    return hireDate;
  }

  public void setHireDate(Date hireDate) {
    this.hireDate = hireDate;
  }
}
