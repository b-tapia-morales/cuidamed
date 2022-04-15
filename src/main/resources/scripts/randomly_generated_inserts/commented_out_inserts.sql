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
*/

--DELETE FROM residence.routine_checkup;
--call generate_random_RCs('8768514-4', 1.59, 53.3);