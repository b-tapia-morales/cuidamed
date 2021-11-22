/*
 Consulta 1
 Mostrar el rut, el nombre completo, el nombre del medicamento que tiene prescrito, y la fecha original
 de prescripción de todos los adultos mayores cuyo tratamiento haya iniciado hace más de un año.
 */
SELECT E.rut,
       CONCAT(P.first_names, ' ', P.last_name, ' ', P.second_last_name) AS full_name,
       MP.medication_name,
       MP.start_date
FROM residence.elder E,
     residence.person P,
     residence.medication_prescription MP
WHERE P.rut = E.rut
  AND E.rut = MP.rut
  AND MP.start_date IS NOT NULL
  AND (SELECT extract(YEAR FROM age(CURRENT_DATE, MP.start_date)) * 12 +
              extract(MONTH FROM age(CURRENT_DATE, MP.start_date))) > 12
ORDER BY MP.start_date;

/*
 Mostrar el rut, el nombre completo, los medicamentos prescritos, y la fecha original
 de prescripción de todos los adultos mayores cuyo tratamiento haya iniciado hace más de un año.
 */
WITH medication_aggregation AS (SELECT DISTINCT E.rut,
                                                MP.disease_name,
                                                MP.start_date,
                                                string_agg(medication_name, ', ' ORDER BY medication_name) AS medications
                                FROM residence.person P,
                                     residence.elder E,
                                     residence.medication_prescription MP
                                WHERE P.rut = E.rut
                                  AND E.rut = MP.rut
                                GROUP BY E.rut, MP.disease_name, MP.start_date)
SELECT E.rut,
       CONCAT(P.first_names, ' ', P.last_name, ' ', P.second_last_name) AS full_name,
       M.disease_name,
       M.start_date,
       M.medications
FROM residence.person P
         INNER JOIN residence.elder E ON P.rut = E.rut
         RIGHT OUTER JOIN medication_aggregation M ON E.rut = M.rut
WHERE (SELECT extract(YEAR FROM age(CURRENT_DATE, M.start_date)) * 12 +
              extract(MONTH FROM age(CURRENT_DATE, M.start_date))) > 12
ORDER BY M.start_date;

/*
 Mostrar la fecha de chequeo rutinario, el rut, el nombre completo y el IMC de todos los adultos
 mayores que estén en condición de obesidad o de desnutrición.
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
    AND E.rut = Ch.rut
    AND Ch.bmi <= 18.4
   OR Ch.bmi >= 25.0
ORDER BY checkup_date DESC;

/*
 Mostrar el rut, el nombre completo, la fecha de prescripción, de inicio y término de tratamiento de
 todos los adultos mayores que hayan sido administrados el medicamento de Ibuprofeno.
 */
SELECT E.rut,
       CONCAT(Pe.first_names, ' ', Pe.last_name, ' ', Pe.second_last_name) as full_name,
       MP.prescription_date,
       MP.start_date,
       MP.end_date
FROM residence.person Pe,
     residence.elder E,
     residence.medication_prescription MP
WHERE Pe.rut = E.rut
  AND E.rut = Mp.rut
  AND MP.medication_name = 'Ibuprofeno';

/*
 Mostrar la cantidad de adultos mayores correspondientes a cada grupo sanguíneo.
 */
SELECT M.blood_type, count(*)
FROM residence.medical_record M
GROUP BY M.blood_type
ORDER BY M.blood_type;

/*
 Mostrar el rut, el nombre completo, el nombre de la enfermedad y los medicamentos de todos los adultos
 mayores que padezcan de enfermedades crónicas.
 */
WITH medication_aggregation AS (SELECT disease_name,
                                       string_agg(medication_name, ', ' ORDER BY medication_name) AS medications
                                FROM residence.person P,
                                     residence.elder E,
                                     residence.medication_prescription Md
                                WHERE P.rut = E.rut
                                  AND E.rut = Md.rut
                                GROUP BY 1)
