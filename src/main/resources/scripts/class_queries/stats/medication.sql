SELECT medication_name, count(*) as frequency
FROM residence.medication_prescription
GROUP BY medication_name
ORDER BY frequency;