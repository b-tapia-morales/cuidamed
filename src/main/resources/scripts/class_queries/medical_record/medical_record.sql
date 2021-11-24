SELECT P.rut, CONCAT(P.first_names, ' ', P.last_name, ' ',
            P.second_last_name) AS full_name,
       MD.blood_type, MD.healthcare_system
FROM residence.medical_record MD, residence.person P
WHERE P.rut = md.rut