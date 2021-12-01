WITH medication_aggregation AS (SELECT DISTINCT MP.rut,
                                                MP.disease_name,
                                                string_agg(medication_name, ', ' ORDER BY medication_name) AS medications,
                                                MP.prescription_date,
                                                MP.start_date,
                                                MP.end_date
                                FROM residence.medication_prescription MP
                                GROUP BY MP.rut, MP.disease_name, MP.prescription_date,
                                         MP.start_date, MP.end_date)
SELECT P.rut,
       CONCAT(P.first_names, ' ', P.last_name, ' ', P.second_last_name) AS full_name,
       M.disease_name,
       M.medications,
       M.prescription_date,
       M.start_date,
       M.end_date
FROM residence.person P
         RIGHT OUTER JOIN medication_aggregation M ON P.rut = M.rut;