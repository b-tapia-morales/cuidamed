INSERT INTO residence.disease
VALUES ('Artritis', 2, TRUE)
ON CONFLICT DO NOTHING;
INSERT INTO residence.disease
VALUES ('Alzheimer', 3, TRUE)
ON CONFLICT DO NOTHING;
INSERT INTO residence.disease
VALUES ('Neumonia', 4, TRUE)
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


INSERT INTO residence.medication
VALUES ('Paracetamol',1,2,2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication
VALUES ('Hibuprofeno',3,3,1)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication
VALUES ('Galantamina',1,1,2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication
VALUES ('Azitromicina',3,3,2)
ON CONFLICT DO NOTHING;


INSERT INTO residence.prescription
VALUES ('8768514-4','Artritis','2020-05-10','enfermedad que afecta los huesos')
ON CONFLICT DO NOTHING;
INSERT INTO residence.prescription
VALUES ('5595642-1','Alzheimer','2018-07-15','enfermedad degenerativa cerebral')
ON CONFLICT DO NOTHING;
INSERT INTO residence.prescription
VALUES ('10031785-0','Neumonia','2019-06-18','enfemedad por covid necesita reposo 2 semanas en cama y sin licencia')
ON CONFLICT DO NOTHING;

INSERT INTO residence.medication_prescription
VALUES ('8768514-4','Artritis','2020-05-10','Paracetamol','2020-05-10',null)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication_prescription
VALUES ('8768514-4','Artritis','2020-05-10','Hibuprofeno','2020-05-10','2020-05-20')
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication_prescription
VALUES ('5595642-1','Alzheimer','2018-07-15','Galantamina','2018-07-15',null)
ON CONFLICT DO NOTHING;
INSERT INTO residence.medication_prescription
VALUES ('10031785-0','Neumonia','2019-06-18','Azitromicina','2019-06-18','2019-06-30')
ON CONFLICT DO NOTHING;