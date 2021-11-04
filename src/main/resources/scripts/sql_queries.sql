/*
 Consulta 1
 AÚN POR PROBAR.
 */
SELECT E.rut,
       CONCAT(P.first_names, ' ', P.last_name, ' ', P.second_last_name) AS full_name,
       MP.start_date
FROM residence.elder E,
     residence.person P,
     residence.medication_prescription MP
WHERE P.rut = E.rut
  AND E.rut = MP.elder_rut
  AND MP.start_date IS NOT NULL
  AND (SELECT extract(YEAR FROM age(CURRENT_DATE, MP.start_date)) * 12 +
              extract(MONTH FROM age(CURRENT_DATE, MP.start_date))) > 1;

/*
 Consulta 2
 PROBADA.
 */
SELECT DISTINCT ON (Ch.checkup_date) checkup_date,
                                     E.rut,
                                     CONCAT(P.first_names, ' ', P.last_name, ' ',
                                            P.second_last_name) AS full_name,
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
 Consulta 3
 AÚN POR PROBAR.
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
 Consulta 4
 PROBADA.
 */
SELECT M.blood_type, count(*)
FROM residence.medical_record M
GROUP BY M.blood_type
ORDER BY M.blood_type;

-- PARTE 2
SELECT P.rut,
       CONCAT(first_names, ' ', last_name, ' ', second_last_name) as full_name,
       MR.blood_type
from residence.person P,
     residence.elder E,
     residence.medical_record MR
WHERE P.rut = E.rut
  AND E.rut = MR.elder_rut
  AND MR.blood_type = 1;

/*
 Consulta 5
 PROBADA.
 */
WITH medication_aggregation AS (SELECT disease_name,
                                       string_agg(medication_name, ', ' ORDER BY medication_name) AS medications
                                FROM residence.person P,
                                     residence.elder E,
                                     residence.medication_prescription Md
                                WHERE P.rut = E.rut
                                  AND E.rut = Md.elder_rut
                                GROUP BY 1)
SELECT E.rut,
       CONCAT(P.first_names, ' ', P.last_name, ' ', P.second_last_name) AS full_name,
       Esd.disease_name,
       M.medications
FROM residence.person P,
     residence.elder E,
     residence.disease D,
     residence.elder_suffers_disease Esd,
     medication_aggregation M
WHERE P.rut = E.rut
  AND E.rut = Esd.elder_rut
  AND D.disease_name = Esd.disease_name
  AND Esd.disease_name = M.disease_name
  AND D.is_chronic = TRUE;

/*
 Consulta 6
 PENDIENTE.
 */

/*
 Consulta 7
 PROBADA.
 */
WITH medication_aggregation AS (SELECT disease_name,
                                       string_agg(medication_name, ', ' ORDER BY medication_name) AS medications
                                FROM residence.person P,
                                     residence.elder E,
                                     residence.medication_prescription Md
                                WHERE P.rut = E.rut
                                  AND E.rut = Md.elder_rut
                                GROUP BY 1)
SELECT E.rut,
       CONCAT(P.first_names, ' ', P.last_name, ' ', P.second_last_name) AS full_name,
       Esd.disease_name,
       M.medications
FROM residence.person P,
     residence.elder E,
     residence.disease D,
     residence.elder_suffers_disease Esd,
     medication_aggregation M
WHERE P.rut = E.rut
  AND E.rut = Esd.elder_rut
  AND D.disease_name = Esd.disease_name
  AND Esd.disease_name = M.disease_name
  AND lower(M.disease_name) = 'artritis';

/*
 Consulta 8.
 PROBADA.
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
 Consulta 9.
 AÚN POR PROBAR.
 */
SELECT DISTINCT ON (RC.checkup_date) checkup_date,
                                     P.rut,
                                     CONCAT(P.first_names, ' ', P.last_name, ' ',
                                            P.second_last_name) as full_name
