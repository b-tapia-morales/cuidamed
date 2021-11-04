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