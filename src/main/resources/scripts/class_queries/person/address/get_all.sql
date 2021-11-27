SELECT R.region_name,
       P.province_name,
       C.commune_name,
       A.street,
       A.number,
       A.postal_code,
       A.fixed_phone,
       A.rut
FROM residence.region R,
     residence.province P,
     residence.commune C,
     residence.address A
WHERE R.id = P.region_id
  AND P.id = C.province_id
  AND C.id = A.commune_id;
