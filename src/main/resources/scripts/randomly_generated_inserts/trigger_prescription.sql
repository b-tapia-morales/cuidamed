SET search_path = "residence";

DROP TRIGGER IF EXISTS update_medication_prescription on residence.medication_prescription;
DROP FUNCTION IF EXISTS insert_medication_administration();

CREATE OR REPLACE FUNCTION insert_medication_administration() RETURNS TRIGGER AS
$$
DECLARE
    carer_rut TEXT ARRAY DEFAULT array(SELECT rut
                                       FROM carer);
    n         INTEGER DEFAULT cardinality(carer_rut);
BEGIN
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
