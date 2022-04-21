SET search_path = "residence";

DROP TRIGGER IF EXISTS update_prescription ON residence.prescription;
DROP FUNCTION IF EXISTS insert_medication_prescription();


CREATE OR REPLACE FUNCTION insert_medication_prescription() RETURNS TRIGGER AS
$$
DECLARE
    medication  TEXT ARRAY DEFAULT array(SELECT medication_name FROM residence.medication);
    n  INTEGER DEFAULT cardinality(medication);
BEGIN
    INSERT INTO residence.medication_prescription VALUES (new.rut,new.disease_name,new.prescription_date);

    RETURN new;
end;

$$ LANGUAGE plpgsql;

CREATE TRIGGER update_prescription
    AFTER INSERT
    ON residence.prescription
    FOR EACH ROW
EXECUTE PROCEDURE insert_medication_prescription();