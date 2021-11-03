/*
 consulta 1
 */
SELECT E.rut, CONCAT(P.first_names, ' ', P.last_name, ' ', P.second_last_name) AS full_name, MP.start_date
FROM residence.elder E,
     residence.person P,
     residence.medication_prescription MP
WHERE P.rut = E.rut
  AND E.rut = MP.elder_rut
  AND MP.start_date IS NOT NULL
  AND (SELECT extract(YEAR FROM age(CURRENT_DATE, MP.start_date)) * 12 +
              extract(MONTH FROM age(CURRENT_DATE, MP.start_date))) > 1;

/*
 consulta 2
 */
SELECT DISTINCT ON (Ch.checkup_date) checkup_date,
                                     E.rut,
                                     CONCAT(P.first_names, ' ', P.last_name, ' ', P.second_last_name) AS full_name,
                                     Ch.bmi
FROM residence.elder E,
     residence.person P,
     residence.routine_checkup Ch
WHERE P.rut = E.rut
    AND E.rut = Ch.elder_rut
    AND Ch.bmi <= 18.4
   OR Ch.bmi >= 25.0
ORDER BY checkup_date DESC;

/*
 consulta 3
 */
SELECT E.rut,
       CONCAT(Pe.first_names, '', Pe.last_name, '', Pe.second_last_name) as full_name,
       MP.prescription_date,
       MP.start_date
FROM residence.person Pe,
     residence.elder E,
     residence.medication_prescription MP
WHERE Pe.rut = E.rut
  AND E.rut = Mp.elder_rut
  AND MP.medication_name = '**PONER VALOR ACÁ**';

/*
 consulta 4
 */

SELECT M.blood_type, count(*)
FROM residence.medical_record M
GROUP BY M.blood_type
ORDER BY M.blood_type;

SELECT e.rut, CONCAT(first_names, ' ', last_name, ' ', second_last_name) as full_name, medical_record.blood_type
from residence.person as e,
     residence.elder,
     residence.medical_record
where e.rut = elder.rut
  and elder.rut = medical_record.elder_rut
group by (e.rut, full_name, blood_type)
having blood_type = 1;

/*
 consulta 5
 */
SELECT E.rut,
       CONCAT(P.first_names, ' ', P.last_name, ' ', P.second_last_name) AS full_name,
       (SELECT string_agg(Md.medication_name, ', ' ORDER BY medication_name)
        FROM residence.person P,
             residence.elder E,
             residence.medication_prescription Md
        WHERE P.rut = E.rut
          AND E.rut = Md.elder_rut)                                     AS medication_list
FROM residence.person P,
     residence.elder E,
     residence.disease D,
     residence.elder_suffers_disease Esd
WHERE P.rut = E.rut
  AND E.rut = Esd.elder_rut
  AND D.is_chronic = TRUE
GROUP BY E.rut, full_name, medication_list
HAVING count(E.rut) >= 2;

/*
 consulta 6
 */

/*
 consulta 7
 */

/*
 consulta 8
 */
SELECT E.rut,
       CONCAT(P.first_names, ' ', P.last_name, ' ', P.second_last_name) AS full_name,
       I.intervention_date,
       I.severity,
       I.description
FROM residence.person P,
     residence.elder E,
     residence.surgical_intervention I
WHERE P.rut = E.rut
  AND E.rut = I.elder_rut
  AND I.hospital LIKE '%Clínica%';


/*
 consulta 9
 */




