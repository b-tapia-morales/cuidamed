SET search_path = "residence";


DROP TRIGGER IF EXISTS update_medication_prescription on residence.medication_prescription;
DROP FUNCTION IF EXISTS insert_medication_administration();

CREATE OR REPLACE FUNCTION insert_medication_administration() RETURNS TRIGGER AS
$$
DECLARE
    carer_rut    TEXT ARRAY DEFAULT array(SELECT rut
                                          FROM residence.carer);
    n            INTEGER DEFAULT cardinality(carer_rut);
    date_current TIMESTAMP;
    TIMES       TIME ARRAY DEFAULT ARRAY['12:00:00','06:00:00','04:00:00','03:00:00', '02:20:00'];
BEGIN
    date_current = CURRENT_TIMESTAMP;
    FOR i in 1..new.frequency
        LOOP
            IF i = 1 THEN
                INSERT INTO residence.medication_administration
                VALUES (new.rut, new.medication_name, date_current, null, 0, carer_rut[floor(random() * (n - 1) + 1)]);
            ELSE
                INSERT INTO residence.medication_administration
                VALUES (new.rut, new.medication_name, date_current, null, 0, carer_rut[floor(random() * (n - 1) + 1)]);
            END IF;
            date_current = date_current + TIMES[new.frequency-1];
        END LOOP;
    return new;
END
$$ LANGUAGE plpgsql;


CREATE TRIGGER update_medication_prescription
    AFTER INSERT
    ON residence.medication_prescription
    FOR EACH ROW
execute procedure insert_medication_administration();

SELECT *
from residence.medication_administration;
SELECT *
from residence.medication_prescription;
SELECT *
from residence.medication;
SELECT *
from residence.carer;

DELETE
FROM residence.medication_administration;
DELETE
FROM residence.medication_prescription;

DELETE
FROM residence.medication;