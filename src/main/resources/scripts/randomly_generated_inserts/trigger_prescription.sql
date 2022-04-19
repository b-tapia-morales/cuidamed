SET search_path = "residence";

DROP TRIGGER IF EXISTS update_medication_prescription on residence.medication_prescription;
DROP FUNCTION IF EXISTS insert_medication_administration();

CREATE OR REPLACE FUNCTION insert_medication_administration() RETURNS TRIGGER AS
$$
DECLARE
    carer_rut TEXT ARRAY DEFAULT array(SELECT rut
                                       FROM residence.carer);
    n         INTEGER DEFAULT cardinality(carer_rut);
    date_current DATE DEFAULT CURRENT_DATE;
BEGIN
    IF new.frequency = 2 THEN
        FOR i in 1..n
            LOOP
                date_current = date_current + interval '9 hr';
                INSERT INTO residence.medication_administration
                VALUES (new.rut, new.medication_name, date_current, null, 0, carer_rut[floor(random() * (n-1) + 1)]);
            END LOOP;
    END IF;
    IF new.frequency
    INSERT INTO residence.medication_administration
    VALUES (new.rut, new.medication_name, CURRENT_TIMESTAMP(6)+ '10 hr', null, 0, carer_rut[floor(random() * (n-1) + 1)]);
    return new;
END
$$ LANGUAGE plpgsql;


CREATE TRIGGER update_medication_prescription
    AFTER INSERT
    ON residence.medication_prescription
    FOR EACH ROW
execute procedure insert_medication_administration();

SELECT * from residence.medication_administration;
SELECT * from residence.medication_prescription;
SELECT * from residence.medication;
SELECT * from residence.carer;

DELETE FROM residence.medication_administration;
DELETE FROM residence.medication_prescription;

DELETE FROM residence.medication;

SELECT current_date;