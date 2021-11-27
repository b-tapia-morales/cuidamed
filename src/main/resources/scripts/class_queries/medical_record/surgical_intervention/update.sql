UPDATE residence.surgical_intervention
SET hospital    = ?,
    severity    = ?,
    description = ?
WHERE rut = ?;