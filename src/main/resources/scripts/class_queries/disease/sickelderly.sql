SELECT P.rut,
       CONCAT(P.first_names, ' ', P.last_name, ' ',
              P.second_last_name) AS full_name,
       SE.disease_name,
       SE.diagnosis_date
FROM residence.person P, residence.sick_elderly SE
WHERE P.rut = SE.rut;