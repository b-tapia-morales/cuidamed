DROP TABLE IF EXISTS residence.routine_checkup;
DROP TABLE IF EXISTS residence.allergy;
DROP TABLE IF EXISTS residence.surgical_intervention;
DROP TABLE IF EXISTS residence.medical_record;

CREATE TABLE IF NOT EXISTS residence.medical_record
(
    elder_rut         VARCHAR(16) NOT NULL,
    blood_type        SMALLINT    NOT NULL,
    healthcare_system SMALLINT    NOT NULL,
    FOREIGN KEY (elder_rut) REFERENCES residence.elder (rut),
    PRIMARY KEY (elder_rut)
);

CREATE TABLE IF NOT EXISTS residence.routine_checkup
(
    elder_rut          VARCHAR(16) NOT NULL,
    checkup_date       DATE        NOT NULL,
    height             REAL        NOT NULL,
    weight             REAL        NOT NULL,
    bmi                REAL        NOT NULL,
    heart_rate         SMALLINT    NOT NULL,
    diastolic_pressure REAL        NOT NULL,
    systolic_pressure  REAL        NOT NULL,
    body_temperature   REAL        NOT NULL,
    FOREIGN KEY (elder_rut) REFERENCES residence.elder (rut),
    PRIMARY KEY (elder_rut, checkup_date)
);

CREATE TABLE IF NOT EXISTS residence.allergy
(
    elder_rut    VARCHAR(16) NOT NULL,
    allergy_type SMALLINT    NOT NULL,
    allergy_name TEXT        NOT NULL,
    FOREIGN KEY (elder_rut) REFERENCES residence.elder (rut),
    PRIMARY KEY (elder_rut, allergy_name)
);

CREATE TABLE IF NOT EXISTS residence.surgical_intervention
(
    elder_rut         VARCHAR(16) NOT NULL,
    intervention_date DATE        NOT NULL,
    hospital          VARCHAR(32) NOT NULL,
    severity          SMALLINT    NOT NULL,
    description       TEXT        NOT NULL,
    FOREIGN KEY (elder_rut) REFERENCES residence.elder (rut),
    PRIMARY KEY (elder_rut, intervention_date)
);