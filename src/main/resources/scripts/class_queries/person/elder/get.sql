SELECT *
FROM residence.person P
         NATURAL JOIN residence.elder E
WHERE rut = ?;