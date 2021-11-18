CREATE EXTENSION unaccent;

CREATE FUNCTION remove_accents(text) RETURNS text
    LANGUAGE sql
    IMMUTABLE STRICT AS
'SELECT unaccent(lower($1))';

CREATE FUNCTION extract_days(date) returns INT
    LANGUAGE sql
    IMMUTABLE STRICT AS
'SELECT extract(DAY FROM age(CURRENT_DATE, $1))'