CREATE OR REPLACE FUNCTION generate_rut(n INTEGER) RETURNS TEXT AS
$func$
DECLARE
    last_digit_arr CHAR ARRAY DEFAULT ['1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'K'];
BEGIN
    RETURN '';
END
$func$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generate_last_digit(rut TEXT) RETURNS CHAR AS
$func$
DECLARE
    n     INTEGER DEFAULT length(rut);
    digit TEXT;
    sum   INTEGER DEFAULT 0;
    count INTEGER DEFAULT 2;
BEGIN
    FOR i IN N..2
        LOOP
            digit = substr(rut, i, i - 1)::int;
            sum = sum + digit * count;
            count = count + 1;
            IF (count == 8) THEN
                count = 2;
            END IF;
        END LOOP;
    RETURN (11 - sum % 11)::char;
END
$func$ LANGUAGE plpgsql;
