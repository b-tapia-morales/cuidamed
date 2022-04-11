CREATE OR REPLACE FUNCTION generate_random_name()
    RETURNS VARCHAR AS
$func$
DECLARE
    names_array TEXT ARRAY DEFAULT ARRAY ['Antonio', 'Manuel', 'Jose', 'Francisco', 'David',
        'Juan', 'Javier', 'Daniel', 'Carlos', 'Jesus', 'Alejandro', 'Miguel', 'Rafael', 'Pedro',
        'Pablo', 'Angel', 'Sergio', 'Fernando', 'Jorge', 'Luis', 'Alberto', 'Alvaro', 'Adrian',
        'Diego', 'Raul', 'Ivan', 'Ruben', 'Enrique', 'Oscar', 'Ramon', 'Vicente', 'Andres',
        'Joaquin', 'Santiago', 'Victor', 'Eduardo', 'Mario', 'Roberto', 'Jaime', 'Marcos',
        'Ignacio', 'Alfonso', 'Jordi', 'Hugo', 'Ricardo', 'Salvador', 'Guillermo', 'Emilio',
        'Gabriel', 'Marc', 'Gonzalo', 'Julio', 'Julian', 'Mohamed', 'Tomas', 'Martin',
        'Agustin', 'Nicolas', 'Ismael', 'Joan', 'Felix', 'Samuel', 'Cristian', 'Aitor', 'Lucas',
        'Hector', 'Iker', 'Josep', 'Alex', 'Mariano', 'Domingo', 'Sebastian', 'Alfredo',
        'Cesar', 'Felipe', 'Rodrigo', 'Mateo', 'Xavier'];
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

