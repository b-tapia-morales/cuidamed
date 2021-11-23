CREATE SCHEMA IF NOT EXISTS residence;

DROP TABLE IF EXISTS residence.medication_administration;
DROP TABLE IF EXISTS residence.medication_prescription;
DROP TABLE IF EXISTS residence.medication;
DROP TABLE IF EXISTS residence.prescription;
DROP TABLE IF EXISTS residence.sick_elderly;
DROP TABLE IF EXISTS residence.disease;

DROP TABLE IF EXISTS residence.routine_checkup;
DROP TABLE IF EXISTS residence.allergy;
DROP TABLE IF EXISTS residence.surgical_intervention;
DROP TABLE IF EXISTS residence.medical_record;

DROP TABLE IF EXISTS residence.address;
DROP TABLE IF EXISTS residence.carer;
DROP TABLE IF EXISTS residence.elder;
DROP TABLE IF EXISTS residence.responsible;
DROP TABLE IF EXISTS residence.person;

DROP TABLE IF EXISTS residence.commune;
DROP TABLE IF EXISTS residence.province;
DROP TABLE IF EXISTS residence.region;

CREATE TABLE IF NOT EXISTS residence.region
(
    id           SMALLSERIAL,
    region_name  VARCHAR(64) UNIQUE NOT NULL,
    abbreviation VARCHAR(4) UNIQUE  NOT NULL,
    capital      VARCHAR(64) UNIQUE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS residence.province
(
    id            SMALLSERIAL,
    province_name VARCHAR(64) UNIQUE NOT NULL,
    region_id     SMALLINT           NOT NULL,
    FOREIGN KEY (region_id) references residence.region (id),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS residence.commune
(
    id           SMALLSERIAL,
    commune_name VARCHAR(64) UNIQUE NOT NULL,
    province_id  SMALLINT           NOT NULL,
    FOREIGN KEY (province_id) references residence.province (id),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS residence.person
(
    rut              VARCHAR(16)  NOT NULL,
    first_names      VARCHAR(128) NOT NULL,
    last_name        VARCHAR(64)  NOT NULL,
    second_last_name VARCHAR(64)  NOT NULL,
    birth_date       DATE         NOT NULL,
    gender           SMALLINT     NOT NULL,
    PRIMARY KEY (rut)
);

CREATE TABLE IF NOT EXISTS residence.responsible
(
    rut          VARCHAR(16) NOT NULL,
    mobile_phone INT         NOT NULL,
    FOREIGN KEY (rut) REFERENCES residence.person (rut),
    PRIMARY KEY (rut)
);

CREATE TABLE IF NOT EXISTS residence.carer
(
    rut          VARCHAR(16) NOT NULL,
    mobile_phone INT         NOT NULL,
    hire_date    DATE        NOT NULL,
    FOREIGN KEY (rut) REFERENCES residence.person (rut),
    PRIMARY KEY (rut)
);

CREATE TABLE IF NOT EXISTS residence.elder
(
    rut             VARCHAR(16) NOT NULL,
    is_active       BOOLEAN     NOT NULL,
    admission_date  DATE        NOT NULL,
    responsible_rut VARCHAR(16) NOT NULL,
    FOREIGN KEY (rut) REFERENCES residence.person (rut),
    FOREIGN KEY (responsible_rut) REFERENCES residence.responsible (rut),
    PRIMARY KEY (rut)
);

CREATE TABLE IF NOT EXISTS residence.address
(
    commune_id  SMALLSERIAL  NOT NULL,
    street      VARCHAR(128) NOT NULL,
    number      SMALLINT     NOT NULL,
    postal_code INT,
    fixed_phone INT,
    rut         VARCHAR(16)  NOT NULL,
    FOREIGN KEY (commune_id) REFERENCES residence.commune (id),
    FOREIGN KEY (rut) REFERENCES residence.person (rut),
    primary key (commune_id, street, number, rut)
);

CREATE TABLE IF NOT EXISTS residence.medical_record
(
    rut               VARCHAR(16) NOT NULL,
    blood_type        SMALLINT    NOT NULL,
    healthcare_system SMALLINT    NOT NULL,
    FOREIGN KEY (rut) REFERENCES residence.elder (rut),
    PRIMARY KEY (rut)
);

CREATE TABLE IF NOT EXISTS residence.routine_checkup
(
    rut                VARCHAR(16) NOT NULL,
    checkup_date       DATE        NOT NULL,
    height             REAL        NOT NULL,
    weight             REAL        NOT NULL,
    bmi                REAL        NOT NULL,
    heart_rate         SMALLINT    NOT NULL,
    diastolic_pressure REAL        NOT NULL,
    systolic_pressure  REAL        NOT NULL,
    body_temperature   REAL        NOT NULL,
    FOREIGN KEY (rut) REFERENCES residence.medical_record (rut),
    PRIMARY KEY (rut, checkup_date)
);

CREATE TABLE IF NOT EXISTS residence.allergy
(
    rut          VARCHAR(16) NOT NULL,
    allergy_type SMALLINT    NOT NULL,
    allergy_name TEXT        NOT NULL,
    FOREIGN KEY (rut) REFERENCES residence.medical_record (rut),
    PRIMARY KEY (rut, allergy_name)
);

CREATE TABLE IF NOT EXISTS residence.surgical_intervention
(
    rut               VARCHAR(16) NOT NULL,
    intervention_date DATE        NOT NULL,
    hospital          VARCHAR(32) NOT NULL,
    severity          SMALLINT    NOT NULL,
    description       TEXT        NOT NULL,
    FOREIGN KEY (rut) REFERENCES residence.medical_record (rut),
    PRIMARY KEY (rut, intervention_date)
);

CREATE TABLE IF NOT EXISTS residence.disease
(
    disease_name VARCHAR(32) NOT NULL,
    disease_type SMALLINT    NOT NULL,
    is_chronic   BOOLEAN     NOT NULL,
    PRIMARY KEY (disease_name)
);

CREATE TABLE IF NOT EXISTS residence.sick_elderly
(
    rut            VARCHAR(16) NOT NULL,
    disease_name   VARCHAR(32) NOT NULL,
    diagnosis_date DATE        NOT NULL,
    FOREIGN KEY (rut) REFERENCES residence.elder (rut),
    FOREIGN KEY (disease_name) REFERENCES residence.disease (disease_name),
    PRIMARY KEY (rut, disease_name, diagnosis_date)
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
    rut               VARCHAR(16) NOT NULL,
    disease_name      VARCHAR(32) NOT NULL,
    prescription_date DATE        NOT NULL,
    description       TEXT        NOT NULL,
    FOREIGN KEY (rut) REFERENCES residence.elder (rut),
    FOREIGN KEY (disease_name) REFERENCES residence.disease (disease_name),
    PRIMARY KEY (rut, disease_name, prescription_date)
);

CREATE TABLE IF NOT EXISTS residence.medication_prescription
(
    rut               VARCHAR(16) NOT NULL,
    disease_name      VARCHAR(32) NOT NULL,
    prescription_date DATE        NOT NULL,
    medication_name   VARCHAR(64) NOT NULL,
    start_date        DATE        NOT NULL,
    end_date          DATE,
    frequency         SMALLINT    NOT NULL,
    quantity          SMALLINT    NOT NULL,
    FOREIGN KEY (rut, disease_name, prescription_date) REFERENCES residence.prescription (rut, disease_name, prescription_date),
    FOREIGN KEY (medication_name) REFERENCES residence.medication (medication_name),
    PRIMARY KEY (rut, disease_name, prescription_date, medication_name)
);

CREATE TABLE IF NOT EXISTS residence.medication_administration
(
    elder_rut          VARCHAR(16) NOT NULL,
    medication_name    VARCHAR(64) NOT NULL,
    estimated_datetime TIMESTAMP   NOT NULL,
    real_datetime      TIMESTAMP,
    status             SMALLINT    NOT NULL,
    carer_rut          VARCHAR(16),
    FOREIGN KEY (carer_rut) REFERENCES residence.carer (rut),
    FOREIGN KEY (elder_rut) REFERENCES residence.elder (rut),
    FOREIGN KEY (medication_name) REFERENCES residence.medication (medication_name),
    PRIMARY KEY (elder_rut, medication_name, estimated_datetime)
);
