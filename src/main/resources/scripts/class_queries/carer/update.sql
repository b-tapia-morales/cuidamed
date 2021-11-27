UPDATE residence.person
SET first_names      = ?,
    last_name        = ?,
    second_last_name = ?,
    birth_date       = ?,
    gender           = ?
WHERE rut = ?;

UPDATE residence.carer
SET mobile_phone = ?,
    hire_date    = ?
WHERE rut = ?;
