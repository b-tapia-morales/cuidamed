SET search_path = "residence";

DROP TRIGGER IF EXISTS update_medication_prescription on medication_prescription;
DROP FUNCTION IF EXISTS insert_medication_administration();

/*
CREATE OR REPLACE FUNCTION insert_medication_administration_alt() RETURNS TRIGGER AS
$$
DECLARE
    carer_rut    TEXT ARRAY DEFAULT array(SELECT rut
                                          FROM carer);
    n            INTEGER DEFAULT cardinality(carer_rut);
    date_current TIMESTAMP;
    TIMES        TIME ARRAY DEFAULT ARRAY ['12:00:00','06:00:00','04:00:00','03:00:00', '02:20:00'];
BEGIN
    date_current = CURRENT_TIMESTAMP;
    FOR i in 1..new.quantity
        LOOP
            IF i = 1 THEN
                INSERT INTO medication_administration
                VALUES (new.rut, new.medication_name, date_trunc('second', date_current::timestamp), null, 0,
                        carer_rut[floor(random() * (n - 1) + 1)]);
            ELSE
                INSERT INTO medication_administration
                VALUES (new.rut, new.medication_name, date_trunc('second', date_current::timestamp), null, 0,
                        carer_rut[floor(random() * (n - 1) + 1)]);
            END IF;
            date_current = date_current + TIMES[new.quantity - 1];
        END LOOP;
    return new;
END
$$ LANGUAGE plpgsql;
*/

CREATE OR REPLACE FUNCTION insert_medication_administration() RETURNS TRIGGER AS
$$
DECLARE
    lower_bound        INTEGER DEFAULT 8;
    upper_bound        INTEGER DEFAULT 20;
    starting_timestamp TIMESTAMP DEFAULT new.prescription_date::timestamp + make_interval(days => 1);
    next_timestamp     TIMESTAMP DEFAULT starting_timestamp;
    hour_interval      INTEGER DEFAULT ((upper_bound - lower_bound) / new.quantity)::int;
    n                  INTEGER;
BEGIN
    next_timestamp = next_timestamp + make_interval(hours => lower_bound);
    SELECT(extract(DAY FROM new.end_date::timestamp - starting_timestamp)) INTO n;
    FOR i in 1..n
        LOOP
            IF new.quantity = 1 THEN
                INSERT INTO medication_administration (elder_rut, medication_name, estimated_datetime,
                                                       real_datetime, status, carer_rut)
                VALUES (new.rut, new.medication_name, date_trunc('second', next_timestamp::timestamp), null, 0, null)
                ON CONFLICT DO NOTHING;
                next_timestamp = next_timestamp + make_interval(days => 1);
            ELSE
                FOR j in 1..new.quantity
                    LOOP
                        INSERT INTO medication_administration (elder_rut, medication_name, estimated_datetime,
                                                               real_datetime, status, carer_rut)
                        VALUES (new.rut, new.medication_name, date_trunc('second', next_timestamp::timestamp), null, 0, null)
                        ON CONFLICT DO NOTHING;
                        next_timestamp = next_timestamp + make_interval(hours => hour_interval);
                    END LOOP;
                next_timestamp = next_timestamp + make_interval(hours => 12);
            END IF;
        END LOOP;
    return new;
END
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_medication_prescription
    AFTER INSERT
    ON medication_prescription
    FOR EACH ROW
EXECUTE PROCEDURE insert_medication_administration();