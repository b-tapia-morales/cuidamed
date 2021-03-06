INSERT INTO residence.disease
VALUES ('Artritis', 4, TRUE);
INSERT INTO residence.disease
VALUES ('Alzheimer', 6, TRUE);
INSERT INTO residence.disease
VALUES ('Neumonia', 11, FALSE);
INSERT INTO residence.disease
VALUES ('Artrosis', 4, TRUE);
INSERT INTO residence.disease
VALUES ('Demencia senil', 6, TRUE);
INSERT INTO residence.disease
VALUES ('Parkinson', 6, TRUE);
INSERT INTO residence.disease
VALUES ('ELA', 7, TRUE);
INSERT INTO residence.disease
VALUES ('Osteoporosis', 1, TRUE);
INSERT INTO residence.disease
VALUES ('Ictus', 10, FALSE);
INSERT INTO residence.disease
VALUES ('Diabetes', 5, TRUE);
INSERT INTO residence.disease
VALUES ('Hipertensión', 10, TRUE);
INSERT INTO residence.disease
VALUES ('Infarto', 10, TRUE);
INSERT INTO residence.disease
VALUES ('Fibromialgia', 4, TRUE);
INSERT INTO residence.disease
VALUES ('Anemia', 3, TRUE);
INSERT INTO residence.disease
VALUES ('Estreñimiento', 12, TRUE);
INSERT INTO residence.disease
VALUES ('Nauseas y vómitos', 12, TRUE);


INSERT INTO residence.medication
VALUES ('Paracetamol', 1, 2, 1);
INSERT INTO residence.medication
VALUES ('Ibuprofeno', 1, 2, 1);
INSERT INTO residence.medication
VALUES ('Galantamina', 1, 2, 1);
INSERT INTO residence.medication
VALUES ('Azitromicina', 1, 1, 1);
INSERT INTO residence.medication
VALUES ('Demerol', 1, 2, 1);
INSERT INTO residence.medication
VALUES ('Donepezilo', 1, 2, 1);
INSERT INTO residence.medication
VALUES ('Levadopa', 1, 2, 1);
INSERT INTO residence.medication
VALUES ('Alteplasa', 2, 5, 2);
INSERT INTO residence.medication
VALUES ('Riluzol', 1, 2, 1);
INSERT INTO residence.medication
VALUES ('Suplementos de calcio y vitamina D', 1, 1, 1);
INSERT INTO residence.medication
VALUES ('Tolbutamida', 1, 2, 1);
INSERT INTO residence.medication
VALUES ('Acebutolol', 1, 1, 1);
INSERT INTO residence.medication
VALUES ('Atenolol', 1, 2, 1);
INSERT INTO residence.medication
VALUES ('Nitroglicerina', 2, 5, 2);
INSERT INTO residence.medication
VALUES ('Duloxetina', 1, 1, 1);
INSERT INTO residence.medication
VALUES ('Pregabalina', 1, 1, 1);
INSERT INTO residence.medication
VALUES ('Suplementos Vitamínicos', 1, 1, 1);
INSERT INTO residence.medication
VALUES ('Lubiprostona', 1, 1, 1);
INSERT INTO residence.medication
VALUES ('Pepto-Bismol', 1, 3, 1);
INSERT INTO residence.medication
VALUES ('Kaopectate', 1, 4, 2);



INSERT INTO residence.sick_elderly
VALUES ('8768514-4', 'Artritis', '2020-05-08');
INSERT INTO residence.sick_elderly
VALUES ('5595642-1', 'Alzheimer', '2018-07-14');
INSERT INTO residence.sick_elderly
VALUES ('10031785-0', 'Neumonia', '2019-06-15');
INSERT INTO residence.sick_elderly
VALUES ('8742099-k', 'Demencia senil', '2018-05-14');
INSERT INTO residence.sick_elderly
VALUES ('8768514-4', 'Artrosis', '2020-03-19');
INSERT INTO residence.sick_elderly
VALUES ('5595642-1', 'Diabetes', '2017-07-02');
INSERT INTO residence.sick_elderly
VALUES ('5875397-1', 'Nauseas y vómitos', '2018-11-08');
INSERT INTO residence.sick_elderly
VALUES ('8820206-6', 'Osteoporosis', '2020-09-30');
INSERT INTO residence.sick_elderly
VALUES ('8346739-8', 'Infarto', '2021-02-14');
INSERT INTO residence.sick_elderly
VALUES ('8358513-7', 'Estreñimiento', '2018-05-14');
INSERT INTO residence.sick_elderly
VALUES ('8358513-7', 'Diabetes', '2017-12-20');
INSERT INTO residence.sick_elderly
VALUES ('5902831-6', 'Parkinson', '2019-10-18');
INSERT INTO residence.sick_elderly
VALUES ('5968347-0', 'Ictus', '2016-12-20');
INSERT INTO residence.sick_elderly
VALUES ('5875397-1', 'Hipertensión', '2018-09-25');


INSERT INTO residence.diagnostic
VALUES ('8768514-4', 'Artritis', '2020-05-10', 'Enfermedad que afecta los huesos.');
INSERT INTO residence.diagnostic
VALUES ('5595642-1', 'Alzheimer', '2018-07-15', 'Enfermedad degenerativa cerebral.');
INSERT INTO residence.diagnostic
VALUES ('10031785-0', 'Neumonia', '2019-06-18',
        'Enfermedad por COVID. necesita reposo 2 semanas en cama y sin licencia.');
