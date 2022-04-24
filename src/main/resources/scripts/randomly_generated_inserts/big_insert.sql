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

DROP INDEX IF EXISTS bmi_btree;
DROP INDEX IF EXISTS heart_rate_btree;

SELECT count(*) AS medication_administration_count
FROM medication_administration;

SELECT *
FROM person p
         INNER JOIN elder e USING (rut)
         RIGHT OUTER JOIN medication_administration mr ON rut = mr.elder_rut;

SELECT count(*) AS routine_checkup_count
FROM routine_checkup;

SELECT *
FROM person p
         INNER JOIN elder e USING (rut)
         INNER JOIN routine_checkup rc USING (rut);

EXPLAIN ANALYZE
SELECT *
FROM person p
         INNER JOIN elder e USING (rut)
         INNER JOIN routine_checkup rc USING (rut)
WHERE bmi <= 18.00
   OR bmi >= 30.00;

CREATE INDEX bmi_btree ON routine_checkup USING btree (bmi);

EXPLAIN ANALYZE
SELECT *
FROM person p
         INNER JOIN elder e USING (rut)
         INNER JOIN routine_checkup rc USING (rut)
WHERE bmi <= 18.00
   OR bmi >= 30.00;

EXPLAIN analyze
SELECT *
FROM routine_checkup
WHERE heart_rate > 100
   or heart_rate < 60;

--CREATE INDEX sys_press_btree ON routine_checkup USING btree (systolic_pressure);
CREATE INDEX heart_rate_btree ON routine_checkup USING btree (heart_rate);

EXPLAIN analyze
SELECT *
FROM routine_checkup
WHERE heart_rate > 100
   or heart_rate < 60;
