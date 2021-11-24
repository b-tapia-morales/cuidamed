SELECT P.rut,
       CONCAT(P.first_names, ' ', P.last_name, ' ',
              P.second_last_name) AS full_name,
       P.birth_date,
       P.gender,
       C.mobile_phone,
       C.hire_date
FROM residence.carer C
         NATURAL JOIN residence.person P;