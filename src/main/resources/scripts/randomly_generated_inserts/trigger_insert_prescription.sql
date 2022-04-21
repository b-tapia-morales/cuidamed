SET search_path = "residence";

DROP TRIGGER IF EXISTS update_prescription ON residence.prescription;
DROP FUNCTION IF EXISTS insert_medication_prescription();


CREATE OR REPLACE FUNCTION insert_medication_prescription() RETURNS TRIGGER AS
$$
DECLARE
    medication        TEXT ARRAY DEFAULT array(SELECT medication_name
                                               FROM residence.medication);
    n                 INTEGER DEFAULT cardinality(medication);
    cant_medication INTEGER;
BEGIN
    cant_medication = floor(random() * 3 + 1);
    FOR i in 1..cant_medication
        LOOP
            INSERT INTO residence.medication_prescription
            VALUES (new.rut, new.disease_name, new.prescription_date, medication[floor(random() * (n - 1) + 1)],
                new.prescription_date, null, floor(random() * (5 - 2 + 1) + 2)::int, floor(random() * 5 + 1));
        END LOOP;
    RETURN new;
end;

$$ LANGUAGE plpgsql;

CREATE TRIGGER update_prescription
    AFTER INSERT
    ON residence.prescription
    FOR EACH ROW
EXECUTE PROCEDURE insert_medication_prescription();

DELETE FROM residence.prescription;

SELECT * from residence.prescription;
SELECT * FROM residence.medication_prescription;
SELECT * FROM residence.medication_administration;
