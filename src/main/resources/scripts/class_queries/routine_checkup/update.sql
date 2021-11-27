UPDATE residence.routine_checkup
SET height             =?,
    weight             = ?,
    bmi                = ?,
    heart_rate         = ?,
    diastolic_pressure = ?,
    systolic_pressure  = ?,
    body_temperature   = ?
WHERE rut = ?
  AND checkup_date = ?;