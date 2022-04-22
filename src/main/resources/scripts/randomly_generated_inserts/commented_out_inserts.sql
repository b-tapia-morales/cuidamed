/*
 CREATE OR REPLACE PROCEDURE insert_random_routine_checkups_alt()
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
 */

/*
CREATE OR REPLACE FUNCTION insert_medication_administration_alt() RETURNS TRIGGER AS
$$
DECLARE
    carer_rut    TEXT ARRAY DEFAULT array(SELECT rut
                                          FROM carer);
    n            INTEGER DEFAULT cardinality(carer_rut);
    date_current TIMESTAMP;
    TIMES        TIME ARRAY DEFAULT ARRAY ['12:00:00','06:00:00','04:00:00','03:00:00', '02:20:00'];
BEGIN
    date_current = CURRENT_TIMESTAMP;
    FOR i in 1..new.quantity
        LOOP
            IF i = 1 THEN
                INSERT INTO medication_administration
                VALUES (new.rut, new.medication_name, date_trunc('second', date_current::timestamp), null, 0,
                        carer_rut[floor(random() * (n - 1) + 1)]);
            ELSE
                INSERT INTO medication_administration
                VALUES (new.rut, new.medication_name, date_trunc('second', date_current::timestamp), null, 0,
                        carer_rut[floor(random() * (n - 1) + 1)]);
            END IF;
            date_current = date_current + TIMES[new.quantity - 1];
        END LOOP;
    return new;
END
$$ LANGUAGE plpgsql;
*/

/*
WITH ruts AS (SELECT * FROM unnest(generate_rut_arr(100, 1, 5))),
     first_names AS (SELECT *
                     FROM unnest(generate_concatenated_male_names(100))),
     last_names AS (
         SELECT *
         FROM unnest(generate_concatenated_male_names(100))),
     INSERT INTO residence.person (rut, first_name, last_name, second_last_name, birth_date, gender)
     SELECT rut[1 + floor(random() * n)::int],
           fecha,
           height,
           weight - (random() * (3 - (-3)) + 10),
           (weight / (height * height)),
           (round(random() * (100 - 60) + 10)),
           random() * (140 - 80) + 10,
           random() * (120 - 80) + 10,
           random() * (35 - 39) + 10
    FROM generate_series('2000-01-01' :: date,
                         '2030-12-01' :: date,
                         '1 day' :: interval) as fecha;


CALL generate_random_elder(100);

CALL generate_random_responsible(100);

SELECT *
FROM residence.person;

DELETE
FROM residence.person;

SELECT *
FROM residence.person WHERE birth_date > '1940-01-01'::date;

select
        date (timestamp '2016-01-01' +
              random() * (timestamp '2017-12-31' - timestamp '2016-01-01'))
        + time '10:00:00'
        + random() * INTERVAL '8 hours';

select (NOW() + (random() * (interval '65 years')) + '90 years')::date;

Index on medication_administration

DROP INDEX IF EXISTS extract_hour_hash;
DROP INDEX IF EXISTS extract_hour_btree;

DROP INDEX IF EXISTS date_diff_hash;
DROP INDEX IF EXISTS date_diff_btree;

EXPLAIN ANALYZE SELECT *
FROM medication_administration
WHERE extract(DAY FROM estimated_datetime - now()) = 1
  AND extract(HOUR FROM estimated_datetime) = (12);

CREATE INDEX extract_hour_hash ON medication_administration USING hash (extract(HOUR FROM estimated_datetime));
CREATE INDEX extract_hour_btree ON medication_administration USING btree (extract(HOUR FROM estimated_datetime));

CREATE INDEX date_diff_hash ON medication_administration USING hash (extract(DAY FROM estimated_datetime - now()));
CREATE INDEX date_diff_btree ON medication_administration USING btree (extract(DAY FROM estimated_datetime - now()));

EXPLAIN ANALYZE SELECT *
FROM medication_administration
WHERE extract(DAY FROM estimated_datetime - now()) = 1
  AND extract(HOUR FROM estimated_datetime) = (12);

*/

--DELETE FROM residence.routine_checkup;
--call generate_random_RCs('8768514-4', 1.59, 53.3);