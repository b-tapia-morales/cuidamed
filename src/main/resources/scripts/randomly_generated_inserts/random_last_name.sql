CREATE OR REPLACE FUNCTION generate_last_names(n INTEGER) RETURNS TEXT ARRAY AS
$func$
DECLARE
    last_names           TEXT ARRAY DEFAULT ARRAY ['González', 'Muñoz', 'Rojas', 'Díaz', 'Pérez',
        'Soto', 'Contreras', 'Silva', 'Martínez', 'Sepúlveda', 'Morales', 'Rodríguez', 'López',
        'Fuentes', 'Hernández', 'Torres', 'Araya', 'Flores', 'Espinoza', 'Valenzuela',
        'Castillo', 'Tapia', 'Reyes', 'Gutiérrez', 'Castro', 'Pizarro', 'Álvarez', 'Vásquez',
        'Sánchez', 'Fernández', 'Ramírez', 'Carrasco', 'Gómez', 'Cortés', 'Herrera', 'Núñez',
        'Jara', 'Vergara', 'Rivera', 'Figueroa', 'Riquelme', 'García', 'Miranda', 'Bravo',
        'Vera', 'Molina', 'Vega', 'Campos', 'Sandoval', 'Orellana', 'Cárdenas', 'Olivares',
        'Alarcón', 'Gallardo', 'Ortiz', 'Garrido', 'Salazar', 'Guzmán', 'Henríquez', 'Saavedra',
        'Navarro', 'Aguilera', 'Parra', 'Romero', 'Aravena', 'Vargas', 'Vázquez', 'Cáceres',
        'Yáñez', 'Leiva', 'Escobar', 'Ruiz', 'Valdés', 'Vidal', 'Salinas', 'Zúñiga', 'Peña',
        'Godoy', 'Lagos', 'Maldonado', 'Bustos', 'Medina', 'Pino', 'Palma', 'Moreno', 'Sanhueza',
        'Carvajal', 'Navarrete', 'Sáez', 'Alvarado', 'Donoso', 'Poblete', 'Bustamante', 'Toro',
        'Ortega', 'Venegas', 'Guerrero', 'Mendoza', 'Farías', 'San Martín'];
    generated_last_names TEXT ARRAY;
BEGIN
    FOR i in 1..n
        LOOP
            generated_last_names = array_append(generated_last_names, 1 + floor(random() * n)::int);
        END LOOP;
    RETURN generated_last_names;
END;
$func$ language plpgsql;

CREATE OR REPLACE FUNCTION generate_concatenated_last_names(n INTEGER) RETURNS TEXT ARRAY AS
$func$
DECLARE
    last_names           TEXT ARRAY DEFAULT ARRAY ['González', 'Muñoz', 'Rojas', 'Díaz', 'Pérez',
        'Soto', 'Contreras', 'Silva', 'Martínez', 'Sepúlveda', 'Morales', 'Rodríguez', 'López',
        'Fuentes', 'Hernández', 'Torres', 'Araya', 'Flores', 'Espinoza', 'Valenzuela',
        'Castillo', 'Tapia', 'Reyes', 'Gutiérrez', 'Castro', 'Pizarro', 'Álvarez', 'Vásquez',
        'Sánchez', 'Fernández', 'Ramírez', 'Carrasco', 'Gómez', 'Cortés', 'Herrera', 'Núñez',
        'Jara', 'Vergara', 'Rivera', 'Figueroa', 'Riquelme', 'García', 'Miranda', 'Bravo',
        'Vera', 'Molina', 'Vega', 'Campos', 'Sandoval', 'Orellana', 'Cárdenas', 'Olivares',
        'Alarcón', 'Gallardo', 'Ortiz', 'Garrido', 'Salazar', 'Guzmán', 'Henríquez', 'Saavedra',
        'Navarro', 'Aguilera', 'Parra', 'Romero', 'Aravena', 'Vargas', 'Vázquez', 'Cáceres',
        'Yáñez', 'Leiva', 'Escobar', 'Ruiz', 'Valdés', 'Vidal', 'Salinas', 'Zúñiga', 'Peña',
        'Godoy', 'Lagos', 'Maldonado', 'Bustos', 'Medina', 'Pino', 'Palma', 'Moreno', 'Sanhueza',
        'Carvajal', 'Navarrete', 'Sáez', 'Alvarado', 'Donoso', 'Poblete', 'Bustamante', 'Toro',
        'Ortega', 'Venegas', 'Guerrero', 'Mendoza', 'Farías', 'San Martín'];
    generated_last_names TEXT ARRAY;
    i                    INTEGER;
    j                    INTEGER;
BEGIN
    FOR k in 1..n
        LOOP
            i = 1 + floor(random() * n)::int;
            j = 1 + floor(random() * n)::int;
            generated_last_names = array_append(generated_last_names, concat(last_names[i], ' ',
                                                                             last_names[j]));
        END LOOP;
    RETURN generated_last_names;
END;
$func$ language plpgsql;

SELECT *
from unnest(generate_concatenated_last_names(50)) AS last_names;