CREATE OR REPLACE FUNCTION generate_names(code TEXT)
    RETURNS TEXT ARRAY AS
$func$
DECLARE
    male_names   TEXT ARRAY DEFAULT ARRAY ['Agustín', 'Benjamín', 'Vicente', 'Martín', 'Matías',
        'Joaquín', 'Tomás', 'Maximiliano', 'Mateo', 'Cristóbal', 'Alonso', 'Sebastián', 'José',
        'Felipe', 'Diego', 'Lucas', 'Nicolás', 'Máximo', 'Juan', 'Bastián', 'Gaspar', 'Gabriel',
        'Renato', 'Santiago', 'Emilio', 'Ignacio', 'Francisco', 'Javier', 'Damián', 'Bruno',
        'Simón', 'Daniel', 'Luis', 'Facundo', 'Franco', 'Ángel', 'Luciano', 'Julián', 'Pedro',
        'Pablo', 'Amaro', 'Fernando', 'Carlos', 'Cristián', 'Thomas', 'Esteban', 'Ian', 'David',
        'Alexander', 'León', 'Dante', 'Rafael', 'Jorge', 'Gustavo', 'Emiliano', 'Dylan',
        'Rodrigo', 'Víctor', 'Manuel', 'Camilo', 'Alejandro', 'Miguel', 'Elías', 'Álvaro',
        'Eduardo', 'Leonardo', 'Fabián', 'Andrés', 'Valentín', 'Gonzalo', 'Cristopher', 'Kevin',
        'Isaac', 'Alexis', 'Samuel', 'Aaron', 'Clemente', 'Jean', 'Ricardo', 'Alan', 'Héctor',
        'Sergio', 'Óscar', 'Claudio', 'Demian', 'Patricio', 'Iván', 'Guillermo', 'Mathías',
        'Marcelo', 'Mauricio', 'Josué', 'Jesús', 'Lukas', 'Isaías', 'César', 'Axel', 'Alfonso',
        'Alex', 'Baltazar'];
    female_names TEXT ARRAY DEFAULT ARRAY ['Sofía', 'Emilia', 'Florencia', 'Antonella',
        'Martina', 'Isidora', 'Maite', 'Josefa', 'Amanda', 'Agustina', 'Catalina', 'Antonia',
        'Trinidad', 'Fernanda', 'María', 'Valentina', 'Javiera', 'Isabella', 'Ignacia',
        'Constanza', 'Julieta', 'Francisca', 'Emily', 'Renata', 'Mia', 'Camila', 'Victoria',
        'Matilda', 'Rafaela', 'Belén', 'Pascal', 'Monserrat', 'Laura', 'Magdalena', 'Paz',
        'Anaís', 'Josefina', 'Pía', 'Violeta', 'Matilde', 'Dominique', 'Colomba', 'Rocío',
        'Amalia', 'Leonor', 'Daniela', 'Pascale', 'Emma', 'Amparo', 'Samantha', 'Gabriela',
        'Ámbar', 'Rafaella', 'Amelia', 'Mayra', 'Sophia', 'Génesis', 'Ema', 'Alondra',
        'Mariana', 'Dominga', 'Mayte', 'Michelle', 'Rayén', 'Danae', 'Elena', 'Lucía',
        'Millaray', 'Paula', 'Elizabeth', 'Anahís', 'Carolina', 'Elisa', 'Amy', 'Bárbara',
        'Isabel', 'Ashley', 'Thiare', 'Noemí', 'Aylin', 'Luciana', 'Krishna', 'Ángela',
        'Esperanza', 'Sara', 'Carla', 'Noelia', 'Kiara', 'Katalina', 'Celeste', 'Montserrat',
        'Denisse', 'Dafne', 'Abigail', 'Antonela', 'Olivia', 'Maura', 'Alejandra', 'Alexandra',
        'Consuelo'];
    names        TEXT ARRAY;
    choice       TEXT DEFAULT upper(code);
BEGIN
    CASE choice
        WHEN 'M' THEN names = male_names;
        WHEN 'F' THEN names = female_names;
        ELSE names = NULL;
        END CASE;
    RETURN names;
END
$func$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generate_concatenated_names(code TEXT, n INTEGER) RETURNS TEXT ARRAY AS
$func$
DECLARE
    names              TEXT ARRAY;
    concatenated_names TEXT ARRAY;
BEGIN
    names = generate_names(code);
    IF names IS NULL THEN
        RETURN NULL;
    END IF;
    FOR i IN 1..n
        LOOP
            concatenated_names =
                    array_append(concatenated_names, concatenate_name(names));
        END LOOP;
    RETURN concatenated_names;
END
$func$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generate_concatenated_male_names(n INTEGER) RETURNS TEXT ARRAY AS
$func$
BEGIN
    RETURN generate_concatenated_names('M', n);
END
$func$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generate_concatenated_female_names(n INTEGER) RETURNS TEXT ARRAY AS
$func$
BEGIN
    RETURN generate_concatenated_names('F', n);
END
$func$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION concatenate_name(names TEXT ARRAY) RETURNS TEXT AS
$func$
DECLARE
    names_match BOOLEAN DEFAULT TRUE;
    n           INTEGER DEFAULT cardinality(names);
    i           INTEGER;
    j           INTEGER;
BEGIN
    WHILE names_match
        LOOP
            i = 1 + floor(random() * n)::int;
            j = 1 + floor(random() * n)::int;
            IF (lower(names[i]) != lower(names[j])) THEN
                names_match = FALSE;
            END IF;
        END LOOP;
    RETURN concat(names[i], ' ', names[j]);
END
$func$ LANGUAGE plpgsql;

SELECT * from unnest(generate_concatenated_male_names(5));