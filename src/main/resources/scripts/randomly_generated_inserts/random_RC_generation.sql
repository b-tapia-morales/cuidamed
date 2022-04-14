/* Lo que había hecho ayer, falta hacer una forma de hacerlo para cada adulto mayor */
CREATE or replace PROCEDURE generate_random_RCs(rut varchar, height real, weight real)
    LANGUAGE plpgsql
AS
$$
BEGIN
    INSERT INTO residence.routine_checkup(rut, checkup_date, height, weight, bmi, heart_rate,
                                          diastolic_pressure, systolic_pressure, body_temperature)
    SELECT rut,
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
end;
$$;
--DELETE
--FROM residence.routine_checkup;
call generate_random_RCs('8768514-4', 1.75, 40.3);
select *
from routine_checkup;