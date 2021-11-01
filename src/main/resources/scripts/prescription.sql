DROP TABLE IF EXISTS residence.medication_prescription;
DROP TABLE IF EXISTS residence.medication;
DROP TABLE IF EXISTS residence.prescription;
DROP TABLE IF EXISTS residence.elder_suffers_disease;
DROP TABLE IF EXISTS residence.disease;

CREATE TABLE IF NOT EXISTS residence.disease
(
    disease_name VARCHAR(32) NOT NULL,
    disease_type SMALLINT    NOT NULL,
    is_chronic   BOOLEAN     NOT NULL,
    PRIMARY KEY (disease_name)
);

CREATE TABLE IF NOT EXISTS residence.elder_suffers_disease
(
    elder_rut      VARCHAR(16) NOT NULL,
    disease_name   VARCHAR(32) NOT NULL,
    diagnosis_date DATE        NOT NULL,
    FOREIGN KEY (elder_rut) REFERENCES residence.elder (rut),
    FOREIGN KEY (disease_name) REFERENCES residence.disease (disease_name),
    PRIMARY KEY (elder_rut, disease_name, diagnosis_date)
);

CREATE TABLE IF NOT EXISTS residence.medication
(
    medication_name      VARCHAR(64) NOT NULL,
    administration_route SMALLINT    NOT NULL,
    pharmaceutical_form  SMALLINT    NOT NULL,
    measure_unit         SMALLINT    NOT NULL,
    PRIMARY KEY (medication_name)
);

CREATE TABLE IF NOT EXISTS residence.prescription
(
    elder_rut         VARCHAR(16) NOT NULL,
    disease_name      VARCHAR(32) NOT NULL,
    prescription_date DATE        NOT NULL,
    description       TEXT        NOT NULL,
    FOREIGN KEY (elder_rut) REFERENCES residence.elder (rut),
    FOREIGN KEY (disease_name) REFERENCES residence.disease (disease_name),
    PRIMARY KEY (elder_rut, disease_name, prescription_date)
);

CREATE TABLE IF NOT EXISTS residence.medication_prescription
(
    elder_rut         VARCHAR(16) NOT NULL,
    disease_name      VARCHAR(32) NOT NULL,
    prescription_date DATE        NOT NULL,
    medication_name   VARCHAR(64) NOT NULL,
    start_date        DATE        NOT NULL,
    end_date          DATE,
    FOREIGN KEY (elder_rut, disease_name, prescription_date) REFERENCES residence.prescription (elder_rut, disease_name, prescription_date),
    FOREIGN KEY (medication_name) REFERENCES residence.medication (medication_name),
    PRIMARY KEY (elder_rut, disease_name, prescription_date, medication_name)
);

CREATE TABLE IF NOT EXISTS residence.medication_administration
(
    carer_rut          VARCHAR(16) NOT NULL,
    elder_rut          VARCHAR(16) NOT NULL,
    medication_name    VARCHAR(64) NOT NULL,
    status             SMALLINT    NOT NULL,
    frequency          SMALLINT    NOT NULL,
    quantity           SMALLINT    NOT NULL,
    estimated_datetime TIMESTAMP   NOT NULL,
    real_datetime      TIMESTAMP,
    FOREIGN KEY (carer_rut) REFERENCES residence.carer (rut),
    FOREIGN KEY (elder_rut) REFERENCES residence.elder (rut),
    FOREIGN KEY (medication_name) REFERENCES residence.medication (medication_name),
    PRIMARY KEY (carer_rut, elder_rut, medication_name, estimated_datetime)
);
