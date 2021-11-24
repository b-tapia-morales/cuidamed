SELECT P.rut,
       CONCAT(P.first_names, ' ', P.last_name, ' ',
              P.second_last_name) AS full_name,
       SI.intervention_date,
       SI.hospital,
       SI.severity,
       SI.description
FROM residence.person P, residence.surgical_intervention SI
WHERE P.rut = SI.rut;
