SELECT P.rut,
       CONCAT(P.first_names, ' ', P.last_name, ' ',
              P.second_last_name) AS full_name,
       P.birth_date,
       P.gender,
       E.is_active,
       E.admission_date,
       E.responsible_rut
FROM residence.elder E
         NATURAL JOIN residence.person P;