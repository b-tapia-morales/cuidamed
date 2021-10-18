DROP TABLE IF EXISTS residence.medication_prescription;
DROP TABLE IF EXISTS residence.medication;
DROP TABLE IF EXISTS residence.prescription;
DROP TABLE IF EXISTS residence.elder_suffers_disease;
DROP TABLE IF EXISTS residence.disease;

CREATE TABLE IF NOT EXISTS residence.disease
(
    disease_name VARCHAR(32) PRIMARY KEY,
    disease_type SMALLINT NOT NULL,
    is_chronic   BOOLEAN
);

CREATE TABLE IF NOT EXISTS residence.elder_suffers_disease
(
    elder_rut      VARCHAR(16) REFERENCES residence.person (rut),
    disease_name   VARCHAR(32) REFERENCES residence.disease (disease_name),
    diagnosis_date DATE,
    PRIMARY KEY (elder_rut, disease_name, diagnosis_date)
);

CREATE TABLE IF NOT EXISTS residence.medication
(
    medication_name      VARCHAR(64) PRIMARY KEY,
    administration_route SMALLINT NOT NULL,
    pharmaceutical_form  SMALLINT NOT NULL,
    measure_unit         SMALLINT NOT NULL
);

CREATE TABLE IF NOT EXISTS residence.prescription
(
    elder_rut         VARCHAR(16) REFERENCES residence.elder (rut),
    disease_name      VARCHAR(32) REFERENCES residence.disease (disease_name),
    prescription_date DATE,
    description       TEXT NOT NULL,
    PRIMARY KEY (elder_rut, disease_name, prescription_date)
);

CREATE TABLE IF NOT EXISTS residence.medication_prescription
(
    elder_rut         VARCHAR(16) REFERENCES residence.prescription (elder_rut),
    prescription_date DATE REFERENCES residence.prescription (prescription_date),
    medication_name   VARCHAR(64) REFERENCES residence.medication (medication_name),
    start_date        DATE NOT NULL,
    end_date          DATE,
    PRIMARY KEY (elder_rut, prescription_date, medication_name)
);
