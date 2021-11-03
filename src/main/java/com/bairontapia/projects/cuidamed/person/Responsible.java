package com.bairontapia.projects.cuidamed.person;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "residence", name = "responsible")
@PrimaryKeyJoinColumn(name = "rut")
@Getter
@Setter
public class Responsible extends Person {

  @Column(name = "mobile_phone", unique = true, nullable = false)
  private Integer mobilePhone;

  @OneToOne(mappedBy = "responsible", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Elder elder;

  @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Setter(AccessLevel.PROTECTED)
  private Address address;
}
