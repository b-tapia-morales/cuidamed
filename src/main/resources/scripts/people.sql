CREATE TABLE IF NOT EXISTS residence.person
(
    rut              VARCHAR(16) PRIMARY KEY,
    first_names      VARCHAR(128) NOT NULL,
    last_name        VARCHAR(64)  NOT NULL,
    second_last_name VARCHAR(64)  NOT NULL,
    birth_date       DATE         NOT NULL,
    age              SMALLINT,
    sex              SMALLINT     NOT NULL
);

CREATE TABLE IF NOT EXISTS residence.responsible
(
    rut          VARCHAR(16) PRIMARY KEY REFERENCES residence.person (rut),
    mobile_phone VARCHAR(16) NOT NULL
);

CREATE TABLE IF NOT EXISTS residence.carer
(
    rut          VARCHAR(16) PRIMARY KEY REFERENCES residence.person (rut),
    mobile_phone VARCHAR(16) NOT NULL,
    hire_date    DATE        NOT NULL
);

CREATE TABLE IF NOT EXISTS residence.elder
(
    rut             VARCHAR(16) PRIMARY KEY REFERENCES residence.person (rut),
    active          BOOLEAN,
    admission_date  DATE NOT NULL,
    responsible_rut VARCHAR(16) REFERENCES residence.responsible (rut)
);

CREATE TABLE IF NOT EXISTS address
(
    commune_id  SMALLINT     NOT NULL REFERENCES residence.commune (id),
    street      VARCHAR(128) NOT NULL,
    number      SMALLINT     NOT NULL,
    postal_code INT,
    fixed_phone VARCHAR(16),
    person_rut  VARCHAR(16) REFERENCES residence.person (rut),
    PRIMARY KEY (commune_id, street, number, person_rut)
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