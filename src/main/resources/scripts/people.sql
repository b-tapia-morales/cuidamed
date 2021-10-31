DROP TABLE IF EXISTS residence.carer;
DROP TABLE IF EXISTS residence.elder;
DROP TABLE IF EXISTS residence.responsible;
DROP TABLE IF EXISTS residence.person;

CREATE TABLE IF NOT EXISTS residence.person
(
    rut              VARCHAR(16)  NOT NULL,
    first_names      VARCHAR(128) NOT NULL,
    last_name        VARCHAR(64)  NOT NULL,
    second_last_name VARCHAR(64)  NOT NULL,
    birth_date       DATE         NOT NULL,
    age              SMALLINT     NOT NULL,
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
    commune_id  SMALLSERIAL     NOT NULL,
    street      VARCHAR(128) NOT NULL,
    number      SMALLINT     NOT NULL,
    postal_code INT,
    fixed_phone INT,
    person_rut  VARCHAR(16),
    FOREIGN KEY (commune_id) REFERENCES residence.commune (id),
    FOREIGN KEY (person_rut) REFERENCES residence.person (rut),
    primary key (commune_id, street, number, person_rut)
);


/*CREATE TABLE IF NOT EXISTS apartment
(
    commune_id     SMALLINT     NOT NULL,
    street         VARCHAR(128) NOT NULL,
    number         SMALLINT     NOT NULL,
    person_rut     VARCHAR(16)  NOT NULL,
    apartment_name VARCHAR(128) NOT NULL,
    building       VARCHAR(128) NOT NULL,
    floor          SMALLINT,
    FOREIGN KEY (commune_id, street, number, person_rut) REFERENCES address (commune_id, street, number, person_rut),
    PRIMARY KEY (number, apartment_name, building, floor)
);*/