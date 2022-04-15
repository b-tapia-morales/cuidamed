
SET search_path = "residence";

DROP FUNCTION IF EXISTS generate_random_date(INTEGER, INTEGER);
DROP FUNCTION IF EXISTS generate_random_date_arr(INTEGER, INTEGER, INTEGER);

CREATE OR REPLACE FUNCTION generate_random_date(starting_timeframe INTEGER, ending_timeframe INTEGER) RETURNS DATE AS
$func$
DECLARE
    starting_interval INTERVAL DEFAULT make_interval(years => starting_timeframe);
    ending_interval   INTERVAL DEFAULT make_interval(years => (ending_timeframe - starting_timeframe));
BEGIN
    RETURN now() + (random() * (now() - ending_interval - now())) - starting_interval;
END;
$func$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generate_random_date_arr(n INTEGER, starting_timeframe INTEGER, ending_timeframe INTEGER) RETURNS DATE ARRAY AS
$func$
DECLARE
    date_arr      DATE ARRAY;
BEGIN
    FOR i IN 1..N
        LOOP
            date_arr = array_append(date_arr, generate_random_date(starting_timeframe, ending_timeframe));
        END LOOP;
    RETURN date_arr;
END;
$func$ LANGUAGE plpgsql;

SELECT *
FROM unnest(generate_random_date_arr(100, 30, 35)) AS "date";