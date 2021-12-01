SELECT *
FROM residence.person
         NATURAL JOIN residence.elder
WHERE responsible_rut = ?;