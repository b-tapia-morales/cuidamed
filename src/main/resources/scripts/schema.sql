
CREATE DATABASE cuidamed

CREATE TABLE region IF NOT EXISTS
(
    id SMALLSERIAL PRIMARY KEY,
    region_name VARCHAR(64) UNIQUE NOT NULL,
    abbreviation VARCHAR(4) UNIQUE NOT NULL,
    capital VARCHAR(64) UNIQUE NOT NULL,
);

INSERT INTO region (region_name, abbreviation, capital)
VALUES ('Arica y Parinacota', 'AP', 'Arica'),
       ('Tarapacá', 'TA', 'Iquique'),
       ('Antofagasta', 'AN', 'Antofagasta'),
       ('Atacama', 'AT', 'Copiapó'),
       ('Coquimbo', 'CO', 'La Serena'),
       ('Valparaiso', 'VA', 'valparaíso'),
       ('Metropolitana de Santiago', 'RM', 'Santiago'),
       ('Libertador General Bernardo O''Higgins', 'OH', 'Rancagua'),
       ('Maule', 'MA', 'Talca'),
       ('Ñuble', 'NB', 'Chillán'),
       ('Biobío', 'BI', 'Concepción'),
       ('La Araucanía', 'IAR', 'Temuco'),
       ('Los Ríos', 'LR', 'Valdivia'),
       ('Los Lagos', 'LL', 'Puerto Montt'),
       ('Aysén del General Carlos Ibáñez del Campo', 'AI', 'Coyhaique'),
       ('Magallanes y de la Antártica Chilena', 'MG', 'Punta Arenas');


