UPDATE residence.person
SET first_names      = ?,
    last_name        = ?,
    second_last_name = ?,
    birth_date       = ?,
    gender           = ?
WHERE rut = ?;

UPDATE residence.elder
SET is_active      = ?,
    admission_date = ?
WHERE rut = ?;