FROM residence.person P,
     residence.elder E,
     residence.routine_checkup RC,
     residence.elder_suffers_disease SD
WHERE P.rut = E.rut
    AND E.rut = RC.elder_rut
    AND RC.elder_rut = SD.elder_rut
    AND lower(SD.disease_name) LIKE '%hipertension%'
   OR lower(SD.disease_name) LIKE '%hipertensión%'
    AND RC.diastolic_pressure < 80
   OR RC.systolic_pressure > 130
ORDER BY checkup_date DESC;

/*
 Consulta 10.
 PROBADA.
 */
SELECT personE.rut                      AS elder_rut,
       CONCAT(personE.first_names, ' ', personE.last_name, ' ',
              personE.second_last_name) as elder_full_name,
       personR.rut                      AS responsible_rut,
       CONCAT(personR.first_names, ' ', personR.last_name, ' ',
              personR.second_last_name) as responsible_full_name,
       CONCAT('+56 9 ', R.mobile_phone) AS responsible_mobile_phone,
       Reg.region_name,
       Prov.province_name,
       Comm.commune_name,
       A.street,
       A.number
FROM residence.person personE,
     residence.elder E,
     residence.person personR,
     residence.responsible R,
     residence.address A,
     residence.region Reg,
     residence.province Prov,
     residence.commune Comm
WHERE Reg.id = Prov.region_id
  AND Prov.id = Comm.province_id
  AND Comm.id = A.commune_id
  AND A.person_rut = personR.rut
  AND personE.rut = E.rut
  AND personR.rut = R.rut
  AND R.rut = E.responsible_rut;

/*
 Consulta 11
 AÚN POR PROBAR.
 */
SELECT P.rut,
       CONCAT(P.first_names, ' ', P.last_name, ' ',
              P.second_last_name),
       SD.disease_name,
       SD.diagnosis_date
FROM residence.person P,
     residence.elder E,
     residence.elder_suffers_disease SD
WHERE P.rut = E.rut
  AND E.rut = SD.elder_rut;

/**
  CONSULTA 11.
  PROBADA.
 */
WITH days AS (SELECT Pr.elder_rut                                     as rut,
                     Pr.prescription_date::date - MP.start_date::date AS difference
              FROM residence.prescription Pr,
                   residence.medication_prescription MP
              WHERE Pr.elder_rut = MP.elder_rut)
SELECT P.rut,
       CONCAT(P.first_names, ' ', P.last_name, ' ',
              P.second_last_name),
       Pr.prescription_date,
       MP.start_date,
       D.difference
FROM residence.person P,
     residence.elder E,
     residence.prescription Pr,
     residence.medication_prescription MP,
     days D
WHERE P.rut = E.rut
  AND E.rut = Pr.elder_rut
  AND Pr.elder_rut = MP.elder_rut
  AND MP.elder_rut = D.rut
  AND D.difference > 1;

/*
 CONSULTA 12.
 PROBADO.
 */
WITH full_address AS (SELECT Reg.id             AS region_id,
                             Reg.region_name    AS region,
                             Prov.province_name AS province,
                             Comm.commune_name  AS commune,
                             A.person_rut       AS rut,
                             A.street,
                             A.number
                      FROM residence.address A,
                           residence.responsible R,
                           residence.region Reg,
                           residence.province Prov,
                           residence.commune Comm
                      WHERE Reg.id = Prov.region_id
                        AND Prov.id = Comm.province_id
                        AND Comm.id = A.commune_id
                        AND A.person_rut = R.rut),
     person_table AS (SELECT personE.rut                      AS elder_rut,
                             CONCAT(personE.first_names, ' ', personE.last_name, ' ',
                                    personE.second_last_name) as elder_full_name,
                             personR.rut                      AS responsible_rut,
                             CONCAT(personR.first_names, ' ', personR.last_name, ' ',
                                    personR.second_last_name) as responsible_full_name,
                             CONCAT('+56 9 ', R.mobile_phone) AS responsible_mobile_phone
                      FROM residence.person personE,
                           residence.elder E,
                           residence.person personR,
                           residence.responsible R
                      WHERE personE.rut = E.rut
                        AND personR.rut = R.rut
                        AND R.rut = E.responsible_rut)
