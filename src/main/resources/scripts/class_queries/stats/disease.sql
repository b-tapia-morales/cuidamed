SELECT disease_name, count(*) as frequency
FROM residence.sick_elderly
GROUP BY disease_name
ORDER BY frequency;