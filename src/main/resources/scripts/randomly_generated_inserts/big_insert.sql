SET search_path = "residence";

DROP PROCEDURE IF EXISTS batch_insert(n INTEGER);

CREATE OR REPLACE PROCEDURE batch_insert(n INTEGER)
    LANGUAGE plpgsql AS
$func$
BEGIN
    TRUNCATE person CASCADE;
    TRUNCATE disease CASCADE;
    TRUNCATE medication CASCADE;
    CALL batch_insert_people(n);
    CALL insert_random_disease();
    CALL insert_random_medication();
    CALL insert_random_medical_records();
    CALL insert_random_routine_checkups();
    CALL insert_random_prescription();
END;
$func$;

CALL batch_insert(100);

DROP INDEX IF EXISTS extract_hour_hash;
CREATE INDEX extract_hour_hash ON medication_administration USING hash (extract(HOUR FROM estimated_datetime));

DROP INDEX IF EXISTS extract_hour_btree;
CREATE INDEX extract_hour_btree ON medication_administration USING btree (extract(HOUR FROM estimated_datetime));

SELECT count(*) FROM medication_administration;

SELECT * FROM person p NATURAL JOIN elder e RIGHT JOIN medication_administration mr ON e.rut = mr.elder_rut;

SELECT * FROM medication_administration;

SELECT * FROM medication_administration WHERE extract(HOUR FROM estimated_datetime) NOT IN (8, 11, 12, 14, 16, 17, 20);

