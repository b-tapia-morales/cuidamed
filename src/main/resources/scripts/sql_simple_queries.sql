SELECT P.rut,
       CONCAT(P.first_names, ' ', P.last_name, ' ',
              P.second_last_name) as full_name
FROM residence.person P,
     residence.elder E
WHERE P.rut = E.rut;

SELECT personE.rut,
       CONCAT(personE.first_names, ' ', personE.last_name, ' ',
              personE.second_last_name) as elder_full_name,
       personR.rut,
       CONCAT(personR.first_names, ' ', personR.last_name, ' ',
              personR.second_last_name) as responsible_full_name,
       R.mobile_phone
FROM residence.person personE,
     residence.elder E,
     residence.person personR,
     residence.responsible R
WHERE personE.rut = E.rut
  AND personR.rut = R.rut
  AND E.responsible_rut = R.rut;

SELECT P.rut,
       CONCAT(P.first_names, ' ', P.last_name, ' ',
              P.second_last_name) as full_name,
       A.street,
       A.number
FROM residence.person P,
     residence.carer Ca,
     residence.address A,
     residence.commune Co
WHERE P.rut = Ca.rut
  AND Ca.rut = A.person_rut
  AND A.commune_id = Co.id
  AND Co.commune_name = 'Rancagua';

SELECT RC.elder_rut,
       count(RC.elder_rut) as rc_count
FROM residence.person P,
     residence.routine_checkup RC
WHERE P.rut = RC.elder_rut
GROUP BY RC.elder_rut
ORDER BY rc_count;