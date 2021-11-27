SELECT *
FROM residence.person P
         NATURAL JOIN residence.carer C
WHERE rut = ?;