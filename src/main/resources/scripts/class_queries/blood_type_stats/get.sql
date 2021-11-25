SELECT M.blood_type, count(*)
FROM residence.medical_record M
WHERE blood_type = ?
GROUP BY M.blood_type;