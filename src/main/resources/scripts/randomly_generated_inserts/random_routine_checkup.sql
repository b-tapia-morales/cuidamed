SET search_path = "residence";

DROP PROCEDURE IF EXISTS insert_random_medical_records();
DROP PROCEDURE IF EXISTS insert_random_routine_checkups();

CREATE OR REPLACE PROCEDURE insert_random_medical_records()
    LANGUAGE plpgsql AS
$$
DECLARE
    ruts TEXT ARRAY DEFAULT ARRAY(SELECT rut
                                  FROM elder);
    n    INTEGER DEFAULT cardinality(ruts);
BEGIN
    FOR i in 1..n
        LOOP
            INSERT INTO medical_record
            VALUES (ruts[i], floor(random() * 8 + 1)::int, floor(random() * 2 + 1)::int);
        END LOOP;
END;
$$;

CREATE OR REPLACE PROCEDURE insert_random_routine_checkups()
    LANGUAGE plpgsql
AS
$$
DECLARE
    elder_ruts        TEXT ARRAY DEFAULT array(SELECT rut
                                               FROM elder);
    admission_dates   DATE ARRAY DEFAULT ARRAY(SELECT admission_date
                                               FROM elder);
    n                 INTEGER DEFAULT (SELECT count(*)
                                       FROM medical_record);
    calculated_height DOUBLE PRECISION;
    calculated_weight DOUBLE PRECISION;
    calculated_bmi    DOUBLE PRECISION;
    elder_gender      SMALLINT;
BEGIN
    FOR i in 1..n
        LOOP
            elder_gender =
                    (SELECT gender FROM person WHERE rut = elder_ruts[i]);
            CASE
                WHEN elder_gender = 1 THEN calculated_height = (random() * (1.75 - 1.55) + 1.55);
                WHEN elder_gender = 2 THEN calculated_height = (random() * (1.65 - 1.45) + 1.45);
                END CASE;
            calculated_bmi = (random() * (34.99 - 16.00) + 16.00);
            calculated_weight = pow(calculated_height, 2) * calculated_bmi;
            INSERT INTO routine_checkup(rut, checkup_date, height, weight, bmi, heart_rate, diastolic_pressure,
                                        systolic_pressure, body_temperature)
            SELECT elder_ruts[i],
                   generated_date,
                   round(calculated_height::numeric, 2),
                   round(calculated_weight::numeric, 1),
                   round(calculated_bmi::numeric, 1),
                   round((random() * (110 - 70) + 70)::numeric, 0),
                   round((random() * (120 - 70) + 70)::numeric, 0),
                   round((random() * (180 - 110) + 110)::numeric, 0),
                   round((random() * (41 - 35) + 35)::numeric, 1)
            FROM generate_series(admission_dates[i] :: date,
                                 now() :: date,
                                 make_interval(days => 1)) as generated_date;
        end loop;
end;
$$;

CALL insert_random_medical_records();

SELECT *
FROM person
         NATURAL JOIN elder
         NATURAL JOIN medical_record;

CALL insert_random_routine_checkups();

SELECT *
FROM person
         NATURAL JOIN elder
         NATURAL JOIN routine_checkup
ORDER BY checkup_date
LIMIT 200;

EXPLAIN ANALYZE
SELECT *
FROM routine_checkup
WHERE checkup_date < '2016-01-01'
  AND checkup_date < '2015-01-02';