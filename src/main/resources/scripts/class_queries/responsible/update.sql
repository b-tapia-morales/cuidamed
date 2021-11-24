UPDATE residence.person
SET first_names      = ?,
    last_name        = ?,
    second_last_name = ?,
    birth_date       = ?,
    gender           = ?
WHERE rut = ?;

UPDATE residence.responsible
SET mobile_phone = ?
WHERE rut = ?;

