package com.bairontapia.projects.cuidamed.person;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(schema = "residence", name = "carer")
@PrimaryKeyJoinColumn(name = "rut")
@Getter
@Setter
public class Carer extends Person {
  @Column(name = "mobile_phone", unique = true, nullable = false)
  private String mobilePhone;

  @Column(name = "hire_date", nullable = false)
  private LocalDate hireDate;
}
