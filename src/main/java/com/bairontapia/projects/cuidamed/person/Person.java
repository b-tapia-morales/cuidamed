package com.bairontapia.projects.cuidamed.person;

import com.bairontapia.projects.cuidamed.mappings.gender.Gender;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(schema = "residence", name = "person")
public class Person {
  @Id
  @Column(name = "rut", unique = true, nullable = false)
  private String rut;

  @Column(name = "first_names", nullable = false)
  private String firstNames;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "second_last_name", nullable = false)
  private String secondLastName;

  @Column(name = "birth_date", nullable = false)
  private Date birthDate;

  @Convert(converter = Gender.class)
  private Gender gender;

  @Override
  public String toString() {
    return "Person{"
        + "rut='"
        + rut
        + '\''
        + ", firstNames='"
        + firstNames
        + '\''
        + ", lastName='"
        + lastName
        + '\''
        + ", secondLastName='"
        + secondLastName
        + '\''
        + ", birthDate="
        + birthDate;
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

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(final Gender gender) {
    this.gender = gender;
  }
}
