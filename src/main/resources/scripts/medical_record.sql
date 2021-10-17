CREATE TABLE IF NOT EXISTS residence.medical_record
(
    elder_rut         VARCHAR(16) PRIMARY KEY REFERENCES residence.elder (rut),
    blood_type        SMALLINT NOT NULL,
    healthcare_system SMALLINT NOT NULL
);

CREATE TABLE IF NOT EXISTS residence.routine_checkup
(
    elder_rut          VARCHAR(16) NOT NULL REFERENCES residence.elder (rut),
    date               DATE        NOT NULL,
    height             REAL        NOT NULL,
    weight             REAL        NOT NULL,
    bmi                REAL        NOT NULL,
    heart_rate         SMALLINT    NOT NULL,
    diastolic_pressure REAL        NOT NULL,
    systolic_pressure  REAL        NOT NULL,
    body_temperature   REAL        NOT NULL,
    PRIMARY KEY (elder_rut, date)
);

CREATE TABLE IF NOT EXISTS residence.allergy
(
    elder_rut    VARCHAR(16) NOT NULL REFERENCES residence.elder (rut),
    allergy_name VARCHAR(24) NOT NULL,
    type         SMALLINT    NOT NULL,
    PRIMARY KEY (elder_rut, allergy_name)
);

CREATE TABLE IF NOT EXISTS residence.surgical_intervention
(
    elder_rut           VARCHAR(16) NOT NULL REFERENCES residence.elder (rut),
    date        DATE        NOT NULL,
    hospital            VARCHAR(20) NOT NULL,
    severity      SMALLINT    NOT NULL,
    description VARCHAR(64) NOT NULL,
    PRIMARY KEY (elder_rut, surgery_date)
);