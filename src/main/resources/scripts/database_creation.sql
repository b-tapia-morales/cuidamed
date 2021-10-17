REVOKE CONNECT ON DATABASE cuidamed FROM public;

SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = 'cuidamed';

DROP DATABASE cuidamed;

CREATE DATABASE cuidamed
    WITH OWNER = postgres
    ENCODING 'UTF8'
    LC_COLLATE = 'es_ES.UTF-8'
    LC_CTYPE = 'es_ES.UTF-8'
    TEMPLATE template0;
