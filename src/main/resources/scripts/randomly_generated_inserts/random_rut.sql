CREATE OR REPLACE FUNCTION generate_rut(l INTEGER, u INTEGER)
    RETURNS TEXT AS
$func$
DECLARE
    rut        TEXT;
    multiplier INTEGER DEFAULT 1000000;
    lower      INTEGER DEFAULT l * multiplier;
    upper      INTEGER DEFAULT u * multiplier;
BEGIN
    IF (l < 1 OR u > 99) THEN
        RAISE EXCEPTION $$Lower and upper boundary values must be greater than % and
        lesser than % respectively (values given: [%, %])$$, 1, 99, l, u;
    end if;
    rut = floor(random() * (upper - lower + 1) + lower)::int::text;
    RETURN concat(rut, '-', generate_last_digit(rut));
END
$func$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generate_last_digit(rut TEXT) RETURNS CHARACTER AS
$func$
DECLARE
    reverse     TEXT DEFAULT reverse(rut);
    n           INTEGER DEFAULT length(rut);
    digit       INTEGER;
    sum         INTEGER DEFAULT 0;
    count       INTEGER DEFAULT 2;
    subtraction INTEGER;
    last_digit  CHARACTER;
BEGIN
    FOR i IN 1..N
        LOOP
            digit = substr(reverse, i, 1)::int;
            sum = sum + digit * count;
            count = count + 1;
            IF (count = 8) THEN
                count = 2;
            END IF;
        END LOOP;
    subtraction = 11 - mod(sum, 11);
    CASE (subtraction)
        WHEN 11 THEN last_digit = '0';
        WHEN 10 THEN last_digit = 'K';
        ELSE last_digit = subtraction::char;
        END CASE;
    RETURN last_digit;
END
$func$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generate_rut_arr(n INTEGER, l INTEGER, u INTEGER) RETURNS TEXT ARRAY AS
$func$
DECLARE
    arr TEXT ARRAY;
BEGIN
    IF (l < 1 OR u > 99) THEN
        RAISE EXCEPTION $$Lower and upper boundary values must be greater than % and
        lesser than % respectively (values given: [%, %])$$, 1, 99, l, u;
    END IF;
    FOR i IN 1..n
        LOOP
            arr = array_append(arr, generate_rut(l, u));
        END LOOP;
    RETURN arr;
END
$func$ LANGUAGE plpgsql;

SELECT *
FROM generate_rut(-1, 23);

SELECT * FROM unnest(generate_rut_arr(100, 10, 16));
