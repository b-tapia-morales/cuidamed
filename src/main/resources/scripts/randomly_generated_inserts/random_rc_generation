SET search_path = "residence";

DROP PROCEDURE IF EXISTS generate_medical_records();

/* Hay que crear fichas médicas para todos los viejujos o no se podrán crear chequeos rutinarios en
   cada uno
 */
CREATE or replace PROCEDURE generate_medical_records()
    LANGUAGE plpgsql AS
$$
DECLARE
    ruts TEXT ARRAY DEFAULT ARRAY(SELECT rut
                                  FROM residence.elder);
    n    INTEGER DEFAULT cardinality(ruts);
BEGIN
    FOR i in 1..n
        LOOP
            INSERT INTO residence.medical_record
            VALUES (ruts[i], floor(random() * 8 + 1)::int, floor(random() * 2 + 1)::int);
        END LOOP;
END;
$$;

CREATE or replace PROCEDURE generate_random_RCs()
    LANGUAGE plpgsql
AS
$$
DECLARE
    elder_ruts      varchar array default array(select elder.rut
                                                from residence.elder);
    admission_dates date array default ARRAY(select elder.admission_date
                                             from residence.elder);
    n               INTEGER DEFAULT (select count(*)
                                     from residence.medical_record);
    height          real;
    new_bmi         real;
    elder_gender    smallint;
BEGIN
    FOR i in 1..n
        LOOP
            elder_gender =
                    (SELECT person.gender from residence.person where person.rut = elder_ruts[i]);
            CASE
                WHEN elder_gender = 1 THEN height = (random() * (1.75 - 1.55) + 1.55);
                WHEN elder_gender = 2 THEN height = (random() * (1.65 - 1.45) + 1.45);
                ELSE
                    height = (random() * (1.8 - 1.4) + 1.4);
                END CASE;
            new_bmi = (random() * (34.99 - 16) + 16);
            INSERT INTO residence.routine_checkup(rut, checkup_date, height, weight, bmi,
                                                  heart_rate,
                                                  diastolic_pressure, systolic_pressure,
                                                  body_temperature)
            SELECT elder_ruts[i],
                   fecha,
                   height,
                   pow(height, 2) * new_bmi,
                   new_bmi,
                   (round(random() * (100 - 60) + 60)),
                   random() * (100 - 70) + 70,
                   random() * (150 - 110) + 110,
                   random() * (39 - 35) + 35
            FROM generate_series(admission_dates[i] :: date,
                                 now() :: date,
                                 '1 day' :: interval) as fecha;
        end loop;
end;
$$;

CALL generate_medical_records();
select *
from residence.routine_checkup;
SELECT *
FROM residence.person
         NATURAL JOIN residence.elder
         NATURAL JOIN residence.medical_record;

--DELETE
--FROM residence.routine_checkup;
call generate_random_RCs();
explain analyse
select *
from routine_checkup
where checkup_date < '2016-01-01'
  and checkup_date < '2015-01-02';