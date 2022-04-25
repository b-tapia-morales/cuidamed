SET search_path = "residence";

DROP PROCEDURE IF EXISTS batch_insert(INTEGER);

DROP INDEX IF EXISTS bmi_btree;
DROP INDEX IF EXISTS heart_rate_btree;

-- Se crea el procedimiento que se encarga de insertar generar todos los datos al azar.
CREATE OR REPLACE PROCEDURE batch_insert(n INTEGER)
    LANGUAGE plpgsql AS
$func$
BEGIN
    TRUNCATE disease CASCADE;
    TRUNCATE medication CASCADE;
    TRUNCATE person CASCADE;
    CALL insert_diseases();
    CALL insert_medications();
    CALL batch_insert_people(n);
    CALL insert_random_medical_records();
    CALL insert_random_routine_checkups();
    CALL insert_random_prescription();
END;
$func$;

CALL batch_insert(100);

-- ¿Cuántos registros de administraciones de medicamentos generó el procedimiento?
SELECT count(*) AS medication_administration_count
FROM medication_administration;

-- Muéstrelos por consola.
SELECT *
FROM person p
         INNER JOIN elder e USING (rut)
         RIGHT OUTER JOIN medication_administration mr ON rut = mr.elder_rut;

-- ¿Cuántos registros de chequeos rutinarios generó el procedimiento?
SELECT count(*) AS routine_checkup_count
FROM routine_checkup;

-- Muéstrelos por consola.
SELECT *
FROM person p
         INNER JOIN elder e USING (rut)
         INNER JOIN routine_checkup rc USING (rut);

-- Primera consulta, sin joins. El índice no ha sido creado aún.
EXPLAIN analyze
SELECT *
FROM routine_checkup
WHERE heart_rate > 100
   OR heart_rate < 60;

-- Se crea el índice en el atributo 'heart_rate'.
CREATE INDEX heart_rate_btree ON routine_checkup USING btree (heart_rate);

-- Se hace la misma consulta, pero esta vez con el índice creado.
EXPLAIN analyze
SELECT *
FROM routine_checkup
WHERE heart_rate > 100
   OR heart_rate < 60;

-- Segunda consulta, además utilizando joins. El índice no ha sido creado aún.
EXPLAIN ANALYZE
SELECT *
FROM person p
         INNER JOIN elder e USING (rut)
         INNER JOIN routine_checkup rc USING (rut)
WHERE bmi <= 18.00
   OR bmi >= 30.00;

-- Se crea el índice en el atributo 'bmi'.
CREATE INDEX bmi_btree ON routine_checkup USING btree (bmi);

-- Se hace la misma consulta, pero esta vez con el índice creado.
EXPLAIN ANALYZE
SELECT *
FROM person p
         INNER JOIN elder e USING (rut)
         INNER JOIN routine_checkup rc USING (rut)
WHERE bmi <= 18.00
   OR bmi >= 30.00;
