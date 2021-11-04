INSERT INTO residence.disease
VALUES ('Artritis', 4, TRUE)
ON CONFLICT DO NOTHING;
INSERT INTO residence.disease
VALUES ('Alzheimer', 6, TRUE)
ON CONFLICT DO NOTHING;
INSERT INTO residence.disease
VALUES ('Neumonia', 11, FALSE)
ON CONFLICT DO NOTHING;
INSERT INTO residence.disease
VALUES ('Artrosis', 4, TRUE)
ON CONFLICT DO NOTHING;
INSERT INTO residence.disease
VALUES ('Demencia senil', 6, TRUE)
ON CONFLICT DO NOTHING;
INSERT INTO residence.disease
VALUES ('Parkinson', 6, TRUE)
ON CONFLICT DO NOTHING;
INSERT INTO residence.disease
VALUES ('ELA', 7, TRUE)
ON CONFLICT DO NOTHING;
INSERT INTO residence.disease
VALUES ('Osteoporosis', 1, TRUE)
ON CONFLICT DO NOTHING;
INSERT INTO residence.disease
VALUES ('Ictus', 10, FALSE)
ON CONFLICT DO NOTHING;
INSERT INTO residence.disease
VALUES ('Diabetes', 5, TRUE)
ON CONFLICT DO NOTHING;
INSERT INTO residence.disease
VALUES ('Hipertension', 10, TRUE)
ON CONFLICT DO NOTHING;
INSERT INTO residence.disease
VALUES ('Infarto', 10, TRUE)
ON CONFLICT DO NOTHING;
INSERT INTO residence.disease
VALUES ('Fibromialgia', 4, TRUE)
ON CONFLICT DO NOTHING;
INSERT INTO residence.disease
VALUES ('Anemia', 3, TRUE)
ON CONFLICT DO NOTHING;
INSERT INTO residence.disease
VALUES ('Estreñimiento', 12, TRUE)
ON CONFLICT DO NOTHING;
INSERT INTO residence.disease
VALUES ('Nauseas y vomitos', 12, TRUE)
ON CONFLICT DO NOTHING;


