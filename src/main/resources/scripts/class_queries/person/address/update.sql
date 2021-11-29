UPDATE residence.address
SET commune_id  = ?,
    street      = ?,
    number      = ?,
    postal_code = ?,
    fixed_phone = ?
WHERE rut = ?;