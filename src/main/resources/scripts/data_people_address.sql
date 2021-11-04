/*
CREATE TABLE IF NOT EXISTS residence.address
(
    commune_id  SMALLSERIAL  NOT NULL,
    street      VARCHAR(128) NOT NULL,
    number      SMALLINT     NOT NULL,
    postal_code INT,
    fixed_phone INT, /* 6 digitos */
    person_rut  VARCHAR(16),
    FOREIGN KEY (commune_id) REFERENCES residence.commune (id),
    FOREIGN KEY (person_rut) REFERENCES residence.person (rut),
    primary key (commune_id, street, number, person_rut)
);
*/
SELECT *
FROM residence.commune WHERE commune_name = 'La Florida';

/* Personas - Responsables */
INSERT INTO residence.address
VALUES (31, 'Juan Barrera Cortes', 2050, 1700000, 937351,'14585523-3')
ON CONFLICT DO NOTHING;
INSERT INTO residence.address
VALUES (31, 'Manuel Silva Briceño', 361, 1700000, 996322, '14207467-2')
ON CONFLICT DO NOTHING;
INSERT INTO residence.address
VALUES (31, 'Decimo Erler', 283, 1700000, 554699, '13003385-7')
ON CONFLICT DO NOTHING;
INSERT INTO residence.address
VALUES (31, 'Jorge Wilson del Solar', 431, 1700000, 599910, '12997638-1')
ON CONFLICT DO NOTHING;
INSERT INTO residence.address
VALUES (31, 'Lago Ranco', 2185, 1700000, 458261, '14822838-8')
ON CONFLICT DO NOTHING;
INSERT INTO residence.address
VALUES (31, 'Todos Los Santos', 2216, 1700000, 540467, '12682668-0')
ON CONFLICT DO NOTHING;
INSERT INTO residence.address
VALUES (92, 'El Monte', 2899, 8420000, 927361, '14075371-8')
ON CONFLICT DO NOTHING;
INSERT INTO residence.address
VALUES (92, 'Chiñihue', 1021, 8420000, 861278, '14102400-0')
ON CONFLICT DO NOTHING;
INSERT INTO residence.address
VALUES (100, 'San José', 47, 9250000, 595242, '13988190-7')
ON CONFLICT DO NOTHING;
INSERT INTO residence.address
VALUES (100, 'Manuel Rodríguez', 1005, 9250000, 458518, '13371799-4')
ON CONFLICT DO NOTHING;

/* Personas - Cuidadores */
INSERT INTO residence.address
VALUES (109, 'Glasgow', 9315, 8240000, 115684, '15787461-6')
ON CONFLICT DO NOTHING;
INSERT INTO residence.address
VALUES (17, 'Gral. Manuel Baquedano', 1181, 1240000, 623023, '17662625-9')
ON CONFLICT DO NOTHING;
INSERT INTO residence.address
VALUES (17, 'Esmeralda', 2744, 1240000, 858045, '15023397-6')
ON CONFLICT DO NOTHING;
INSERT INTO residence.address
VALUES (148, 'Montecarlo', 1266, 2820000, 550820, '16989228-8')
ON CONFLICT DO NOTHING;
INSERT INTO residence.address
VALUES (148, 'Las Carmelitas', 2563, 2820000, 976529, '16213821-9')
ON CONFLICT DO NOTHING;


SELECT P.rut, P.first_names, P.last_name, P.second_last_name,C.commune_name, A.street, A.number, A.fixed_phone
FROM residence.person P, residence.responsible R, residence.address A, residence.commune C
WHERE P.rut = R.rut AND R.rut = A.person_rut AND A.commune_id = C.id;

SELECT P.rut, P.first_names, P.last_name, P.second_last_name,C.commune_name, A.street, A.number, A.fixed_phone
FROM residence.person P, residence.carer Carer, residence.address A, residence.commune C
WHERE P.rut = Carer.rut AND Carer.rut = A.person_rut AND A.commune_id = C.id;