CREATE TABLE IF NOT EXISTS residence.medical_record
(
    elder_rut   VARCHAR(16) PRIMARY KEY REFERENCES residence.elder (rut),
    blood_type  VARCHAR(4) NOT NULL,
    healthcare_system   VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS residence.routine_checkup
(
    elder_rut           VARCHAR(16) NOT NULL REFERENCES residence.elder (rut),
    checkup_date                DATE    NOT NULL,
    height              FLOAT   NOT NULL,
    weight              FLOAT   NOT NULL,
    BMI                 FLOAT   NOT NULL,
    heart_rate          INT     NOT NULL,
    diastolic_pressure  FLOAT   NOT NULL,
    systolic_pressure   FLOAT   NOT NULL,
    body_temperature    FLOAT   NOT NULL,
    PRIMARY KEY (elder_rut, checkup_date)
);

CREATE TABLE IF NOT EXISTS residence.allergy
(

);