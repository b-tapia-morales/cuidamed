SELECT P.rut,
       CONCAT(P.first_names, ' ', P.last_name, ' ',
              P.second_last_name) AS full_name,
       A.allergy_type,
       A.allergy_name
FROM residence.person P, residence.allergy A
WHERE P.rut = A.rut;