SELECT disease_name, count(*)
FROM residence.sick_elderly
GROUP BY disease_name;