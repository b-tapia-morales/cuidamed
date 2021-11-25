SELECT P.rut,
       CONCAT(P.first_names, ' ', P.last_name, ' ',
              P.second_last_name) AS full_name,
       MP.disease_name,
       MP.prescription_date,
       MP.medication_name,
       MP.start_date,
       MP.end_date,
       Mp.frequency
FROM residence.person P,
     residence.medication_prescription MP
WHERE P.rut = MP.rut;