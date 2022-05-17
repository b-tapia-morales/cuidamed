package com.bairontapia.projects.cuidamed.person;

import com.bairontapia.projects.cuidamed.mappings.gender.Gender;
import java.time.LocalDate;

public interface Person {

  String rut();

  String firstName();

  String lastName();

  String secondLastName();

  LocalDate birthDate();

  Integer age();

  Gender gender();
}
