
/* Hay que crear fichas médicas para todos los viejujos o no se podrán crear chequeos rutinarios en
   cada uno
 */
CREATE or replace PROCEDURE generate_medical_records()
    LANGUAGE plpgsql
AS
$$
DECLARE
    ruts varchar array default array(select elder.rut
                                     from residence.elder);
    n    INTEGER DEFAULT (select count(*)
                          from residence.elder);
BEGIN
    FOR i in 1..n
        LOOP
            INSERT INTO residence.medical_record
            VALUES (ruts[i], (round(random() * (9 - 0) + 0)), (round(random() * (3 - 0) + 0)));
        end loop;
end;
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
    weight          real;
BEGIN
    FOR i in 1..n
        LOOP
            height = (random() * (2 - 1.34) + 1.34);
            weight = (random() * (100 - 49) + 49);
            INSERT INTO residence.routine_checkup(rut, checkup_date, height, weight, bmi,
                                                  heart_rate,
                                                  diastolic_pressure, systolic_pressure,
                                                  body_temperature)
            SELECT elder_ruts[i],
                   fecha,
                   height,
                   weight - (random() * (0.5 - (-0.5)) + (-0.5)),
                   (weight / (pow(height, 2))),
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

call generate_medical_records();
--DELETE
--FROM residence.routine_checkup;
call generate_random_RCs();
select *
from routine_checkup;
explain analyse select *
from routine_checkup where checkup_date < '2016-01-01' and checkup_date < '2015-01-02';