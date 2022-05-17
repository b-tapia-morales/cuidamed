SELECT *
FROM residence.medication_prescription
WHERE rut = ?
  AND disease_name = ?;