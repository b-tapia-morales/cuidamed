WITH address_table AS (SELECT Reg.region_name    AS region,
                              Prov.province_name AS province,
                              Comm.commune_name  AS commune,
                              Addr.street,
                              Addr.number,
                              Addr.postal_code,
                              Addr.fixed_phone,
                              Addr.rut
                       FROM residence.address Addr,
                            residence.responsible R,
                            residence.region Reg,
                            residence.province Prov,
                            residence.commune Comm
                       WHERE Reg.id = Prov.region_id
                         AND Prov.id = Comm.province_id
                         AND Comm.id = Addr.commune_id
                         AND Addr.rut = R.rut),
     person_table AS (SELECT personE.rut                      AS elder_rut,
                             CONCAT(personE.first_names, ' ', personE.last_name, ' ',
                                    personE.second_last_name) as elder_full_name,
                             personR.rut                      AS responsible_rut,
                             CONCAT(personR.first_names, ' ', personR.last_name, ' ',
                                    personR.second_last_name) as responsible_full_name,
                             CONCAT('+56 9 ', R.mobile_phone) AS mobile_phone
                      FROM residence.person personE,
                           residence.elder E,
                           residence.person personR,
                           residence.responsible R
                      WHERE personE.rut = E.rut
                        AND personR.rut = R.rut
                        AND R.rut = E.responsible_rut)
SELECT P.elder_rut,
       P.elder_full_name,
       P.responsible_rut,
       P.responsible_full_name,
       P.mobile_phone,
       A.fixed_phone,
       A.region,
       A.province,
       A.commune,
       A.street,
       A.number
FROM person_table P,
     address_table A
WHERE P.responsible_rut = A.rut;
