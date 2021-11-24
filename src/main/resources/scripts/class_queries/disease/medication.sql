SELECT P.rut,
       CONCAT(P.first_names, ' ', P.last_name, ' ', P.second_last_name) AS full_name,
       MP.disease_name,
       string_agg(medication_name, ', ' ORDER BY medication_name)       AS medications,
       MP.prescription_date,
       MP.start_date,
       MP.end_date
FROM residence.person P
         RIGHT OUTER JOIN residence.medication_prescription MP ON P.rut = MP.rut
GROUP BY P.rut, MP.disease_name, MP.prescription_date, MP.start_date,
         MP.end_date;