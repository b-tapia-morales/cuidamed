CREATE OR REPLACE FUNCTION generate_male_names()
    RETURNS VARCHAR AS
$func$
DECLARE
    names_array TEXT ARRAY DEFAULT ARRAY ['Agustín', 'Benjamín', 'Vicente', 'Martín', 'Matías',
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
    names_match BOOLEAN DEFAULT TRUE;
    n           INTEGER DEFAULT cardinality(names_array);
    i           INTEGER;
    j           INTEGER;
BEGIN
    WHILE names_match
        LOOP
            i = 1 + floor(random() * n)::int;
            j = 1 + floor(random() * n)::int;
            IF (lower(names_array[i]) != lower(names_array[j])) THEN
                names_match = FALSE;
            END IF;
        END LOOP;
    RETURN concat(names_array[i], ' ', names_array[j]);
END
$func$ LANGUAGE plpgsql;

