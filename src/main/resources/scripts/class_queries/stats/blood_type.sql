SELECT blood_type, count(*)
FROM residence.medical_record
GROUP BY blood_type
ORDER BY blood_type;