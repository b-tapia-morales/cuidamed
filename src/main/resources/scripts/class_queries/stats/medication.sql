SELECT medication_name, count(*)
FROM residence.medication_prescription
GROUP BY medication_name;