CREATE TABLE IF NOT EXISTS person
(
    rut              VARCHAR(16) PRIMARY KEY,
    first_names      VARCHAR(128) NOT NULL,
    last_name        VARCHAR(64)  NOT NULL,
    second_last_name VARCHAR(64)  NOT NULL,
    birth_date       DATE         NOT NULL,
    age              SMALLINT     NOT NULL
);

CREATE TABLE IF NOT EXISTS responsible
(
    rut   VARCHAR(16) PRIMARY KEY REFERENCES person (rut),
    phone VARCHAR(16)
);

CREATE TABLE IF NOT EXISTS carer
(
    rut       VARCHAR(16) PRIMARY KEY REFERENCES person (rut),
    phone     VARCHAR(16),
    hire_date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS elder
(
    rut             VARCHAR(16) PRIMARY KEY REFERENCES person (rut),
    active          BOOLEAN,
    admission_date  DATE NOT NULL,
    responsible_rut VARCHAR(16) REFERENCES responsible (rut)
)