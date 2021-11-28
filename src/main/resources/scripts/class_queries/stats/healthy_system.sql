SELECT healthcare_system, count(*)
FROM residence.medical_record
GROUP BY healthcare_system;