INSERT INTO residence.medication
VALUES ('Paracetamol', 1, 2, 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication
VALUES ('Ibuprofeno', 3, 3, 1)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication
VALUES ('Galantamina', 1, 1, 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication
VALUES ('Azitromicina', 3, 3, 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication
VALUES ('Demerol', 3, 3, 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication
VALUES ('Donepezilo', 3, 3, 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication
VALUES ('Levadopa', 3, 3, 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication
VALUES ('Alteplasa', 3, 3, 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication
VALUES ('Riluzol', 3, 3, 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication
VALUES ('Suplementos de calcio y vitamina D', 3, 3, 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication
VALUES ('Tolbutamida', 3, 3, 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication
VALUES ('Acebutolol', 3, 3, 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication
VALUES ('Atenolol', 3, 3, 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication
VALUES ('Nitroglicerina', 3, 3, 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication
VALUES ('Duloxetina', 3, 3, 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication
VALUES ('Pregabalina', 3, 3, 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication
VALUES ('Suplementos Vitaminicos', 3, 3, 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication
VALUES ('Lubiprostona', 3, 3, 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication
VALUES ('Pepto-Bismol', 3, 3, 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication
VALUES ('Kaopectate', 3, 3, 2)
ON CONFLICT DO NOTHING;



INSERT INTO residence.elder_suffers_disease
VALUES ('8768514-4', 'Artritis', '2020-05-08')
ON CONFLICT DO NOTHING;
INSERT INTO residence.elder_suffers_disease
VALUES ('5595642-1', 'Alzheimer', '2018-07-14')
ON CONFLICT DO NOTHING;
INSERT INTO residence.elder_suffers_disease
VALUES ('10031785-0', 'Neumonia', '2019-06-15')
ON CONFLICT DO NOTHING;
INSERT INTO residence.elder_suffers_disease
VALUES ('8742099-k', 'Demencia senil', '2018-05-14')
ON CONFLICT DO NOTHING;
INSERT INTO residence.elder_suffers_disease
VALUES ('8768514-4', 'Artrosis', '2020-03-19')
ON CONFLICT DO NOTHING;
INSERT INTO residence.elder_suffers_disease
VALUES ('5595642-1', 'Diabetes', '2017-07-02')
ON CONFLICT DO NOTHING;
INSERT INTO residence.elder_suffers_disease
VALUES ('5875397-1', 'Nauseas y vomitos', '2018-11-08')
ON CONFLICT DO NOTHING;
INSERT INTO residence.elder_suffers_disease
VALUES ('8820206-6', 'Osteoporosis', '2020-09-30')
ON CONFLICT DO NOTHING;
INSERT INTO residence.elder_suffers_disease
VALUES ('8346739-8', 'Infarto', '2021-02-14')
ON CONFLICT DO NOTHING;
INSERT INTO residence.elder_suffers_disease
VALUES ('8358513-7', 'Estreñimiento', '2018-05-14')
ON CONFLICT DO NOTHING;
INSERT INTO residence.elder_suffers_disease
VALUES ('8358513-7', 'Diabetes', '2017-12-20')
ON CONFLICT DO NOTHING;
INSERT INTO residence.elder_suffers_disease
VALUES ('5902831-6', 'Parkinson', '2019-10-18')
ON CONFLICT DO NOTHING;
INSERT INTO residence.elder_suffers_disease
VALUES ('5968347-0', 'Ictus', '2016-12-20')
ON CONFLICT DO NOTHING;
INSERT INTO residence.elder_suffers_disease
VALUES ('5875397-1', 'Hipertension', '2018-09-25')
ON CONFLICT DO NOTHING;


INSERT INTO residence.prescription
VALUES ('8768514-4', 'Artritis', '2020-05-10', 'enfermedad que afecta los huesos')
ON CONFLICT DO NOTHING;
INSERT INTO residence.prescription
VALUES ('5595642-1', 'Alzheimer', '2018-07-15', 'enfermedad degenerativa cerebral')
ON CONFLICT DO NOTHING;
INSERT INTO residence.prescription
VALUES ('10031785-0', 'Neumonia', '2019-06-18',
        'enfemedad por covid necesita reposo 2 semanas en cama y sin licencia')
ON CONFLICT DO NOTHING;
INSERT INTO residence.prescription
VALUES ('8742099-k', 'Demencia senil', '2018-05-14',
        'enfermedad degenerativa se ruega tomar los medicamentos en orden')
ON CONFLICT DO NOTHING;
INSERT INTO residence.prescription
VALUES ('8768514-4', 'Artrosis', '2020-03-19',
        'enfermedad con mucho dolor en caso de no persistir se ruega llamar a un nuevo medico')
ON CONFLICT DO NOTHING;
INSERT INTO residence.prescription
VALUES ('5595642-1', 'Diabetes', '2017-07-02',
        'enfermedad crónica típica chilena')
ON CONFLICT DO NOTHING;
INSERT INTO residence.prescription
VALUES ('5875397-1', 'Nauseas y vomitos', '2018-11-08',
        'dolores estomacales, cuidado en sus comidas alrededor de 1 semana')
ON CONFLICT DO NOTHING;
INSERT INTO residence.prescription
VALUES ('8820206-6', 'Osteoporosis', '2020-09-30',
        'dolores musculares fuertes en su zona abdominal toma de medicamentos recurrentes')
ON CONFLICT DO NOTHING;
INSERT INTO residence.prescription
VALUES ('8346739-8', 'Infarto', '2021-02-14',
        'disminución de comidas por obstruction de arterias, se ruega ejercidos y nutricionista')
ON CONFLICT DO NOTHING;
INSERT INTO residence.prescription
VALUES ('8358513-7', 'Estreñimiento', '2018-05-14',
        'comidas mas ligeras por 2 semanas y seguimiento de medicamentos ')
ON CONFLICT DO NOTHING;
INSERT INTO residence.prescription
VALUES ('8358513-7', 'Diabetes', '2017-12-20',
        'diabetes elevada toma de medicamentos urgentes de ahora en adelante')
ON CONFLICT DO NOTHING;
INSERT INTO residence.prescription
VALUES ('5902831-6', 'Parkinson', '2019-10-18',
        'enfermedad degenerativa, empezar el tratamiento lo antes posible')
ON CONFLICT DO NOTHING;
INSERT INTO residence.prescription
VALUES ('5968347-0', 'Ictus', '2016-12-20',
        'sintomas tipicos de la enfermedad Ictus , se ruega tomar los medicamentos los antes posibles')
ON CONFLICT DO NOTHING;
INSERT INTO residence.prescription
VALUES ('5875397-1', 'Hipertension', '2018-09-25',
        'presiones arteriales altas se ruega, empezar un tratamiento')
ON CONFLICT DO NOTHING;

INSERT INTO residence.medication_prescription
VALUES ('8768514-4', 'Artritis', '2020-05-10', 'Paracetamol', '2020-05-10', null)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication_prescription
VALUES ('8768514-4', 'Artritis', '2020-05-10', 'Ibuprofeno', '2020-05-10', '2020-05-20')
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication_prescription
VALUES ('5595642-1', 'Alzheimer', '2018-07-15', 'Galantamina', '2018-07-15', null)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication_prescription
VALUES ('10031785-0', 'Neumonia', '2019-06-18', 'Azitromicina', '2019-06-18', '2019-06-30')
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication_prescription
VALUES ('8742099-k', 'Demencia senil', '2018-05-14', 'Donepezilo', '2018-05-19', null)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication_prescription
VALUES ('8768514-4', 'Artrosis', '2020-03-19', 'Ibuprofeno', '2020-03-19', null)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication_prescription
VALUES ('8768514-4', 'Artrosis', '2020-03-19', 'Demerol', '2020-03-19', null)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication_prescription
VALUES ('5595642-1', 'Diabetes', '2017-07-02', 'Tolbutamida', '2017-07-10', null)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication_prescription
VALUES ('5875397-1', 'Nauseas y vomitos', '2018-11-08', 'Pepto-Bismol', '2018-11-09', null)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication_prescription
VALUES ('5875397-1', 'Nauseas y vomitos', '2018-11-08', 'Kaopectate', '2018-11-09', null)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication_prescription
VALUES ('8820206-6', 'Osteoporosis', '2020-09-30', 'Suplementos de calcio y vitamina D', '2020-09-30',
        null)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication_prescription
VALUES ('8346739-8', 'Infarto', '2021-02-14', 'Nitroglicerina', '2021-02-14',
        null)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication_prescription
VALUES ('8358513-7', 'Estreñimiento', '2018-05-14', 'Lubiprostona', '2018-05-16',
        null)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication_prescription
VALUES ('8358513-7', 'Diabetes', '2017-12-20', 'Tolbutamida', '2017-12-22',
        null)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication_prescription
VALUES ('5902831-6', 'Parkinson', '2019-10-18', 'Levadopa', '2019-10-21',
        null)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication_prescription
VALUES ('5968347-0', 'Ictus', '2016-12-20', 'Alteplasa', '2016-12-20',
        null)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication_prescription
VALUES ('5875397-1', 'Hipertension', '2018-09-25', 'Alteplasa', '2018-09-25',
        null)
ON CONFLICT DO NOTHING;