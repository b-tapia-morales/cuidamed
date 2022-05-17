SET search_path = "residence";

DROP PROCEDURE IF EXISTS insert_random_diagnostic();

CREATE OR REPLACE PROCEDURE insert_random_diagnostic()
    LANGUAGE plpgsql AS
$$
DECLARE
    ruts        TEXT ARRAY DEFAULT ARRAY(SELECT rut
                                         FROM elder);
    diseases    TEXT ARRAY DEFAULT ARRAY(SELECT disease_name
                                         FROM disease);
    k           INTEGER;
    n           INTEGER DEFAULT cardinality(ruts);
    m           INTEGER;
    random_date DATE DEFAULT current_date + make_interval(months => 1);
BEGIN
    FOR i in 1..n
        LOOP
            m = floor(random() * 3 + 1);
            FOR j in 1..m
                LOOP
                    k = floor(random() * cardinality(diseases) + 1);
                    random_date = current_date + random() * make_interval(months => 1);
                    INSERT INTO sick_elderly (rut, disease_name, diagnosis_date)
                    VALUES (ruts[i], diseases[k], random_date)
                    ON CONFLICT DO NOTHING;
                    INSERT INTO diagnostic (rut, disease_name, prescription_date, description)
                    VALUES (ruts[i], diseases[k], random_date, '')
                    ON CONFLICT DO NOTHING;
                END LOOP;
        END LOOP;
END;
$$;