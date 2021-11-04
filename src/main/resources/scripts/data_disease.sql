INSERT INTO residence.disease
VALUES ('artritis',2,TRUE)
ON CONFLICT DO NOTHING;
INSERT INTO residence.disease
VALUES ('alzheimer',3,TRUE)
ON CONFLICT DO NOTHING;
INSERT INTO residence.disease
VALUES ('neumonia',4,TRUE)
ON CONFLICT DO NOTHING;


INSERT INTO residence.elder_suffers_disease
VALUES ('8768514-4','artritis','2020-05-08')
ON CONFLICT DO NOTHING;
INSERT INTO residence.elder_suffers_disease
VALUES ('5595642-1','alzheimer','2018-07-14')
ON CONFLICT DO NOTHING;
INSERT INTO residence.elder_suffers_disease
VALUES ('10031785-0','neumonia','2019-06-15')
ON CONFLICT DO NOTHING;