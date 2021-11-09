package com.bairontapia.projects.cuidamed.person;

import com.bairontapia.projects.cuidamed.mappings.gender.Gender;
import com.bairontapia.projects.cuidamed.mappings.gender.GenderConverter;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(schema = "residence", name = "person")
@Getter
@Setter
public abstract class Person {

  @Id
  @Column(name = "rut", unique = true, nullable = false, updatable = false)
  @Setter(AccessLevel.PROTECTED)
  private String rut;

  @Column(name = "first_names", nullable = false)
  private String firstNames;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "second_last_name", nullable = false)
  private String secondLastName;

  @Column(name = "birth_date", nullable = false)
  private LocalDate birthDate;

  @Column(name = "gender", nullable = false)
  @Convert(converter = GenderConverter.class)
  private Gender gender;

  @Override
  public String toString() {
    return String.format
        ("""
            Rut:\t\t\t\t\t\t\t\t\t%s
            Nombres:\t\t\t\t\t\t\t%s
            Apellido paterno:\t\t\t%s
            Apellido materno:\t\t\t%s
            Fecha de nacimiento:\t%s
            Sexo:\t\t\t\t\t\t\t\t\t%s
            """, rut, firstNames, lastName, secondLastName, birthDate, gender);
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof Person person) {
      return Objects.equals(rut, person.getRut());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(rut);
  }
}
