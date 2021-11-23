/*
 Fichas médicas
 */
INSERT INTO residence.medical_record
VALUES ('8742099-k', 4, 1);
INSERT INTO residence.medical_record
VALUES ('8768514-4', 3, 1);
INSERT INTO residence.medical_record
VALUES ('5595642-1', 1, 1);
INSERT INTO residence.medical_record
VALUES ('5875397-1', 7, 2);
INSERT INTO residence.medical_record
VALUES ('10031785-0', 8, 1);
INSERT INTO residence.medical_record
VALUES ('5968347-0', 1, 2);
INSERT INTO residence.medical_record
VALUES ('8820206-6', 3, 2);
INSERT INTO residence.medical_record
VALUES ('8346739-8', 4, 2);
INSERT INTO residence.medical_record
VALUES ('8358513-7', 6, 1);
INSERT INTO residence.medical_record
VALUES ('5902831-6', 4, 2);

/*
 Chequeos rutinarios
 */
INSERT INTO residence.routine_checkup
VALUES ('8346739-8', '2021-10-04', 1.60, 50.6, 19.5, 80, 120, 80, 36.3);
INSERT INTO residence.routine_checkup
VALUES ('5875397-1', '2021-11-04', 1.50, 53.6, 23.6, 90, 105, 71, 35.9);
INSERT INTO residence.routine_checkup
VALUES ('8358513-7', '2021-11-04', 1.73, 70.7, 23.4, 73, 125, 84, 35.7);
INSERT INTO residence.routine_checkup
VALUES ('5902831-6', '2021-10-04', 1.60, 64.0, 25.0, 93, 108, 74, 37.8);
INSERT INTO residence.routine_checkup
VALUES ('8768514-4', '2021-10-04', 1.59, 53.3, 21.0, 60, 113, 69, 38.5);
INSERT INTO residence.routine_checkup
VALUES ('5595642-1', '2021-11-04', 1.70, 69.2, 23.9, 80, 118, 81, 37.6);
INSERT INTO residence.routine_checkup
VALUES ('5875397-1', '2021-10-04', 1.50, 53.6, 23.6, 90, 105, 71, 35.9);
INSERT INTO residence.routine_checkup
VALUES ('5902831-6', '2021-11-04', 1.60, 64.0, 25.0, 93, 108, 74, 37.8);

/*
 Alergias
 */
INSERT INTO residence.allergy
VALUES ('8346739-8', 4, 'Líquidos para mano');
INSERT INTO residence.allergy
VALUES ('5875397-1', 4, 'Guantes Quirúrgicos');
INSERT INTO residence.allergy
VALUES ('8358513-7', 6, 'Gato');
INSERT INTO residence.allergy
VALUES ('5902831-6', 7, 'Flores');
INSERT INTO residence.allergy
VALUES ('8768514-4', 2, 'Frutilla');
INSERT INTO residence.allergy
VALUES ('5595642-1', 1, 'Paracetamol');
INSERT INTO residence.allergy
VALUES ('5875397-1', 3, 'Abeja');
INSERT INTO residence.allergy
VALUES ('5902831-6', 5, 'Humedad');

/*
 Intervenciones quirúrgicas.
 */
INSERT INTO residence.surgical_intervention
VALUES ('8358513-7', '2020-04-15', 'San Pablo', 1, 'Esguince menor en tobillo derecho');
INSERT INTO residence.surgical_intervention
VALUES ('5902831-6', '2019-01-07', 'Clínica Elqui', 2,
        'Operación por caída en muslo izquierdo con corte de 15 cm, se ingresa un total de 15 puntos');
INSERT INTO residence.surgical_intervention
VALUES ('8768514-4', '2021-03-24', 'Ovalle', 3, 'Operación interna de transplante de hígado');