SELECT E.rut,
       CONCAT(P.first_names, ' ', P.last_name, ' ', P.second_last_name) AS full_name,
       SE.disease_name,
       M.medications
FROM residence.person P,
     residence.elder E,
     residence.disease D,
     residence.sick_elderly SE,
     medication_aggregation M
WHERE P.rut = E.rut
  AND E.rut = SE.rut
  AND D.disease_name = SE.disease_name
  AND SE.disease_name = M.disease_name
  AND D.is_chronic = TRUE;

/*
 Mostrar el rut, el nombre completo, el nombre de la enfermedad y los medicamentos de todos los adultos
 mayores que padezcan de Artritis.
 */
WITH medication_aggregation AS (SELECT disease_name,
                                       string_agg(medication_name, ', ' ORDER BY medication_name) AS medications
                                FROM residence.person P,
                                     residence.elder E,
                                     residence.medication_prescription Md
                                WHERE P.rut = E.rut
                                  AND E.rut = Md.rut
                                GROUP BY 1)
SELECT E.rut,
       CONCAT(P.first_names, ' ', P.last_name, ' ', P.second_last_name) AS full_name,
       SE.disease_name,
       M.medications
FROM residence.person P,
     residence.elder E,
     residence.disease D,
     residence.sick_elderly SE,
     medication_aggregation M
WHERE P.rut = E.rut
  AND E.rut = SE.rut
  AND D.disease_name = SE.disease_name
  AND SE.disease_name = M.disease_name
  AND lower(M.disease_name) = 'artritis';

/*
 Mostrar el rut, el nombre completo, la fecha de intervención, el grado de severidad y la descripción
 del procedimiento de todos los adultos mayores que hayan sido atendidos en Clínicas.
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
  AND E.rut = I.rut
  AND I.hospital LIKE '%Clínica%';


/*
 Mostrar la fecha de chequeo rutinario, el rut, el nombre completo, la presión diastólica y sistólica
 y el ritmo cardiaco de todos los adultos mayores que hayan sido diagnosticados com Hipertensión.
 */
SELECT DISTINCT ON (RC.checkup_date) checkup_date,
                                     P.rut,
                                     CONCAT(P.first_names, ' ', P.last_name, ' ',
                                            P.second_last_name) as full_name,
                                     RC.diastolic_pressure,
                                     RC.systolic_pressure,
                                     RC.heart_rate
FROM residence.person P,
     residence.elder E,
     residence.routine_checkup RC,
     residence.sick_elderly SE
WHERE P.rut = E.rut
    AND E.rut = RC.rut
    AND RC.rut = SE.rut
    AND lower(SE.disease_name) LIKE '%hipertension%'
   OR lower(SE.disease_name) LIKE '%hipertensión%'
ORDER BY checkup_date DESC;

/*
 Mostrar rut y nombre completo de todos los adultos mayores con sus respectivos responsables a cargo,
 incluyendo rut, número de contacto y datos de localización.
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
  AND A.rut = personR.rut
  AND personE.rut = E.rut
  AND personR.rut = R.rut
  AND R.rut = E.responsible_rut;

/*
 Mostrar el rut, el nombre completo, la fecha de prescripción, de inicio del tratamiento y la diferencia
 en días entre estas dos últimas fechas de todos los adultos mayores.
 */
SELECT DISTINCT Pr.rut,
                CONCAT(P.first_names, ' ', P.last_name, ' ',
                       P.second_last_name)                                 AS full_name,
                Pr.prescription_date,
                MP.start_date,
                extract(DAY FROM age(MP.start_date, Pr.prescription_date)) AS difference
FROM residence.person P,
     residence.elder E,
     residence.prescription Pr,
     residence.medication_prescription MP
WHERE P.rut = E.rut
  AND E.rut = Pr.rut
  AND Pr.rut = MP.rut
  AND Pr.disease_name = MP.disease_name
  AND Pr.prescription_date = MP.prescription_date
ORDER BY difference DESC;

