SET search_path = "residence";

DROP TRIGGER IF EXISTS update_medication_prescription on medication_prescription;
DROP FUNCTION IF EXISTS insert_medication_administration();

CREATE OR REPLACE FUNCTION insert_medication_administration() RETURNS TRIGGER AS
$$
DECLARE
    lower_bound        INTEGER DEFAULT 8;
    upper_bound        INTEGER DEFAULT 20;
    starting_timestamp TIMESTAMP DEFAULT new.prescription_date::timestamp + make_interval(days => 1);
    next_timestamp     TIMESTAMP DEFAULT starting_timestamp;
    hour_interval      INTEGER;
    n                  INTEGER;
BEGIN
    next_timestamp = next_timestamp + make_interval(hours => lower_bound);
    SELECT(extract(DAY FROM new.end_date::timestamp - starting_timestamp)) INTO n;
    IF new.quantity = 1 THEN
        FOR i in 1..n
            LOOP
                INSERT INTO medication_administration
                VALUES (new.rut, new.medication_name, next_timestamp, null, 0, null)
                ON CONFLICT DO NOTHING;
                next_timestamp = next_timestamp + make_interval(days => 1);
            END LOOP;
    ELSE
        FOR i in 1..n
            LOOP
                hour_interval = ((upper_bound - lower_bound) / (new.quantity - 1))::int;
                FOR j in 1..(new.quantity - 1)
                    LOOP
                        INSERT INTO medication_administration
                        VALUES (new.rut, new.medication_name, next_timestamp, null, 0, null)
                        ON CONFLICT DO NOTHING;
                        next_timestamp = next_timestamp + make_interval(hours => hour_interval);
                    END LOOP;
                INSERT INTO medication_administration
                VALUES (new.rut, new.medication_name, next_timestamp, null, 0, null)
                ON CONFLICT DO NOTHING;
                next_timestamp = next_timestamp + make_interval(hours => 12);
            END LOOP;
    END IF;
    return new;
END
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_medication_prescription
    AFTER INSERT
    ON medication_prescription
    FOR EACH ROW
EXECUTE PROCEDURE insert_medication_administration();