SELECT P.elder_rut,
       P.elder_full_name,
       P.responsible_rut,
       P.responsible_full_name,
       P.responsible_mobile_phone,
       F.region,
       F.province,
       F.commune,
       F.street,
       F.number
FROM person_table P,
     full_address F
WHERE P.responsible_rut = F.rut
  AND F.region_id <> 5;

/*
 CONSULTA 12 (FORMA ALTERNATIVA)
 PROBADO
 */
WITH full_address AS (SELECT Reg.id             AS region_id,
                             Reg.region_name    AS region,
                             Prov.province_name AS province,
                             Comm.commune_name  AS commune,
                             A.person_rut       AS rut,
                             A.street,
                             A.number
                      FROM residence.address A
                               INNER JOIN residence.commune Comm on Comm.id = A.commune_id
                               INNER JOIN residence.province Prov on Prov.id = Comm.province_id
                               INNER JOIN residence.region Reg on Reg.id = Prov.region_id
                               INNER JOIN residence.responsible R on R.rut = A.person_rut),
     person_table AS (SELECT personE.rut                      AS elder_rut,
                             CONCAT(personE.first_names, ' ', personE.last_name, ' ',
                                    personE.second_last_name) as elder_full_name,
                             personR.rut                      AS responsible_rut,
                             CONCAT(personR.first_names, ' ', personR.last_name, ' ',
                                    personR.second_last_name) as responsible_full_name,
                             CONCAT('+56 9 ', R.mobile_phone) AS responsible_mobile_phone
                      FROM residence.person personE,
                           residence.elder E,
                           residence.person personR,
                           residence.responsible R
                      WHERE personE.rut = E.rut
                        AND personR.rut = R.rut
                        AND R.rut = E.responsible_rut)
SELECT P.elder_rut,
       P.elder_full_name,
       P.responsible_rut,
       P.responsible_full_name,
       P.responsible_mobile_phone,
       F.region,
       F.province,
       F.commune,
       F.street,
       F.number
FROM person_table P,
     full_address F
WHERE P.responsible_rut = F.rut
  AND F.region_id <> 5;

SELECT DISTINCT E.rut                      AS elder_rut,
                CONCAT(E.first_names, ' ', E.last_name, ' ',
                       E.second_last_name) as elder_full_name
from residence.person E,
     residence.elder,
     residence.elder_suffers_disease SD,
     residence.disease D
WHERE E.rut = elder.rut
  AND E.rut = SD.elder_rut
  AND SD.disease_name = D.disease_name
  AND D.is_chronic = TRUE;

SELECT DISTINCT E.rut                      AS elder_rut,
                CONCAT(E.first_names, ' ', E.last_name, ' ',
                       E.second_last_name) as elder_full_name
from residence.person E,
     residence.elder,
     residence.elder_suffers_disease SD,
     residence.disease D,
     residence.medication_prescription MP
WHERE E.rut = elder.rut
  AND E.rut = SD.elder_rut
  AND SD.disease_name = D.disease_name
  AND D.is_chronic = FALSE
  AND MP.disease_name = SD.disease_name
  AND MP.end_date IS NOT NULL;

SELECT DISTINCT E.rut                      AS elder_rut,
                CONCAT(E.first_names, ' ', E.last_name, ' ',
                       E.second_last_name) as elder_full_name
from residence.person E,
     residence.elder,
     residence.elder_suffers_disease SD,
     residence.disease D,
     residence.medication_prescription MP
WHERE E.rut = elder.rut
  AND E.rut = SD.elder_rut
  AND SD.disease_name = D.disease_name
  AND D.is_chronic = FALSE
  AND MP.disease_name = SD.disease_name
  AND MP.end_date IS NULL;