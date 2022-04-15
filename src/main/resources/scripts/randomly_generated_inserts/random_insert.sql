SET search_path = "residence";

DROP PROCEDURE IF EXISTS batch_insert_people(INTEGER, INTEGER, INTEGER, INTEGER, INTEGER);
DROP PROCEDURE IF EXISTS batch_insert_elders(INTEGER);
DROP PROCEDURE IF EXISTS batch_insert_responsibles(INTEGER);

CREATE or replace PROCEDURE batch_insert_people(size INTEGER, starting_digit INTEGER, ending_digit INTEGER,
                                                starting_timeframe INTEGER, ending_timeframe INTEGER)
    LANGUAGE plpgsql
AS
$$
DECLARE
    ruts              TEXT ARRAY DEFAULT generate_rut_arr(size, starting_digit, ending_digit);
    n                 INTEGER DEFAULT 1 + floor(random() * size)::int;
    m                 INTEGER DEFAULT size - n;
    male_names        TEXT ARRAY DEFAULT generate_male_first_names_arr(n);
    female_names      TEXT ARRAY DEFAULT generate_female_first_names_arr(m);
    last_names        TEXT ARRAY DEFAULT generate_last_names_arr(size);
    second_last_names TEXT ARRAY DEFAULT generate_last_names_arr(size);
    birth_dates       DATE ARRAY DEFAULT generate_random_date_arr(size, starting_timeframe, ending_timeframe);
BEGIN
    FOR i IN 1..n
        LOOP
            INSERT INTO residence.person (rut, first_names, last_name, second_last_name, birth_date, gender)
            VALUES (ruts[i], male_names[i], last_names[i], second_last_names[i], birth_dates[i], 1);
        END LOOP;
    FOR j IN 1..m
        LOOP
            INSERT INTO residence.person (rut, first_names, last_name, second_last_name, birth_date, gender)
            VALUES (ruts[n + j], female_names[j], last_names[n + j], second_last_names[n + j], birth_dates[n + j], 2);
        END LOOP;
END;
$$;

CREATE or replace PROCEDURE batch_insert_elders(n INTEGER)
    LANGUAGE plpgsql
AS
$$
BEGIN
    CALL batch_insert_people(n, 3, 6, 70, 90);
END;
$$;

CREATE or replace PROCEDURE batch_insert_responsibles(n INTEGER)
    LANGUAGE plpgsql
AS
$$
BEGIN
    CALL batch_insert_people(n, 10, 15, 30, 50);
END;
$$;

DELETE FROM residence.elder;
DELETE FROM residence.responsible;
DELETE FROM residence.person;

CALL batch_insert_responsibles(100);
CALL batch_insert_elders(100);

SELECT * FROM residence.person;