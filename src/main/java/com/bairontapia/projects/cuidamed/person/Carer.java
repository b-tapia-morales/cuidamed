package com.bairontapia.projects.cuidamed.person;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@NamedEntityGraph(
    name = "carer-graph",
    attributeNodes = {
        @NamedAttributeNode("address")
    }
)
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

  @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Setter(AccessLevel.PROTECTED)
  private Address address;
}
