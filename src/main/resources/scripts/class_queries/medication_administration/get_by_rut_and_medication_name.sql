SELECT *
FROM residence.medication_administration
WHERE elder_rut = ?
  AND medication_name = ?;