/*
 Mostrar rut y nombre completo de todos los adultos mayores cuyos responsables a cargo vivan fuera de
 la Región de Coquimbo, incluyendo rut, nombre completo, número de contacto y datos de localización.
 */
WITH full_address AS (SELECT Reg.id             AS region_id,
                             Reg.region_name    AS region,
                             Prov.province_name AS province,
                             Comm.commune_name  AS commune,
                             Addr.rut           AS rut,
                             Addr.street,
                             Addr.number
                      FROM residence.address Addr,
                           residence.responsible R,
                           residence.region Reg,
                           residence.province Prov,
                           residence.commune Comm
                      WHERE Reg.id = Prov.region_id
                        AND Prov.id = Comm.province_id
                        AND Comm.id = Addr.commune_id
                        AND Addr.rut = R.rut),
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
 Mostrar la edad promedio de los adultos mayores.
 */
WITH ages AS (SELECT date_part('year', age(CURRENT_DATE, P.birth_date)) AS difference
              FROM residence.person P,
                   residence.elder E
              WHERE P.rut = E.rut)
SELECT avg(difference)
FROM ages;

/*
 Mostrar la diferencia en edad promedio entre los adultos mayores y sus responsables.
 */
WITH elder AS (SELECT date_part('year', age(CURRENT_DATE, P.birth_date)) AS age
               FROM residence.person P,
                    residence.elder E
               WHERE P.rut = E.rut),
     responsible AS (SELECT date_part('year', age(CURRENT_DATE, P.birth_date)) AS age
                     FROM residence.person P,
                          residence.responsible R
                     WHERE P.rut = R.rut)
SELECT avg(E.age)                                           as elder_avg_age,
       avg(R.age)                                           as responsible_avg_age,
       (round(cast(avg(E.age) - avg(R.age) AS NUMERIC), 2)) as avg_age_difference
FROM elder E,
     responsible R;

/*
 Mostrar la cantidad de adultos mayores correspondientes a una edad determinada.
 */
SELECT date_part('year', age(CURRENT_DATE, P.birth_date)) AS age, count(*)
FROM residence.person P,
     residence.elder E
WHERE P.rut = E.rut
GROUP BY age
ORDER BY age;

/*
 Mostrar la cantidad de adultos mayores que tengan enfermedades de carácter crónico, la cantidad de
 adultos mayores que tengan enfermedades no crónicas cuyo tratamiento ya concluyó, y la cantidad de
 adultos mayores que tengan enfermedades no crónicas cuyo tratamiento aún no concluye.
 */
WITH C AS (SELECT count(*)
           FROM residence.person P,
                residence.elder E,
                residence.sick_elderly SE,
                residence.disease D
           WHERE P.rut = E.rut
             AND E.rut = SE.rut
             AND SE.disease_name = D.disease_name
             AND D.is_chronic = TRUE),
     NCA AS (SELECT count(*)
             from residence.person P,
                  residence.elder E,
                  residence.sick_elderly SE,
                  residence.medication_prescription MP,
                  residence.disease D
             WHERE P.rut = E.rut
               AND E.rut = SE.rut
               AND SE.disease_name = D.disease_name
               AND D.is_chronic = FALSE
               AND MP.end_date IS NOT NULL
               AND extract(DAY FROM age(MP.end_date, CURRENT_DATE)) > 1),
     NCNA AS (SELECT count(*)
              from residence.person P,
                   residence.elder E,
                   residence.sick_elderly SE,
                   residence.medication_prescription MP,
                   residence.disease D
              WHERE P.rut = E.rut
                AND E.rut = SE.rut
                AND SE.disease_name = D.disease_name
                AND D.is_chronic = FALSE
                AND MP.end_date IS NOT NULL
                AND extract(DAY FROM age(MP.end_date, CURRENT_DATE)) < 1)
SELECT C.count    AS chronic_active,
       NCA.count  AS not_chronic_active,
       NCNA.count AS not_chronic_not_active
FROM C,
     NCA,
     NCNA;