INSERT INTO residence.diagnostic
VALUES ('8742099-k', 'Demencia senil', '2018-05-14',
        'Enfermedad degenerativa. se ruega tomar los medicamentos en orden.');
INSERT INTO residence.diagnostic
VALUES ('8768514-4', 'Artrosis', '2020-03-19',
        'Enfermedad con mucho dolor. n caso de no persistir, se ruega llamar a un nuevo médico.');
INSERT INTO residence.diagnostic
VALUES ('5595642-1', 'Diabetes', '2017-07-02', 'Enfermedad crónica típica chilena.');
INSERT INTO residence.diagnostic
VALUES ('5875397-1', 'Nauseas y vómitos', '2018-11-08',
        'Dolores estomacales, cuidado en sus comidas alrededor de 1 semana.');
INSERT INTO residence.diagnostic
VALUES ('8820206-6', 'Osteoporosis', '2020-09-30',
        'dolores musculares fuertes en su zona abdominal. Toma de medicamentos recurrentes.');
INSERT INTO residence.diagnostic
VALUES ('8346739-8', 'Infarto', '2021-02-14',
        'Disminución de comidas por obstrucción de arterias. Se ruega ejercidos y nutricionista.');
INSERT INTO residence.diagnostic
VALUES ('8358513-7', 'Estreñimiento', '2018-05-14',
        'Comidas más ligeras por 2 semanas y seguimiento de medicamentos.');
INSERT INTO residence.diagnostic
VALUES ('8358513-7', 'Diabetes', '2017-12-20',
        'Diabetes elevada. toma de medicamentos urgentes de ahora en adelante.');
INSERT INTO residence.diagnostic
VALUES ('5902831-6', 'Parkinson', '2019-10-18',
        'Enfermedad degenerativa, empezar el tratamiento lo antes posible.');
INSERT INTO residence.diagnostic
VALUES ('5968347-0', 'Ictus', '2016-12-20',
        'Síntomas típicos de la enfermedad ictus, se ruega tomar los medicamentos los antes posible.');
INSERT INTO residence.diagnostic
VALUES ('5875397-1', 'Hipertensión', '2018-09-25',
        'Presiones arteriales altas, se ruega empezar un tratamiento.');

INSERT INTO residence.medication_prescription
VALUES ('8768514-4', 'Artritis', '2020-05-10', 'Paracetamol', '2020-05-10', null, 3, 2);
INSERT INTO residence.medication_prescription
VALUES ('8768514-4', 'Artritis', '2020-05-10', 'Ibuprofeno', '2020-05-10', '2020-05-20', 2, 1);
INSERT INTO residence.medication_prescription
VALUES ('5595642-1', 'Alzheimer', '2018-07-15', 'Galantamina', '2018-07-15', null, 3, 1);
INSERT INTO residence.medication_prescription
VALUES ('10031785-0', 'Neumonia', '2019-06-18', 'Azitromicina', '2019-06-18', '2019-06-30', 2, 2);
INSERT INTO residence.medication_prescription
VALUES ('8742099-k', 'Demencia senil', '2018-05-14', 'Donepezilo', '2018-05-19', null, 1, 1);
INSERT INTO residence.medication_prescription
VALUES ('8768514-4', 'Artrosis', '2020-03-19', 'Ibuprofeno', '2020-03-19', null, 3, 4);
INSERT INTO residence.medication_prescription
VALUES ('8768514-4', 'Artrosis', '2020-03-19', 'Demerol', '2020-03-19', null, 1, 2);
INSERT INTO residence.medication_prescription
VALUES ('5595642-1', 'Diabetes', '2017-07-02', 'Tolbutamida', '2017-07-10', null, 4, 3);
INSERT INTO residence.medication_prescription
VALUES ('5875397-1', 'Nauseas y vómitos', '2018-11-08', 'Pepto-Bismol', '2018-11-09', null, 4, 2);
INSERT INTO residence.medication_prescription
VALUES ('5875397-1', 'Nauseas y vómitos', '2018-11-08', 'Kaopectate', '2018-11-09', null, 1, 1);
INSERT INTO residence.medication_prescription
VALUES ('8820206-6', 'Osteoporosis', '2020-09-30', 'Suplementos de calcio y vitamina D',
        '2020-09-30', null, 3, 3);
INSERT INTO residence.medication_prescription
VALUES ('8346739-8', 'Infarto', '2021-02-14', 'Nitroglicerina', '2021-02-14', null, 4, 2);
INSERT INTO residence.medication_prescription
VALUES ('8358513-7', 'Estreñimiento', '2018-05-14', 'Lubiprostona', '2018-05-16', null, 1, 1);
INSERT INTO residence.medication_prescription
VALUES ('8358513-7', 'Diabetes', '2017-12-20', 'Tolbutamida', '2017-12-22', null, 5, 3);
INSERT INTO residence.medication_prescription
VALUES ('5902831-6', 'Parkinson', '2019-10-18', 'Levadopa', '2019-10-21', null, 2, 3);
INSERT INTO residence.medication_prescription
VALUES ('5968347-0', 'Ictus', '2016-12-20', 'Alteplasa', '2016-12-20', null, 4, 3);
INSERT INTO residence.medication_prescription
VALUES ('5875397-1', 'Hipertensión', '2018-09-25', 'Alteplasa', '2018-09-25', null, 3, 3);