SELECT P.rut,
       CONCAT(P.first_names, ' ', P.last_name, ' ',
              P.second_last_name) AS full_name,
       Pr.disease_name,
       Pr.prescription_date,
       Pr.description
FROM residence.person P,
     residence.prescription Pr
WHERE P.rut = Pr.rut;