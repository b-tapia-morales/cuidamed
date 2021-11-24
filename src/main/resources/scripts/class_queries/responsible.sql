SELECT P.rut,
       CONCAT(P.first_names, ' ', P.last_name, ' ',
              P.second_last_name) AS full_name,
       P.birth_date,
       P.gender,
       R.mobile_phone
FROM residence.responsible R
         NATURAL JOIN residence.person P;