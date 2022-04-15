REVOKE CONNECT ON DATABASE cuidamed FROM public;

SELECT pg_terminate_backend(pid)
FROM pg_stat_activity
WHERE datname = 'cuidamed';

DROP DATABASE cuidamed;

CREATE DATABASE cuidamed;
CREATE USER cuidamed_dev WITH LOGIN SUPERUSER ENCRYPTED PASSWORD 'MVmYneLqe91$';
GRANT ALL PRIVILEGES ON DATABASE cuidamed TO cuidamed_dev;
