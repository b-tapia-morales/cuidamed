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
    m                 INTEGER;
    n                 INTEGER DEFAULT (SELECT count(*)
                                       FROM medical_record);
    calculated_height DOUBLE PRECISION;
    calculated_weight DOUBLE PRECISION;
    calculated_bmi    DOUBLE PRECISION;
    is_negative       BOOLEAN;
    delta             DOUBLE PRECISION;
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
            calculated_bmi = (random() * (34.99 - 17.00) + 17.00);
            calculated_weight = calculated_bmi * pow(calculated_height, 2);
            SELECT(current_date - admission_dates[i]::date) INTO m;
            FOR j in 1..m
                LOOP
                    is_negative = (random() < 0.5);
                    delta = (random() * 0.5);
                    IF ((is_negative) AND (calculated_bmi <= 17.00)) THEN
                        delta = abs(delta);
                    ELSIF ((NOT is_negative) AND (calculated_bmi >= 34.99)) THEN
                        delta = -delta;
                    ELSIF (is_negative) THEN
                        delta = -delta;
                    END IF;
                    calculated_weight = calculated_weight + delta;
                    calculated_bmi = calculated_weight / pow(calculated_height, 2);
                    INSERT INTO routine_checkup(rut, checkup_date, height, weight, bmi, heart_rate,
                                                diastolic_pressure,
                                                systolic_pressure, body_temperature)
                    VALUES (elder_ruts[i],
                            current_date - make_interval(days => (j - 1)),
                            round(calculated_height::numeric, 2),
                            round(calculated_weight::numeric, 1),
                            round(calculated_bmi::numeric, 1),
                            floor(random() * (110 - 70) + 70)::int,
                            floor(random() * (120 - 70) + 70)::int,
                            floor(random() * (180 - 110) + 110)::int,
                            round((random() * (41 - 35) + 35)::numeric, 1));
                END LOOP;
        END LOOP;
END;
$$;

CALL insert_random_medical_records();
CALL insert_random_routine_checkups();

SELECT *
FROM person
         NATURAL JOIN elder
         NATURAL JOIN medical_record;

SELECT *
FROM person
         NATURAL JOIN elder
         NATURAL JOIN routine_checkup
WHERE bmi < 17.00 OR bmi > 34.99
ORDER BY weight
LIMIT 500;

EXPLAIN ANALYZE
SELECT *
FROM routine_checkup
WHERE checkup_date > '2015-01-01'
  AND checkup_date < '2016-01-01';