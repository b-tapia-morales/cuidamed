package com.bairontapia.projects.cuidamed.person;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(schema = "residence", name = "carer")
@PrimaryKeyJoinColumn(name = "rut")
@Getter
@Setter
public class Carer extends Person {
  @Column(name = "mobile_phone", unique = true, nullable = false)
  private Integer mobilePhone;

  @Column(name = "hire_date", nullable = false)
  private LocalDate hireDate;

  @OneToOne(cascade = CascadeType.ALL, mappedBy = "person", fetch = FetchType.LAZY)
  @Setter(AccessLevel.PROTECTED)
  private Address address;
}
