SELECT healthcare_system, count(*) AS frequency
FROM residence.medical_record
GROUP BY healthcare_system
ORDER BY frequency DESC;