SELECT P.rut,
       CONCAT(P.first_names, ' ', P.last_name, ' ',
              P.second_last_name) AS full_name,
       RC.checkup_date,
       RC.height,
       RC.weight,
       RC.bmi,
       RC.heart_rate,
       RC.diastolic_pressure,
       RC.systolic_pressure,
       RC.body_temperature
FROM residence.person P,
     residence.routine_checkup RC
WHERE P.rut = RC.rut;