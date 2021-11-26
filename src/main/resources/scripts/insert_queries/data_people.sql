/*
 Personas - Adultos mayores
 */
INSERT INTO residence.person
VALUES ('8742099-k', 'Matías José', 'González', 'Rojas', '1953-11-15', 1)
ON CONFLICT DO NOTHING;
INSERT INTO residence.person
VALUES ('8768514-4', 'Alberto Manuel', 'Rojas', 'Sepúlveda', '1953-11-15', 1)
ON CONFLICT DO NOTHING;
INSERT INTO residence.person
VALUES ('5595642-1', 'Juan Henry', 'López', 'Muñoz', '1949-06-17', 1)
ON CONFLICT DO NOTHING;
INSERT INTO residence.person
VALUES ('5875397-1', 'Alejandro Arturo', 'Silva', 'Reinoso', '1944-09-09', 1)
ON CONFLICT DO NOTHING;
INSERT INTO residence.person
VALUES ('10031785-0', 'Ramón Felipe', 'Araya', 'Aguilar', '1954-08-11', 1)
ON CONFLICT DO NOTHING;
INSERT INTO residence.person
VALUES ('5968347-0', 'María Luisa', 'Aguirre', 'Zúñiga', '1945-07-07', 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.person
VALUES ('8820206-6', 'Rocío Michelle', 'Tello', 'Godoy', '1951-01-28', 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.person
VALUES ('8346739-8', 'Ana Luz', 'Meneses', 'Pizarro', '1944-05-17', 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.person
VALUES ('8358513-7', 'Laura Jasmin', 'Veliz', 'Carmona', '1944-05-06', 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.person
VALUES ('5902831-6', 'Luis Alberto', 'Contreras', 'Parra', '1951-11-30', 1)
ON CONFLICT DO NOTHING;

/*
 Personas - Responsables
 */
INSERT INTO residence.person
VALUES ('14585523-3', 'Alondra María', 'González', 'Palma', '1981-11-30', 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.person
VALUES ('14207467-2', 'Juan Fernando', 'Contreras', 'Prado', '1979-11-25', 1)
ON CONFLICT DO NOTHING;
INSERT INTO residence.person
VALUES ('13003385-7', 'Luz María', 'Sepúlveda', 'Burgos', '1974-08-21', 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.person
VALUES ('12997638-1', 'Verónica Marcela', 'Lopez', 'Aguirre', '1986-02-03', 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.person
VALUES ('14822838-8', 'Sebastián Felipe', 'Silva', 'Avello', '1971-03-20', 1)
ON CONFLICT DO NOTHING;
INSERT INTO residence.person
VALUES ('12682668-0', 'Diego Alejandro', 'Chepillo', 'Veliz', '1988-06-17', 1)
ON CONFLICT DO NOTHING;
INSERT INTO residence.person
VALUES ('14075371-8', 'Claudia Carmen', 'Carmona', 'Jara', '1984-05-12', 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.person
VALUES ('14102400-0', 'Mario Luis', 'Godoy', 'Olivares', '1976-08-14', 1)
ON CONFLICT DO NOTHING;
INSERT INTO residence.person
VALUES ('13988190-7', 'Joselyn Ana', 'Zúñiga', 'Cordovez', '1977-11-11', 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.person
VALUES ('13371799-4', 'Ana María', 'Aguilar', 'Vicencio', '1979-06-06', 2)
ON CONFLICT DO NOTHING;

/*
 Personas - Cuidadores
 */
INSERT INTO residence.person
VALUES ('15787461-6', 'María José', 'Pastén', 'Cordovez', '1984-01-04', 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.person
VALUES ('17662625-9', 'Valentina', 'González', 'Tapia', '1984-01-08', 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.person
VALUES ('15023397-6', 'Camila', 'Varas', 'Silva', '1982-03-22', 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.person
VALUES ('16989228-8', 'Constanza', 'Rojas', 'Rojas', '1986-11-30', 2)
ON CONFLICT DO NOTHING;
INSERT INTO residence.person
VALUES ('16213821-9', 'Mario', 'Orellana', 'Contreras', '1987-02-19', 1)
ON CONFLICT DO NOTHING;

/*
 Responsables.
 */
INSERT INTO residence.responsible
VALUES ('14585523-3', 90495269)
ON CONFLICT DO NOTHING;
INSERT INTO residence.responsible
VALUES ('14207467-2', 64877715)
ON CONFLICT DO NOTHING;
INSERT INTO residence.responsible
VALUES ('13003385-7', 80227504)
ON CONFLICT DO NOTHING;
INSERT INTO residence.responsible
VALUES ('12997638-1', 20705426)
ON CONFLICT DO NOTHING;
INSERT INTO residence.responsible
VALUES ('14822838-8', 75178361)
ON CONFLICT DO NOTHING;
INSERT INTO residence.responsible
VALUES ('12682668-0', 64269505)
ON CONFLICT DO NOTHING;
INSERT INTO residence.responsible
VALUES ('14075371-8', 11613559)
ON CONFLICT DO NOTHING;
INSERT INTO residence.responsible
VALUES ('14102400-0', 63843353)
ON CONFLICT DO NOTHING;
INSERT INTO residence.responsible
VALUES ('13988190-7', 23502579)
ON CONFLICT DO NOTHING;
INSERT INTO residence.responsible
VALUES ('13371799-4', 67271212)
ON CONFLICT DO NOTHING;

/*
 Cuidadores
 */
INSERT INTO residence.carer
VALUES ('15787461-6', 69777161, '2016-02-25')
ON CONFLICT DO NOTHING;
INSERT INTO residence.carer
VALUES ('17662625-9', 30918281, '2016-05-15')
ON CONFLICT DO NOTHING;
INSERT INTO residence.carer
VALUES ('15023397-6', 83201886, '2017-01-16')
ON CONFLICT DO NOTHING;
INSERT INTO residence.carer
VALUES ('16989228-8', 23338982, '2017-10-13')
ON CONFLICT DO NOTHING;
INSERT INTO residence.carer
VALUES ('16213821-9', 97752711, '2017-05-23')
ON CONFLICT DO NOTHING;

/*
 Adultos Mayores
 */
INSERT INTO residence.elder
VALUES ('8742099-k', TRUE, '2017-09-20', '14585523-3')
ON CONFLICT DO NOTHING;
INSERT INTO residence.elder
VALUES ('8768514-4', TRUE, '2018-06-23', '13003385-7')
ON CONFLICT DO NOTHING;
INSERT INTO residence.elder
VALUES ('5595642-1', TRUE, '2017-09-26', '12997638-1')
ON CONFLICT DO NOTHING;
INSERT INTO residence.elder
VALUES ('5875397-1', FALSE, '2017-07-05', '14822838-8')
ON CONFLICT DO NOTHING;
INSERT INTO residence.elder
VALUES ('10031785-0', FALSE, '2018-03-22', '13371799-4')
ON CONFLICT DO NOTHING;
INSERT INTO residence.elder
VALUES ('5968347-0', TRUE, '2018-09-16', '13988190-7')
ON CONFLICT DO NOTHING;
INSERT INTO residence.elder
VALUES ('8820206-6', TRUE, '2017-07-03', '14102400-0')
ON CONFLICT DO NOTHING;
INSERT INTO residence.elder
VALUES ('8346739-8', TRUE, '2018-10-04', '14075371-8')
ON CONFLICT DO NOTHING;
INSERT INTO residence.elder
VALUES ('8358513-7', TRUE, '2017-03-21', '12682668-0')
ON CONFLICT DO NOTHING;
INSERT INTO residence.elder
VALUES ('5902831-6', TRUE, '2016-09-03', '14207467-2')
ON CONFLICT DO NOTHING;
