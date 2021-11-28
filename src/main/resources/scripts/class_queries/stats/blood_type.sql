SELECT blood_type, count(*) as frequency
FROM residence.medical_record
GROUP BY blood_type
ORDER BY frequency DESC ;