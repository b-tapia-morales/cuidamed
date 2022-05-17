SET
    search_path = "residence";

DROP PROCEDURE IF EXISTS batch_insert_people(INTEGER);
DROP PROCEDURE IF EXISTS batch_insert_people(INTEGER, INTEGER, INTEGER, INTEGER, INTEGER);
DROP PROCEDURE IF EXISTS batch_insert_elders(INTEGER);
DROP PROCEDURE IF EXISTS batch_insert_responsibles(INTEGER);

CREATE
    OR REPLACE PROCEDURE batch_insert_people(n INTEGER)
    LANGUAGE plpgsql AS
$func$
DECLARE
    responsible_mobile_phones INTEGER ARRAY DEFAULT ARRAY(SELECT (floor(random() * (70000000) + 30000000))
                                                          FROM generate_series(1, n));
    elder_admission_dates
                              DATE ARRAY DEFAULT generate_random_date_arr(n, 1, 20);
    people_rut_arr
                              TEXT ARRAY;
    elder_rut_arr
                              TEXT ARRAY;
    responsible_rut_arr
                              TEXT ARRAY;
BEGIN
    CALL batch_insert_responsibles(n);
    CALL batch_insert_elders(n);
    people_rut_arr = ARRAY(SELECT rut FROM person);
    responsible_rut_arr = people_rut_arr[:n];
    elder_rut_arr = people_rut_arr[n + 1:];
    FOR i in 1..n
        LOOP
            INSERT INTO responsible (rut, mobile_phone)
            VALUES (responsible_rut_arr[i], responsible_mobile_phones[i]);
            INSERT INTO elder (rut, is_active, admission_date, responsible_rut)
            VALUES (elder_rut_arr[i], true, elder_admission_dates[i], responsible_rut_arr[i]);
        END LOOP;
END;
$func$;

CREATE
    OR REPLACE PROCEDURE batch_insert_people(size INTEGER, starting_digit INTEGER, ending_digit INTEGER,
                                             starting_timeframe INTEGER, ending_timeframe INTEGER)
    LANGUAGE plpgsql AS
$func$
DECLARE
    n INTEGER DEFAULT ((random() * 0.30 + 0.35) * size)::int;
    m
      INTEGER DEFAULT size - n;
    ruts
      TEXT ARRAY DEFAULT generate_rut_arr(size, starting_digit, ending_digit);
    male_names
      TEXT ARRAY DEFAULT generate_male_first_names_arr(n);
    female_names
      TEXT ARRAY DEFAULT generate_female_first_names_arr(m);
    last_names
      TEXT ARRAY DEFAULT generate_last_names_arr(size);
    second_last_names
      TEXT ARRAY DEFAULT generate_last_names_arr(size);
    birth_dates
      DATE ARRAY DEFAULT generate_random_date_arr(size, starting_timeframe, ending_timeframe);
BEGIN
    FOR i IN 1..n
        LOOP
            INSERT INTO person (rut, first_names, last_name, second_last_name, birth_date, gender)
            VALUES (ruts[i], male_names[i], last_names[i], second_last_names[i],
                    birth_dates[i], 1);
        END LOOP;
    FOR j IN 1..m
        LOOP
            INSERT INTO person (rut, first_names, last_name, second_last_name, birth_date, gender)
            VALUES (ruts[n + j], female_names[j], last_names[n + j], second_last_names[n + j],
                    birth_dates[n + j], 2);
        END LOOP;
END;
$func$;

CREATE
    OR REPLACE PROCEDURE batch_insert_elders(n INTEGER)
    LANGUAGE plpgsql AS
$func$
BEGIN
    CALL batch_insert_people(n, 3, 6, 70, 90);
END;
$func$;

CREATE
    OR REPLACE PROCEDURE batch_insert_responsibles(n INTEGER)
    LANGUAGE plpgsql AS
$func$
BEGIN
    CALL batch_insert_people(n, 10, 15, 30, 50);
END;
$func$;