CREATE OR REPLACE FUNCTION generate_rut(l INTEGER, u INTEGER)
    RETURNS TEXT AS
$func$
DECLARE
    rut_as_int INTEGER;
    lower      INTEGER DEFAULT l * 1000000;
    upper      INTEGER DEFAULT u * 1000000;
BEGIN
    rut_as_int = floor(random() * (upper - lower + 1) + lower)::int;
    RETURN concat(rut_as_int, '-', generate_last_digit(rut_as_int::text));
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

SELECT *
FROM generate_rut(17, 23);
