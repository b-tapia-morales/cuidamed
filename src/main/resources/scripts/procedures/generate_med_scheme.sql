/*
 Solo estoy creando un esquema, que consiste de 3 filas con status 0
 (asumiendo que '0' significa' incompleto o 'pendiente'), sin la fecha real
 de administración y que se generan en intervalos de 8 horitas.

 Para usarlo, escribir call insert_med_scheme(rut del tata, medicamento, rut del cuidador);
 */
CREATE OR REPLACE PROCEDURE insert_med_scheme(patient_rut varchar, med_name varchar,
                                              car_rut varchar)
    LANGUAGE plpgsql
AS
$$
DECLARE
    new_est_date1 TIMESTAMP;
    new_est_date2 TIMESTAMP;
    new_est_date3 TIMESTAMP;
BEGIN
    new_est_date1 = current_timestamp + interval '8 hours';
    new_est_date2 = new_est_date1 + interval '8 hours';
    new_est_date3 = new_est_date2 + interval '8 hours';
    insert into residence.medication_administration
    values (patient_rut, med_name, new_est_date1, null, 0, car_rut);
    insert into residence.medication_administration
    values (patient_rut, med_name, new_est_date2, null, 0, car_rut);
    insert into residence.medication_administration
    values (patient_rut, med_name, new_est_date3, null, 0, car_rut);
end;
$$;

CREATE OR REPLACE FUNCTION auto_generate_scheme()
    RETURNS TRIGGER AS
$$
BEGIN
    /*
    esto no tiene mucho sentido tbh, sería raro tener que terminar los demás
    para poder continuar un tratamiento xd.
    */
    IF ((SELECT count(*)
         from residence.medication_administration
         where elder_rut = new.elder_rut
           and status = 0) < 1) THEN
        RETURN new;
    ELSE
        RAISE EXCEPTION 'Finish the previous ones first';
    end if;
END
$$ LANGUAGE plpgsql;

/* Entiendo que el trigger se ejecuta con cualquier insert en la tabla medication_administration.
   No creo que sea muy grave tho, porque igual el insert_med_scheme puede hacer esa pega y que
   el cuidador solo se encargue de actualizar datos.
 */
CREATE TRIGGER auto_med_scheme_trg
    BEFORE INSERT
    ON residence.medication_administration
    FOR EACH ROW
EXECUTE FUNCTION auto_generate_scheme();

call insert_med_scheme('8742099-k', 'Paracetamol', '15787461-6');

INSERT INTO residence.routine_checkup
VALUES ('14585523-3', '2022-04-10', 1, 1, 1, 1, 1, 1, 1);

SELECT *
FROM elder;

SELECT *
FROM carer;

select *
from medication_administration;
