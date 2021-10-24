package com.bairontapia.projects.cuidamed.person;

import com.bairontapia.projects.cuidamed.mappings.gender.Gender;
import com.bairontapia.projects.cuidamed.mappings.gender.GenderConverter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(schema = "residence", name = "person")
public abstract class Person {
  @Id
  @Column(name = "rut", unique = true, nullable = false, updatable = false)
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

  public String getRut() {
    return rut;
  }

  public void setRut(String rutID) {
    this.rut = rutID;
  }

  public String getFirstNames() {
    return firstNames;
  }

  public void setFirstNames(String firstNames) {
    this.firstNames = firstNames;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getSecondLastName() {
    return secondLastName;
  }

  public void setSecondLastName(String secondLastName) {
    this.secondLastName = secondLastName;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(final Gender gender) {
    this.gender = gender;
  }
}
