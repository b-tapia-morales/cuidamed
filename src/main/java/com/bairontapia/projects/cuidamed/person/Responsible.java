package com.bairontapia.projects.cuidamed.person;

import javax.persistence.*;

@Entity
@Table(schema = "residence", name = "responsible")
@PrimaryKeyJoinColumn(name = "rut")
public class Responsible extends Person {
  @Column(name = "mobile_phone", unique = true, nullable = false)
  private String mobilePhone;

  @OneToOne(mappedBy = "responsible")
  private Elder elder;

  public String getMobilePhone() {
    return mobilePhone;
  }

  public void setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
  }
}
