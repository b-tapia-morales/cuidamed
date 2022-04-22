SET search_path = "residence";

DROP TRIGGER IF EXISTS update_prescription ON prescription;
DROP FUNCTION IF EXISTS insert_medication_prescription();

CREATE OR REPLACE FUNCTION insert_medication_prescription() RETURNS TRIGGER AS
$$
DECLARE
    medication         TEXT ARRAY DEFAULT array(SELECT medication_name
                                                FROM medication);
    chronic            BOOLEAN;
    n                  INTEGER DEFAULT cardinality(medication);
    medications_amount INTEGER;
BEGIN
    medications_amount = floor(random() * 3 + 1);
    FOR i in 1..medications_amount
        LOOP
            chronic = (SELECT is_chronic FROM disease WHERE disease_name = new.disease_name);
            IF (chronic = TRUE) THEN
                INSERT INTO medication_prescription (rut, disease_name, prescription_date, medication_name, start_date,
                                                     end_date, quantity)
                VALUES (new.rut, new.disease_name, new.prescription_date,
                        medication[floor(random() * n + 1)::int], new.prescription_date,
                        (new.prescription_date + make_interval(months => 1))::date,
                        floor(random() * 4 + 1)::int)
                ON CONFLICT DO NOTHING;
            ELSE
                INSERT INTO medication_prescription (rut, disease_name, prescription_date, medication_name, start_date,
                                                     end_date, quantity)
                VALUES (new.rut, new.disease_name, new.prescription_date,
                        medication[floor(random() * n + 1)::int], new.prescription_date,
                        (new.prescription_date + make_interval(days => (floor(random() * 7 + 7)::int)))::date,
                        floor(random() * 4 + 1)::int)
                ON CONFLICT DO NOTHING;
            end if;
        END LOOP;
    RETURN new;
end;

$$ LANGUAGE plpgsql;

CREATE TRIGGER update_prescription
    AFTER INSERT
    ON prescription
    FOR EACH ROW
EXECUTE PROCEDURE insert_medication_prescription();
