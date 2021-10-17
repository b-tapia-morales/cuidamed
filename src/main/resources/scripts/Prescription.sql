CREATE TABLE IF NOT EXISTS residence.disease
(
    name_disease VARCHAR(30) PRIMARY KEY,
    kind         VARCHAR(20) NOT NULL,
    chronic      BOOLEAN,
);



CREATE TABLE IF NOT EXISTS residence.prescription
(
    elder_rut           VARCHAR(16) PRIMARY KEY REFERENCES residence.person (rut),
    name_disease        VARCHAR(20) PRIMARY KEY REFERENCES residence.disease (name_disease),
    date_prescription   DATE PRIMARY KEY,
    description         VARCHAR(50) NOT NULL
);


CREATE TABLE IF NOT EXISTS residence.suffer_disease
(
    elder_rut       VARCHAR(16) PRIMARY KEY REFERENCES residence.person(rut),
    name_disease    VARCHAR(20) PRIMARY KEY REFERENCES residence.disease (name_disease),
    date_diagnosis  DATE PRIMARY KEY,
);

CREATE TABLE IF NOT EXISTS residence.medication_prescription
(
    elder_rut           VARCHAR(16) PRIMARY KEY REFERENCES residence.person (rut),
    date_presciption    DATE PRIMARY KEY,
    /*name_medication     VARCHAR(20) PRIMARY KEY REFERENCES residence.medication (),*/
    date_beginning      DATE NOT  NULL,
    date_finished       DATE,

);
