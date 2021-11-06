package com.bairontapia.projects.cuidamed.person;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Carer, String> {

  @Query(value =
      "SELECT P.rut, P.first_names, P.last_name, P.second_last_name, P.birth_date, P.gender, "
          + "C.hire_date, C.mobile_phone FROM residence.person P "
          + "INNER JOIN residence.carer C ON P.rut = C.rut", nativeQuery = true)
  Iterable<Carer> findAllNative();

}
